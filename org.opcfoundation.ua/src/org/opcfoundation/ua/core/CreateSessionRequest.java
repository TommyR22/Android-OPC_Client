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
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.RequestHeader;


public class CreateSessionRequest extends Object implements ServiceRequest {

	public static final NodeId ID = Identifiers.CreateSessionRequest;
	public static final NodeId BINARY = Identifiers.CreateSessionRequest_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.CreateSessionRequest_Encoding_DefaultXml;	
	
    protected RequestHeader RequestHeader;
    protected ApplicationDescription ClientDescription;
    protected String ServerUri;
    protected String EndpointUrl;
    protected String SessionName;
    protected byte[] ClientNonce;
    protected byte[] ClientCertificate;
    protected Double RequestedSessionTimeout;
    protected UnsignedInteger MaxResponseMessageSize;
    
    public CreateSessionRequest() {}
    
    public CreateSessionRequest(RequestHeader RequestHeader, ApplicationDescription ClientDescription, String ServerUri, String EndpointUrl, String SessionName, byte[] ClientNonce, byte[] ClientCertificate, Double RequestedSessionTimeout, UnsignedInteger MaxResponseMessageSize)
    {
        this.RequestHeader = RequestHeader;
        this.ClientDescription = ClientDescription;
        this.ServerUri = ServerUri;
        this.EndpointUrl = EndpointUrl;
        this.SessionName = SessionName;
        this.ClientNonce = ClientNonce;
        this.ClientCertificate = ClientCertificate;
        this.RequestedSessionTimeout = RequestedSessionTimeout;
        this.MaxResponseMessageSize = MaxResponseMessageSize;
    }
    
    public RequestHeader getRequestHeader()
    {
        return RequestHeader;
    }
    
    public void setRequestHeader(RequestHeader RequestHeader)
    {
        this.RequestHeader = RequestHeader;
    }
    
    public ApplicationDescription getClientDescription()
    {
        return ClientDescription;
    }
    
    public void setClientDescription(ApplicationDescription ClientDescription)
    {
        this.ClientDescription = ClientDescription;
    }
    
    public String getServerUri()
    {
        return ServerUri;
    }
    
    public void setServerUri(String ServerUri)
    {
        this.ServerUri = ServerUri;
    }
    
    public String getEndpointUrl()
    {
        return EndpointUrl;
    }
    
    public void setEndpointUrl(String EndpointUrl)
    {
        this.EndpointUrl = EndpointUrl;
    }
    
    public String getSessionName()
    {
        return SessionName;
    }
    
    public void setSessionName(String SessionName)
    {
        this.SessionName = SessionName;
    }
    
    public byte[] getClientNonce()
    {
        return ClientNonce;
    }
    
    public void setClientNonce(byte[] ClientNonce)
    {
        this.ClientNonce = ClientNonce;
    }
    
    public byte[] getClientCertificate()
    {
        return ClientCertificate;
    }
    
    public void setClientCertificate(byte[] ClientCertificate)
    {
        this.ClientCertificate = ClientCertificate;
    }
    
    public Double getRequestedSessionTimeout()
    {
        return RequestedSessionTimeout;
    }
    
    public void setRequestedSessionTimeout(Double RequestedSessionTimeout)
    {
        this.RequestedSessionTimeout = RequestedSessionTimeout;
    }
    
    public UnsignedInteger getMaxResponseMessageSize()
    {
        return MaxResponseMessageSize;
    }
    
    public void setMaxResponseMessageSize(UnsignedInteger MaxResponseMessageSize)
    {
        this.MaxResponseMessageSize = MaxResponseMessageSize;
    }
    
    /**
      * Deep clone
      *
      * @return cloned CreateSessionRequest
      */
    public CreateSessionRequest clone()
    {
        CreateSessionRequest result = new CreateSessionRequest();
        result.RequestHeader = RequestHeader==null ? null : RequestHeader.clone();
        result.ClientDescription = ClientDescription==null ? null : ClientDescription.clone();
        result.ServerUri = ServerUri;
        result.EndpointUrl = EndpointUrl;
        result.SessionName = SessionName;
        result.ClientNonce = ClientNonce;
        result.ClientCertificate = ClientCertificate;
        result.RequestedSessionTimeout = RequestedSessionTimeout;
        result.MaxResponseMessageSize = MaxResponseMessageSize;
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
