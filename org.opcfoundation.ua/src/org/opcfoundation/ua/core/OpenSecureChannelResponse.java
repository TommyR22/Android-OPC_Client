/* ========================================================================
 * Copyright (c) 2005-2013 The OPC Foundation, Inc. All rights reserved.
 *
 * OPC Foundation MIT License 1.00
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * The complete license agreement can be found here:
 * http://opcfoundation.org/License/MIT/1.00/
 * ======================================================================*/

package org.opcfoundation.ua.core;

import org.opcfoundation.ua.builtintypes.ServiceResponse;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.ChannelSecurityToken;
import org.opcfoundation.ua.core.ResponseHeader;


public class OpenSecureChannelResponse extends Object implements ServiceResponse {

	public static final NodeId ID = Identifiers.OpenSecureChannelResponse;
	public static final NodeId BINARY = Identifiers.OpenSecureChannelResponse_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.OpenSecureChannelResponse_Encoding_DefaultXml;	
	
    protected ResponseHeader ResponseHeader;
    protected UnsignedInteger ServerProtocolVersion;
    protected ChannelSecurityToken SecurityToken;
    protected byte[] ServerNonce;
    
    public OpenSecureChannelResponse() {}
    
    public OpenSecureChannelResponse(ResponseHeader ResponseHeader, UnsignedInteger ServerProtocolVersion, ChannelSecurityToken SecurityToken, byte[] ServerNonce)
    {
        this.ResponseHeader = ResponseHeader;
        this.ServerProtocolVersion = ServerProtocolVersion;
        this.SecurityToken = SecurityToken;
        this.ServerNonce = ServerNonce;
    }
    
    public ResponseHeader getResponseHeader()
    {
        return ResponseHeader;
    }
    
    public void setResponseHeader(ResponseHeader ResponseHeader)
    {
        this.ResponseHeader = ResponseHeader;
    }
    
    public UnsignedInteger getServerProtocolVersion()
    {
        return ServerProtocolVersion;
    }
    
    public void setServerProtocolVersion(UnsignedInteger ServerProtocolVersion)
    {
        this.ServerProtocolVersion = ServerProtocolVersion;
    }
    
    public ChannelSecurityToken getSecurityToken()
    {
        return SecurityToken;
    }
    
    public void setSecurityToken(ChannelSecurityToken SecurityToken)
    {
        this.SecurityToken = SecurityToken;
    }
    
    public byte[] getServerNonce()
    {
        return ServerNonce;
    }
    
    public void setServerNonce(byte[] ServerNonce)
    {
        this.ServerNonce = ServerNonce;
    }
    
    /**
      * Deep clone
      *
      * @return cloned OpenSecureChannelResponse
      */
    public OpenSecureChannelResponse clone()
    {
        OpenSecureChannelResponse result = new OpenSecureChannelResponse();
        result.ResponseHeader = ResponseHeader==null ? null : ResponseHeader.clone();
        result.ServerProtocolVersion = ServerProtocolVersion;
        result.SecurityToken = SecurityToken==null ? null : SecurityToken.clone();
        result.ServerNonce = ServerNonce;
        return result;
    }
    
 

	public NodeId getTypeId() {
		return ID;
	}

	public NodeId getXmlEncodeId() {
		return XML;
	}

	public NodeId getBinaryEncodeId() {
		return BINARY;
	}

	public String toString() {
		return ObjectUtils.printFieldsDeep(this);
	}
	
}
