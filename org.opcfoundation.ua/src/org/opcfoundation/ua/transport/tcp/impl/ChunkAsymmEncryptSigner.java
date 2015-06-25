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
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import org.apache.log4j.Logger;
import org.opcfoundation.ua.common.RuntimeServiceResultException;
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.MessageSecurityMode;
import org.opcfoundation.ua.core.StatusCodes;
import org.opcfoundation.ua.transport.security.SecurityConfiguration;
import org.opcfoundation.ua.transport.security.SecurityAlgorithm;
import org.opcfoundation.ua.transport.security.SecurityPolicy;
import org.opcfoundation.ua.utils.CryptoUtil;


/**
 *
 * 
 */
public class ChunkAsymmEncryptSigner implements Runnable {

	/**
	 * Log4J Error logger. 
	 * Security failures are logged with INFO level.
	 * Security settings are logged with DEBUG level.
	 * Unexpected errors are logged with ERROR level. 
	 */
	static Logger logger = Logger.getLogger(ChunkAsymmEncryptSigner.class);
	
	ByteBuffer chunk, plaintext;
	SecurityConfiguration profile;

	private int signatureSize;
	
	public ChunkAsymmEncryptSigner(ByteBuffer chunk, ByteBuffer body, SecurityConfiguration profile)
	{
		this.chunk = chunk;
		this.plaintext = body;
		this.profile = profile;
	}
	
	@Override
	public void run() 
	throws RuntimeServiceResultException
	{
		try {
			int plaintextSize = plaintext.limit();
			MessageSecurityMode msm = profile.getMessageSecurityMode();
			if ( msm == MessageSecurityMode.Sign ) msm = MessageSecurityMode.SignAndEncrypt;

			SecurityPolicy policy = profile.getSecurityPolicy();
			int sequenceHeader = 8;
			SecurityAlgorithm signatureAlgorithm = policy.getAsymmetricSignatureAlgorithm();
			signatureSize = msm.hasSigning() ? CryptoUtil.getSignatureSize(signatureAlgorithm, profile.getLocalPrivateKey()) : 0;
			
			//At this point padding has already added to the chunk, check AsymChunkfactory
			
			//Get bytes that need to be signed
			//Amount of bytes to sign depends on policy (mode) in use
			if (logger.isDebugEnabled())
				logger.debug("SecurityMode in asymm enc: "+ msm.getValue());

			// Padding size includes the total padding: PaddingSize and ExtraPaddingSize bytes + padding
			int paddingSize = 0;
			
			// Encrypting
			if ( msm == MessageSecurityMode.SignAndEncrypt ){
	        	// Because of the encypting, add the minimal +1 or +2 padding bytes to the total size 
	        	int keySize = profile.getRemoteCertificate2().getKeySize();
	        	if (logger.isTraceEnabled())
	        		logger.trace("keySize="+ keySize);
				paddingSize = getPaddingSize(keySize);
	        	if (logger.isTraceEnabled()) {
	        		logger.trace("padding="+ paddingSize);
	        	}
			}
			
			// Signing
			if ( msm == MessageSecurityMode.Sign || msm == MessageSecurityMode.SignAndEncrypt ) {
				
				byte[] plaintextForSign = new byte[plaintext.arrayOffset()+plaintextSize+paddingSize];
	        
		        // Set position to beginning of the chunk
		        chunk.rewind();
		        chunk.get(plaintextForSign, 0, plaintextForSign.length);
		        
		        // Sign
		        byte[] signature = sign(plaintextForSign, profile.getLocalPrivateKey());

		        // Write back to chunk
	        	chunk.put(signature);
			}
			
	        if (logger.isTraceEnabled())
	        	logger.trace("getPaddingSize: chunk=" + CryptoUtil.toHex(chunk.array(), 64));
	        
	        if ( msm == MessageSecurityMode.SignAndEncrypt ) {
		        // Encrypt everything but the messageHeader and securityHeader
		        // Plaintext tells where the body of this message begins...
		        // There is sequenceHeader (size = 8) before the body and we want to encrypt that too 
		        
		        //Get amount of padding, which depends on security policy (mode) in use
		        byte[] bytesToEncrypt = new byte[ sequenceHeader+plaintextSize+paddingSize+signatureSize ];
		        
		        //Encrypt
				chunk.position( plaintext.arrayOffset() - sequenceHeader );
				chunk.get(bytesToEncrypt, 0, bytesToEncrypt.length);
				encrypt(bytesToEncrypt, 
						profile.getRemoteCertificate(),
						chunk.array(), 
						plaintext.arrayOffset() - sequenceHeader);
	        }
				
	        //set chunk's position to the starting point of plaintext
			chunk.position(plaintext.arrayOffset());
			
		} catch (ServiceResultException e) {
			throw new RuntimeServiceResultException(e);
		}
	}
	
