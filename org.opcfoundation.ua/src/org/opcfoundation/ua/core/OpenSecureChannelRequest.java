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

import org.opcfoundation.ua.builtintypes.ServiceRequest;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.MessageSecurityMode;
import org.opcfoundation.ua.core.RequestHeader;
import org.opcfoundation.ua.core.SecurityTokenRequestType;


public class OpenSecureChannelRequest extends Object implements ServiceRequest {

	public static final NodeId ID = Identifiers.OpenSecureChannelRequest;
	public static final NodeId BINARY = Identifiers.OpenSecureChannelRequest_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.OpenSecureChannelRequest_Encoding_DefaultXml;	
	
    protected RequestHeader RequestHeader;
    protected UnsignedInteger ClientProtocolVersion;
    protected SecurityTokenRequestType RequestType;
    protected MessageSecurityMode SecurityMode;
    protected byte[] ClientNonce;
    protected UnsignedInteger RequestedLifetime;
    
    public OpenSecureChannelRequest() {}
    
    public OpenSecureChannelRequest(RequestHeader RequestHeader, UnsignedInteger ClientProtocolVersion, SecurityTokenRequestType RequestType, MessageSecurityMode SecurityMode, byte[] ClientNonce, UnsignedInteger RequestedLifetime)
    {
        this.RequestHeader = RequestHeader;
        this.ClientProtocolVersion = ClientProtocolVersion;
        this.RequestType = RequestType;
        this.SecurityMode = SecurityMode;
        this.ClientNonce = ClientNonce;
        this.RequestedLifetime = RequestedLifetime;
    }
    
    public RequestHeader getRequestHeader()
    {
        return RequestHeader;
    }
    
    public void setRequestHeader(RequestHeader RequestHeader)
    {
        this.RequestHeader = RequestHeader;
    }
    
    public UnsignedInteger getClientProtocolVersion()
    {
        return ClientProtocolVersion;
    }
    
    public void setClientProtocolVersion(UnsignedInteger ClientProtocolVersion)
    {
        this.ClientProtocolVersion = ClientProtocolVersion;
    }
    
    public SecurityTokenRequestType getRequestType()
    {
        return RequestType;
    }
    
    public void setRequestType(SecurityTokenRequestType RequestType)
    {
        this.RequestType = RequestType;
    }
    
    public MessageSecurityMode getSecurityMode()
    {
        return SecurityMode;
    }
    
    public void setSecurityMode(MessageSecurityMode SecurityMode)
    {
        this.SecurityMode = SecurityMode;
    }
    
    public byte[] getClientNonce()
    {
        return ClientNonce;
    }
    
    public void setClientNonce(byte[] ClientNonce)
    {
        this.ClientNonce = ClientNonce;
    }
    
    public UnsignedInteger getRequestedLifetime()
    {
        return RequestedLifetime;
    }
    
    public void setRequestedLifetime(UnsignedInteger RequestedLifetime)
    {
        this.RequestedLifetime = RequestedLifetime;
    }
    
    /**
      * Deep clone
      *
      * @return cloned OpenSecureChannelRequest
      */
    public OpenSecureChannelRequest clone()
    {
        OpenSecureChannelRequest result = new OpenSecureChannelRequest();
        result.RequestHeader = RequestHeader==null ? null : RequestHeader.clone();
        result.ClientProtocolVersion = ClientProtocolVersion;
        result.RequestType = RequestType;
        result.SecurityMode = SecurityMode;
        result.ClientNonce = ClientNonce;
        result.RequestedLifetime = RequestedLifetime;
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
