/* ========================================================================
 * Copyright (c) 2005-2013 The OPC Foundation, Inc. All rights reserved.
 *
 * OPC Reciprocal Community License ("RCL") Version 1.00
 * 
 * Unless explicitly acquired and licensed from Licensor under another 
 * license, the contents of this file are subject to the Reciprocal 
 * Community License ("RCL") Version 1.00, or subsequent versions as 
 * allowed by the RCL, and You may not copy or use this file in either 
 * source code or executable form, except in compliance with the terms and 
 * conditions of the RCL.
 * 
 * All software distributed under the RCL is provided strictly on an 
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, 
 * AND LICENSOR HEREBY DISCLAIMS ALL SUCH WARRANTIES, INCLUDING WITHOUT 
 * LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE, QUIET ENJOYMENT, OR NON-INFRINGEMENT. See the RCL for specific 
 * language governing rights and limitations under the RCL.
 *
 * The complete license agreement can be found here:
 * http://opcfoundation.org/License/RCL/1.00/
 * ======================================================================*/

package org.opcfoundation.ua.transport.tcp.impl;


import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.opcfoundation.ua.common.RuntimeServiceResultException;
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.MessageSecurityMode;
import org.opcfoundation.ua.core.StatusCodes;
import org.opcfoundation.ua.transport.security.SecurityAlgorithm;
import org.opcfoundation.ua.transport.security.SecurityPolicy;
import org.opcfoundation.ua.utils.CryptoUtil;



/**
 *
 * 
 */
public class ChunkSymmDecryptVerifier implements Runnable {

	/**
	 * Log4J Error logger. 
	 * Security failures are logged with INFO level.
	 * Security settings are logged with DEBUG level.
	 * Unexpected errors are logged with ERROR level. 
	 */
	static Logger logger = Logger.getLogger(ChunkSymmDecryptVerifier.class);
	
	static final int sequenceHeaderSize = 8;
	static final int messageHeaderSize = 8;
	static final int securityHeaderSize = 8;
	static final int SymmetricHeaders = messageHeaderSize + securityHeaderSize; //Headers which are no crypted
	ByteBuffer chunk;
	SecurityToken token;

	public ChunkSymmDecryptVerifier(ByteBuffer chunk, SecurityToken token)
	{
		this.chunk = chunk;
		this.token = token;
	}
	
	@Override
	public void run()
	throws RuntimeServiceResultException
	{
		SecurityPolicy securityPolicy = token.getSecurityPolicy();
		MessageSecurityMode msm = token.getMessageSecurityMode();
		int chunkSize = chunk.limit();
		try {
			// Security channel will be verified elsewhere
			// verify token id
			int tokenId = ChunkUtils.getTokenId(chunk);
			if (tokenId != token.getTokenId())
				throw new ServiceResultException(
						StatusCodes.Bad_UnexpectedError);

			// Move chunk position to the starting point of the body
			
			int decryptedBytes;
			if ( msm == MessageSecurityMode.SignAndEncrypt ) {
				// Option A: Decrypt to separate memory block

				// Get bytes that need to be decrypted
				chunk.position(SymmetricHeaders);
				byte dataToDecrypt[] = new byte[ chunk.limit() - SymmetricHeaders];
				chunk.get(dataToDecrypt, 0, dataToDecrypt.length);
				
				// Run decrypt algorithm
				decryptedBytes = decrypt( token, dataToDecrypt, 0, dataToDecrypt.length, chunk.array(), SymmetricHeaders + chunk.arrayOffset() );

				// Option B: Decrypt in same memory block
				// int decryptedBytes = decrypt(token, chunk.array(),
				// SymmetricHeaders + chunk.arrayOffset(),
				// chunk.limit() - SymmetricHeaders, chunk.array(),
				// SymmetricHeaders + chunk.arrayOffset());
			} else {			
				decryptedBytes = chunk.limit() - SymmetricHeaders;
			}
			
			int padding = 0;
			int signatureSize = securityPolicy.getSymmetricSignatureSize();
			
			// Verify Signature
			if ( msm == MessageSecurityMode.Sign || msm == MessageSecurityMode.SignAndEncrypt) {
				// Verify signature
				byte[] signature = new byte[signatureSize];
				// Extract signature from decrypted message
				// move buffer to the beginning of the signature
				//chunk.position(16 + decryptedBytes - signatureSize);
				chunk.position(chunkSize - signatureSize);
				// Read signature from buffer
				chunk.get(signature);

				// Get the data to verify
				chunk.position(0);
				byte[] dataToVerify = new byte[16 + decryptedBytes - signatureSize];
				chunk.get(dataToVerify, 0, dataToVerify.length);

				// Verify signature, throws ServiceResultException if fails
				verify(token, dataToVerify, signature);
			}
			
			// Assert padding is ok
			if ( msm == MessageSecurityMode.SignAndEncrypt) {

				// Verify padding
				int paddingEnd = 0;
				paddingEnd = SymmetricHeaders + decryptedBytes - signatureSize - 1;
				padding = chunk.get(paddingEnd);

				// check that every value in padding is the same
				for (int ii = paddingEnd - padding; ii < paddingEnd; ii++) {
					if (chunk.get(ii) != padding) {
						logger.error("Padding does not match");
						throw new ServiceResultException(StatusCodes.Bad_SecurityChecksFailed, "Could not verify the padding in the message");
					}
				}
				// Add the one that need to be allocated for padding
				padding++;
			}

			// Calc plaintext size
			chunk.position(messageHeaderSize + securityHeaderSize + sequenceHeaderSize);
			chunk.limit(chunk.position() + decryptedBytes - 8 - padding - signatureSize);
			int bytesToRead = chunk.limit() - messageHeaderSize - securityHeaderSize - sequenceHeaderSize - signatureSize - padding;
			if (bytesToRead < 0) {
				// throwError(StatusCodes.Bad_CommunicationError,
				// "Invalid chunk");
			}
			
		} catch (ServiceResultException e) {
			throw new RuntimeServiceResultException(e);
		}
	}
	
