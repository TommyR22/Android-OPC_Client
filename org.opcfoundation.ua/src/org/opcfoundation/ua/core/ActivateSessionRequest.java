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
import org.opcfoundation.ua.builtintypes.ExtensionObject;
import org.opcfoundation.ua.core.RequestHeader;
import org.opcfoundation.ua.core.SignatureData;
import org.opcfoundation.ua.core.SignedSoftwareCertificate;


public class ActivateSessionRequest extends Object implements ServiceRequest {

	public static final NodeId ID = Identifiers.ActivateSessionRequest;
	public static final NodeId BINARY = Identifiers.ActivateSessionRequest_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ActivateSessionRequest_Encoding_DefaultXml;	
	
    protected RequestHeader RequestHeader;
    protected SignatureData ClientSignature;
    protected SignedSoftwareCertificate[] ClientSoftwareCertificates;
    protected String[] LocaleIds;
    protected ExtensionObject UserIdentityToken;
    protected SignatureData UserTokenSignature;
    
    public ActivateSessionRequest() {}
    
    public ActivateSessionRequest(RequestHeader RequestHeader, SignatureData ClientSignature, SignedSoftwareCertificate[] ClientSoftwareCertificates, String[] LocaleIds, ExtensionObject UserIdentityToken, SignatureData UserTokenSignature)
    {
        this.RequestHeader = RequestHeader;
        this.ClientSignature = ClientSignature;
        this.ClientSoftwareCertificates = ClientSoftwareCertificates;
        this.LocaleIds = LocaleIds;
        this.UserIdentityToken = UserIdentityToken;
        this.UserTokenSignature = UserTokenSignature;
    }
    
    public RequestHeader getRequestHeader()
    {
        return RequestHeader;
    }
    
    public void setRequestHeader(RequestHeader RequestHeader)
    {
        this.RequestHeader = RequestHeader;
    }
    
    public SignatureData getClientSignature()
    {
        return ClientSignature;
    }
    
    public void setClientSignature(SignatureData ClientSignature)
    {
        this.ClientSignature = ClientSignature;
    }
    
    public SignedSoftwareCertificate[] getClientSoftwareCertificates()
    {
        return ClientSoftwareCertificates;
    }
    
    public void setClientSoftwareCertificates(SignedSoftwareCertificate[] ClientSoftwareCertificates)
    {
        this.ClientSoftwareCertificates = ClientSoftwareCertificates;
    }
    
    public String[] getLocaleIds()
    {
        return LocaleIds;
    }
    
    public void setLocaleIds(String[] LocaleIds)
    {
        this.LocaleIds = LocaleIds;
    }
    
    public ExtensionObject getUserIdentityToken()
    {
        return UserIdentityToken;
    }
    
    public void setUserIdentityToken(ExtensionObject UserIdentityToken)
    {
        this.UserIdentityToken = UserIdentityToken;
    }
    
    public SignatureData getUserTokenSignature()
    {
        return UserTokenSignature;
    }
    
    public void setUserTokenSignature(SignatureData UserTokenSignature)
    {
        this.UserTokenSignature = UserTokenSignature;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ActivateSessionRequest
      */
    public ActivateSessionRequest clone()
    {
        ActivateSessionRequest result = new ActivateSessionRequest();
        result.RequestHeader = RequestHeader==null ? null : RequestHeader.clone();
        result.ClientSignature = ClientSignature==null ? null : ClientSignature.clone();
        if (ClientSoftwareCertificates!=null) {
            result.ClientSoftwareCertificates = new SignedSoftwareCertificate[ClientSoftwareCertificates.length];
            for (int i=0; i<ClientSoftwareCertificates.length; i++)
                result.ClientSoftwareCertificates[i] = ClientSoftwareCertificates[i].clone();
        }
        result.LocaleIds = LocaleIds==null ? null : LocaleIds.clone();
        result.UserIdentityToken = UserIdentityToken;
        result.UserTokenSignature = UserTokenSignature==null ? null : UserTokenSignature.clone();
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
