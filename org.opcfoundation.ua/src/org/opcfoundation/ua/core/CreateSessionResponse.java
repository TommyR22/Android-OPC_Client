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
import org.opcfoundation.ua.core.EndpointDescription;
import org.opcfoundation.ua.core.ResponseHeader;
import org.opcfoundation.ua.core.SignatureData;
import org.opcfoundation.ua.core.SignedSoftwareCertificate;


public class CreateSessionResponse extends Object implements ServiceResponse {

	public static final NodeId ID = Identifiers.CreateSessionResponse;
	public static final NodeId BINARY = Identifiers.CreateSessionResponse_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.CreateSessionResponse_Encoding_DefaultXml;	
	
    protected ResponseHeader ResponseHeader;
    protected NodeId SessionId;
    protected NodeId AuthenticationToken;
    protected Double RevisedSessionTimeout;
    protected byte[] ServerNonce;
    protected byte[] ServerCertificate;
    protected EndpointDescription[] ServerEndpoints;
    protected SignedSoftwareCertificate[] ServerSoftwareCertificates;
    protected SignatureData ServerSignature;
    protected UnsignedInteger MaxRequestMessageSize;
    
    public CreateSessionResponse() {}
    
    public CreateSessionResponse(ResponseHeader ResponseHeader, NodeId SessionId, NodeId AuthenticationToken, Double RevisedSessionTimeout, byte[] ServerNonce, byte[] ServerCertificate, EndpointDescription[] ServerEndpoints, SignedSoftwareCertificate[] ServerSoftwareCertificates, SignatureData ServerSignature, UnsignedInteger MaxRequestMessageSize)
    {
        this.ResponseHeader = ResponseHeader;
        this.SessionId = SessionId;
        this.AuthenticationToken = AuthenticationToken;
        this.RevisedSessionTimeout = RevisedSessionTimeout;
        this.ServerNonce = ServerNonce;
        this.ServerCertificate = ServerCertificate;
        this.ServerEndpoints = ServerEndpoints;
        this.ServerSoftwareCertificates = ServerSoftwareCertificates;
        this.ServerSignature = ServerSignature;
        this.MaxRequestMessageSize = MaxRequestMessageSize;
    }
    
    public ResponseHeader getResponseHeader()
    {
        return ResponseHeader;
    }
    
    public void setResponseHeader(ResponseHeader ResponseHeader)
    {
        this.ResponseHeader = ResponseHeader;
    }
    
    public NodeId getSessionId()
    {
        return SessionId;
    }
    
    public void setSessionId(NodeId SessionId)
    {
        this.SessionId = SessionId;
    }
    
    public NodeId getAuthenticationToken()
    {
        return AuthenticationToken;
    }
    
    public void setAuthenticationToken(NodeId AuthenticationToken)
    {
        this.AuthenticationToken = AuthenticationToken;
    }
    
    public Double getRevisedSessionTimeout()
    {
        return RevisedSessionTimeout;
    }
    
    public void setRevisedSessionTimeout(Double RevisedSessionTimeout)
    {
        this.RevisedSessionTimeout = RevisedSessionTimeout;
    }
    
    public byte[] getServerNonce()
    {
        return ServerNonce;
    }
    
    public void setServerNonce(byte[] ServerNonce)
    {
        this.ServerNonce = ServerNonce;
    }
    
    public byte[] getServerCertificate()
    {
        return ServerCertificate;
    }
    
    public void setServerCertificate(byte[] ServerCertificate)
    {
        this.ServerCertificate = ServerCertificate;
    }
    
    public EndpointDescription[] getServerEndpoints()
    {
        return ServerEndpoints;
    }
    
    public void setServerEndpoints(EndpointDescription[] ServerEndpoints)
    {
        this.ServerEndpoints = ServerEndpoints;
    }
    
    public SignedSoftwareCertificate[] getServerSoftwareCertificates()
    {
        return ServerSoftwareCertificates;
    }
    
    public void setServerSoftwareCertificates(SignedSoftwareCertificate[] ServerSoftwareCertificates)
    {
        this.ServerSoftwareCertificates = ServerSoftwareCertificates;
    }
    
    public SignatureData getServerSignature()
    {
        return ServerSignature;
    }
    
    public void setServerSignature(SignatureData ServerSignature)
    {
        this.ServerSignature = ServerSignature;
    }
    
    public UnsignedInteger getMaxRequestMessageSize()
    {
        return MaxRequestMessageSize;
    }
    
    public void setMaxRequestMessageSize(UnsignedInteger MaxRequestMessageSize)
    {
        this.MaxRequestMessageSize = MaxRequestMessageSize;
    }
    
    /**
      * Deep clone
      *
      * @return cloned CreateSessionResponse
      */
    public CreateSessionResponse clone()
    {
        CreateSessionResponse result = new CreateSessionResponse();
        result.ResponseHeader = ResponseHeader==null ? null : ResponseHeader.clone();
        result.SessionId = SessionId;
        result.AuthenticationToken = AuthenticationToken;
        result.RevisedSessionTimeout = RevisedSessionTimeout;
        result.ServerNonce = ServerNonce;
        result.ServerCertificate = ServerCertificate;
        if (ServerEndpoints!=null) {
            result.ServerEndpoints = new EndpointDescription[ServerEndpoints.length];
            for (int i=0; i<ServerEndpoints.length; i++)
                result.ServerEndpoints[i] = ServerEndpoints[i].clone();
        }
        if (ServerSoftwareCertificates!=null) {
            result.ServerSoftwareCertificates = new SignedSoftwareCertificate[ServerSoftwareCertificates.length];
            for (int i=0; i<ServerSoftwareCertificates.length; i++)
                result.ServerSoftwareCertificates[i] = ServerSoftwareCertificates[i].clone();
        }
        result.ServerSignature = ServerSignature==null ? null : ServerSignature.clone();
        result.MaxRequestMessageSize = MaxRequestMessageSize;
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