	/**
	 * Returns the number of bytes in the padding including paddingSize and
	 * possible extraPaddingByte.
	 * 
	 * @param keySize
	 * @return
	 */
	private int getPaddingSize(int keySize){
		//int lastPaddingBytePosition = chunk.limit() - signatureSize -1;
		int lastPaddingBytePosition = chunk.limit() - 1;
		
		if (logger.isTraceEnabled())
		{
			logger.trace("getPaddingSize: chunk=" + CryptoUtil.toHex(chunk.array(), 64));
			logger.trace("getPaddingSize: plaintext=" + CryptoUtil.toHex(plaintext.array(), 64));
			logger.trace("getPaddingSize: plaintext.arrayOffset()=" + plaintext.arrayOffset());
			logger.trace("getPaddingSize: plaintext.limit()=" + plaintext.limit());
			logger.trace("getPaddingSize: lastPaddingBytePosition=" + lastPaddingBytePosition);
		}
		
		if ( keySize > 2048 ) {
			int extraPaddingByte = chunk.get(lastPaddingBytePosition) & 0xff;
			int paddingByte = chunk.get(lastPaddingBytePosition - 1) & 0xff;
			if (logger.isTraceEnabled()) {
				logger.trace("getPaddingSize: paddingByte=" + paddingByte);
				logger.trace("getPaddingSize: extraPaddingByte=" + extraPaddingByte);
				logger.trace("getPaddingSize: padding="
						+ (paddingByte | extraPaddingByte << 8));
			}
			return ( ( paddingByte & 0xff ) | ( (extraPaddingByte & 0xff) << 8 ) ) + 2;
		} else {
			int paddingByte = chunk.get(lastPaddingBytePosition) & 0xff;
			return ( paddingByte & 0xff ) + 1;
		}
		
	}
	
	//This function checks how the encryption is done.
	private void encrypt(byte[] dataToEncrypt, Certificate encryptingCertificate, byte[] output, int outputOffset ) throws ServiceResultException{
		SecurityPolicy policy = profile.getSecurityPolicy();
		if (logger.isDebugEnabled()) logger.debug("rsa_Encrypt: policy="+policy);
		
		RSAPublicKey serverPublic =  (RSAPublicKey) encryptingCertificate.getPublicKey();
		Cipher cipher = null;
		
		int inputBlockSize = 1;
		Key key = profile.getRemoteCertificate().getPublicKey();
		inputBlockSize = CryptoUtil.getPlainTextBlockSize(policy.getAsymmetricEncryptionAlgorithm(), key);
		
		if (logger.isDebugEnabled()) logger.debug("encrypt: inputBlockSize="+inputBlockSize);
		
		//get RSAPublicKey from Certificate
		
		// verify that the input data has the correct block size
		if(dataToEncrypt.length % inputBlockSize !=0) {
			logger.error(String.format("Wrong block size in asym encryption: length=%d inputBlockSize=%d", dataToEncrypt.length, inputBlockSize));
			throw new ServiceResultException(StatusCodes.Bad_InternalError, "Error in asymmetric encrypt: Input data is not an even number of encryption blocks.");
		}
		
		try {
			
			try {
				cipher = Cipher.getInstance(policy.getAsymmetricEncryptionAlgorithm().getTransformation(), CryptoUtil.getSecurityProviderName());
			} catch (NoSuchAlgorithmException e) {
				// SunJCE does not regognize RSA/NONE/PKCS1Padding, but will return such with plain RSA
				cipher = Cipher.getInstance(policy.getAsymmetricEncryptionAlgorithm().getStandardName(), CryptoUtil.getSecurityProviderName());
			}
			if (logger.isTraceEnabled()) logger.trace("encrypt: cipher.provider=" + cipher.getProvider());
			cipher.init(Cipher.ENCRYPT_MODE, serverPublic);
			
			//encrypt one block at time
			int maxIndex = outputOffset+dataToEncrypt.length;
			
			int inputOffset = 0;
			for(int index = outputOffset; index < maxIndex; index += inputBlockSize){
				int amountOfEncryptedBytes = cipher.doFinal(
						dataToEncrypt, inputOffset, 
						inputBlockSize, 
						output, outputOffset); //vika 0 kuuluu olla index
				inputOffset += inputBlockSize;
				outputOffset += amountOfEncryptedBytes;	
				if (logger.isDebugEnabled()) {			
					logger.debug("Asym ecryption: amountOfEncryptedBytes="
							+ amountOfEncryptedBytes + " inputOffset="
							+ inputOffset + " outputOffset=" + outputOffset
							+ " index=" + index);
				}				
			}
			
		} catch (GeneralSecurityException e) {
			throw new ServiceResultException(StatusCodes.Bad_InternalError,e);
		}
		
	}
	
	private byte[] sign(byte[] dataToSign, RSAPrivateKey senderPrivate) throws ServiceResultException{		
		SecurityPolicy policy = profile.getSecurityPolicy();
		
		//Default
		if(policy == SecurityPolicy.NONE){
			return null;
		}
		
		try {
			
			Signature signer = Signature.getInstance(policy.getAsymmetricSignatureAlgorithm().getStandardName(), CryptoUtil.getSecurityProviderName(Signature.class));
			signer.initSign(senderPrivate);
			//compute hash of the message

			signer.update(dataToSign);
			byte[] signature = signer.sign();
			
			if (logger.isTraceEnabled()) {
				logger.trace( senderPrivate );
				logger.trace("sign: dataToSign=" + CryptoUtil.toHex(dataToSign,64));
				logger.trace("sign: signature="+CryptoUtil.toHex(signature));				
			}
			return signature;
			
		} catch (GeneralSecurityException e) {
			throw new ServiceResultException(StatusCodes.Bad_InternalError,e);
		} 
	}

}