    private int decrypt(SecurityToken token, byte[] dataToDecrypt, int inputOffset, int inputLength, byte[] output, int outputOffset) throws ServiceResultException{
		if (logger.isDebugEnabled())
			logger.debug("decrypt: dataToDecrypt.length="+ dataToDecrypt.length + " inputOffset=" + inputOffset + " inputLength=" + inputLength + " output.length=" + output.length + " outputOffset=" + outputOffset);
		
		// Decrypt
		SecurityAlgorithm algorithm = token.getSecurityPolicy().getSymmetricEncryptionAlgorithm();
		if (logger.isTraceEnabled())
		{
			logger.trace("decrypt: token.getRemoteEncryptingKey()="+ CryptoUtil.toHex(token.getRemoteEncryptingKey()));
			logger.trace("decrypt: token.getRemoteInitializationVector()="+ CryptoUtil.toHex(token.getRemoteInitializationVector()));
			logger.trace("decrypt: dataToDecrypt="+ CryptoUtil.toHex(dataToDecrypt));
			logger.trace("decrypt: algorithm=" + algorithm);
		}

		Cipher cipher;
		int decryptedBytes = 0;

		try {
			SecretKeySpec spec = new SecretKeySpec(
					token.getRemoteEncryptingKey(), algorithm.getStandardName());
			cipher = Cipher.getInstance(algorithm.getTransformation());
			cipher.init(Cipher.DECRYPT_MODE, spec,
					new IvParameterSpec(token.getRemoteInitializationVector()));
			decryptedBytes = cipher.update(dataToDecrypt, inputOffset,
					inputLength, output, outputOffset);
			decryptedBytes += cipher.doFinal(output, outputOffset
					+ decryptedBytes);
			if (logger.isTraceEnabled())
				logger.trace("decrypt: output="+ CryptoUtil.toHex(output));
			return decryptedBytes;
		} catch (InvalidKeyException e) {
			throw new ServiceResultException(StatusCodes.Bad_SecurityChecksFailed, e);
		} catch (GeneralSecurityException e) {
			throw new ServiceResultException(StatusCodes.Bad_InternalError, e);
		} catch (IllegalStateException e) {
			throw new ServiceResultException(StatusCodes.Bad_InternalError, e);
		}

		// decrypt
	}
    
    private void verify(SecurityToken token, byte[] dataToVerify, byte[] signature) 
    throws ServiceResultException 
    {
    	//Get right hmac
    	Mac hmac = token.createRemoteHmac();
    	byte[] computedSignature = hmac.doFinal(dataToVerify);
    	
    	//Compare signatures
    	//First test that sizes are the same
    	if(signature.length != computedSignature.length){
			logger.warn("Signature lengths do not match: \n" + CryptoUtil.toHex(signature) + " vs. \n" + CryptoUtil.toHex(computedSignature));
			throw new ServiceResultException(StatusCodes.Bad_SecurityChecksFailed, "Invalid signature");
    	}
    	//Compare byte by byte
    	for(int index = 0; index < signature.length; index++){
    		if(signature[index] != computedSignature[index]){
    			logger.warn("Signatures do not match: \n" + CryptoUtil.toHex(signature) + " vs. \n" + CryptoUtil.toHex(computedSignature));
    			throw new ServiceResultException(StatusCodes.Bad_SecurityChecksFailed, "Invalid signature");

    			
    		}
    	}
    	
    	//Everything went fine, signatures matched
    }
	
}
