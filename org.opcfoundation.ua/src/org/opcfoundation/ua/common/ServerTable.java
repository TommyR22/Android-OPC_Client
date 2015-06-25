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


/**
 * Table for keeping the ServerUris, which are exposed by the server via the property Server.ServerArray. 
 * 
 */
public class ServerTable extends UriTable {

	public static final ServerTable DEFAULT = new ServerTable();

	public synchronized String[] toArray() {
		int len = 0;
		for (Integer i : indexUriMap.getLeftSet())
			if (i > len)
				len = i;
		len++;
		String result[] = new String[len];
		for (int i = 0; i < len; i++)
			result[i] = indexUriMap.getRight(i);
		return result;
	}

	/**
	 * Finds the namespace URI with index in the table
	 * 
	 * @param namespaceIndex
	 *            the index you are looking for
	 * @return the namespace URI with the index or null, if there is no such
	 *         index
	 */
	public String getUri(int namespaceIndex) {
		return indexUriMap.getRight(namespaceIndex);
	}

	public static ServerTable createFromArray(String[] serverArray) {
		ServerTable result = new ServerTable();
		result.addAll(serverArray);
		return result;
	}
	
}
