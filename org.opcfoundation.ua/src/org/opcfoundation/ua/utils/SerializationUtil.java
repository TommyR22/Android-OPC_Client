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

package org.opcfoundation.ua.utils;

import org.opcfoundation.ua.builtintypes.Structure;
import org.opcfoundation.ua.common.NamespaceTable;
import org.opcfoundation.ua.common.ServerTable;
import org.opcfoundation.ua.encoding.DecodingException;
import org.opcfoundation.ua.encoding.EncoderContext;
import org.opcfoundation.ua.encoding.EncodingException;
import org.opcfoundation.ua.encoding.binary.BinaryDecoder;
import org.opcfoundation.ua.encoding.binary.BinaryEncoder;
import org.opcfoundation.ua.encoding.binary.EncoderCalc;
import org.opcfoundation.ua.encoding.binary.IEncodeableSerializer;

public class SerializationUtil {

	public static byte[] serialize(Structure encodeable) 
	throws EncodingException
	{
		EncoderContext ctx = new EncoderContext(NamespaceTable.DEFAULT, ServerTable.DEFAULT, StackUtils.getDefaultSerializer(), Integer.MAX_VALUE);		
		ctx.setEncodeableSerializer( StackUtils.getDefaultSerializer() );
		
		EncoderCalc calc = new EncoderCalc();
		calc.setEncoderContext( ctx );
		calc.putStructure(null, encodeable);

		byte[] data = new byte[calc.getLength()];
		BinaryEncoder enc = new BinaryEncoder(data);
		enc.setEncoderContext(ctx);
		
		enc.putStructure(null, encodeable);
		return data;
	}

	public static Structure deserialize(byte[] data) throws DecodingException
	{
		IEncodeableSerializer serializer = StackUtils.getDefaultSerializer();
		EncoderContext ctx = new EncoderContext(NamespaceTable.DEFAULT, ServerTable.DEFAULT, StackUtils.getDefaultSerializer(), Integer.MAX_VALUE);
		ctx.setEncodeableSerializer( serializer );
			
		BinaryDecoder dec = new BinaryDecoder(data);
		dec.setEncoderContext(ctx);
			
		return dec.getStructure(null);			
	}
    
}
