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

package org.opcfoundation.ua.common;

import org.opcfoundation.ua.builtintypes.UnsignedShort;
import org.opcfoundation.ua.core.EncodeableSerializer;
import org.opcfoundation.ua.encoding.binary.IEncodeableSerializer;

/**
 * Stores context information associated with a 
 * UA server that is used during message processing. 
 */
public class ServiceMessageContext {

	int maxStringLength = UnsignedShort.MAX_VALUE.intValue();
	int maxByteStringLength = UnsignedShort.MAX_VALUE.intValue()*16;
	int maxArrayLength = UnsignedShort.MAX_VALUE.intValue();
	int maxMessageSize = UnsignedShort.MAX_VALUE.intValue()*32;
	IEncodeableSerializer encodeableSerializer = EncodeableSerializer.getInstance();   
	NamespaceTable namespaceTable = NamespaceTable.getDefault();
	
	//TODO Added by Mikko
	public ServiceMessageContext(NamespaceTable namespaceTable/*, EncodeableTable encodeableTable*/) {
	      this.namespaceTable = namespaceTable;
	      ///////////////////////////////////////////7this.encodeableTable = encodeableTable;
	    }
	
	//TODO Added by Mikko
	/**
	   * Convenience constructor that uses default namespace and encodeable tables.
	   * 
	   */
	  public ServiceMessageContext() {
	      //TODO ProxyStubs.initializeProxyStubs();
	      namespaceTable = NamespaceTable.getDefault();
	      /////////////////////////////////////////////////encodeableTable = EncodeableTable.getDefault();
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
	public int getMaxMessageSize() {
		return maxMessageSize;
	}
	public void setMaxMessageSize(int maxMessageSize) {
		this.maxMessageSize = maxMessageSize;
	}
	public NamespaceTable getNamespaceTable() {
		return namespaceTable;
	}
	public void setNamespaceTable(NamespaceTable namespaceTable) {
		this.namespaceTable = namespaceTable;
	}

	public IEncodeableSerializer getEncodeableSerializer() {
		return encodeableSerializer;
	}

	public void setEncodeableSerializer(IEncodeableSerializer encodeableSerializer) {
		this.encodeableSerializer = encodeableSerializer;
	}
	
	
}
