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

package org.opcfoundation.ua.encoding;

import org.opcfoundation.ua.common.NamespaceTable;
import org.opcfoundation.ua.common.ServerTable;
import org.opcfoundation.ua.encoding.binary.IEncodeableSerializer;
import org.opcfoundation.ua.utils.StackUtils;

/**
 *
 * 
 */
public class EncoderContext {

	private static EncoderContext DEFAULT;
	
	public NamespaceTable namespaceTable;
	public ServerTable serverTable;
	public IEncodeableSerializer encodeableSerializer;	

	public int maxMessageSize = 4*1024*1024*1024;
	
	// 0 = norestriction
    public int maxStringLength = 0; //UnsignedShort.MAX_VALUE.intValue();
    public int maxByteStringLength = 0; //UnsignedShort.MAX_VALUE.intValue() * 16;
    public int maxArrayLength = 0; //UnsignedShort.MAX_VALUE.intValue();

	public EncoderContext() {		
	}

	public EncoderContext(NamespaceTable namespaceTable, 
			ServerTable serverTable,
			IEncodeableSerializer encodeableSerializer,
			int maxMessageSize) {
		this.encodeableSerializer = encodeableSerializer;
		this.namespaceTable = namespaceTable;
		this.serverTable = serverTable;
		this.maxMessageSize = maxMessageSize;
	}

	public int getMaxMessageSize() {
		return maxMessageSize;
	}
	
	public void setMaxMessageSize(int encodeMessageMaxSize) {
		this.maxMessageSize = encodeMessageMaxSize;
	}
	
	public NamespaceTable getNamespaceTable() {
		return namespaceTable;
	}

	public void setNamespaceTable(NamespaceTable namespaceTable) {
		this.namespaceTable = namespaceTable;
	}

	public ServerTable getServerTable() {
		return serverTable;
	}

	public void setServerTable(ServerTable serverTable) {
		this.serverTable = serverTable;
	}

	public IEncodeableSerializer getEncodeableSerializer() {
		return encodeableSerializer;
	}

	public void setEncodeableSerializer(IEncodeableSerializer encodeableSerializer) {
		this.encodeableSerializer = encodeableSerializer;
	}
	
	public int getMaxStringLength() {
		return maxStringLength;
	}

	public void setMaxStringLength(int maxStringLength) {
		this.maxStringLength = maxStringLength;
	}

	public int getMaxByteStringLength() {
		return maxByteStringLength;
	}

	public void setMaxByteStringLength(int maxByteStringLength) {
		this.maxByteStringLength = maxByteStringLength;
	}

	public int getMaxArrayLength() {
		return maxArrayLength;
	}

	public void setMaxArrayLength(int maxArrayLength) {
		this.maxArrayLength = maxArrayLength;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("   namespaceTable = "+namespaceTable + "\n");		
		sb.append("   serverTable = "+serverTable + "\n");
		sb.append("   maxMessageSize = "+maxMessageSize + "\n");
		sb.append("   maxStringLength = "+maxStringLength + "\n");
		sb.append("   maxByteStringLength = "+maxByteStringLength + "\n");
		sb.append("   maxArrayLength = "+maxArrayLength + "\n");
		return sb.toString();
	}

	public synchronized static EncoderContext getDefault()
	{
		if (DEFAULT==null) {
			DEFAULT = new EncoderContext();
			DEFAULT.setServerTable(ServerTable.DEFAULT);
			DEFAULT.setNamespaceTable(NamespaceTable.DEFAULT);
			DEFAULT.setEncodeableSerializer(StackUtils.getDefaultSerializer());
		}
		return DEFAULT;
	}
	
}
