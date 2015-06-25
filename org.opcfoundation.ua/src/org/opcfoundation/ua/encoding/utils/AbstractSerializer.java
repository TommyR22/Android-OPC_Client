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

package org.opcfoundation.ua.encoding.utils;

import java.util.Collection;

import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.encoding.DecodingException;
import org.opcfoundation.ua.encoding.EncodeType;
import org.opcfoundation.ua.encoding.EncodingException;
import org.opcfoundation.ua.encoding.IDecoder;
import org.opcfoundation.ua.encoding.IEncodeable;
import org.opcfoundation.ua.encoding.IEncoder;
import org.opcfoundation.ua.encoding.binary.IEncodeableSerializer;

/**
 * Simple serializer that can serialize only one type of class.
 * 
 * @author Toni Kalajainen (toni.kalajainen@vtt.fi)
 */
public abstract class AbstractSerializer implements IEncodeableSerializer {

	Class<? extends IEncodeable> clazz;
	NodeId binaryId, xmlId;
	
	public AbstractSerializer(Class<? extends IEncodeable> clazz, NodeId binaryId, NodeId xmlId)
	{
		if (clazz==null)
			throw new IllegalArgumentException("null arg");
		this.clazz = clazz;
		this.binaryId = binaryId;
		this.xmlId = xmlId;
	}
	
	public abstract void calcEncodeable(IEncodeable encodeable, IEncoder calculator)
	throws EncodingException;

	public abstract void putEncodeable(IEncodeable encodeable, IEncoder encoder) 
	throws EncodingException;
	
	public abstract IEncodeable getEncodeable(IDecoder decoder) 
	throws DecodingException;
	
	
	@Override
	public void calcEncodeable(Class<? extends IEncodeable> clazz,
			IEncodeable encodeable, IEncoder calculator)
			throws EncodingException {
		if (!clazz.equals(this.clazz))
			throw new EncodingException("Cannot encode "+clazz);
		calcEncodeable(encodeable, calculator);
	}


	@Override
	public void putEncodeable(Class<? extends IEncodeable> clazz,
			IEncodeable encodeable, IEncoder encoder) throws EncodingException
			 {
		if (!clazz.equals(this.clazz))
			throw new EncodingException("Cannot encode "+clazz);
		putEncodeable(encodeable, encoder);
	}	
	@Override
	public Class<? extends IEncodeable> getClass(NodeId id) {
		return (id.equals(binaryId) || id.equals(xmlId)) ? clazz : null; 
	}

	@Override
	public NodeId getNodeId(Class<? extends IEncodeable> clazz, EncodeType type) {
		if (type==EncodeType.Binary) return binaryId;
		if (type==EncodeType.Xml) return xmlId;
		return null; 
	}
	
	@Override
	public IEncodeable getEncodeable(Class<? extends IEncodeable> clazz,
			IDecoder decoder) throws DecodingException {
		if (!clazz.equals(this.clazz))
			throw new DecodingException("Cannot decode "+clazz);
		return getEncodeable(decoder);
	}

	@Override
	public void getSupportedClasses(Collection<Class<? extends IEncodeable>> result) {
		result.add(clazz);
	}

	@Override
	public void getSupportedNodeIds(Collection<NodeId> result) {
		if (binaryId!=null)
			result.add(binaryId);
		if (xmlId!=null)
			result.add(xmlId);
	}


}
