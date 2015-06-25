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

import org.opcfoundation.ua.builtintypes.Structure;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.core.MessageSecurityMode;



public class SessionSecurityDiagnosticsDataType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.SessionSecurityDiagnosticsDataType;
	public static final NodeId BINARY = Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultXml;	
	
    protected NodeId SessionId;
    protected String ClientUserIdOfSession;
    protected String[] ClientUserIdHistory;
    protected String AuthenticationMechanism;
    protected String Encoding;
    protected String TransportProtocol;
    protected MessageSecurityMode SecurityMode;
    protected String SecurityPolicyUri;
    protected byte[] ClientCertificate;
    
    public SessionSecurityDiagnosticsDataType() {}
    
    public SessionSecurityDiagnosticsDataType(NodeId SessionId, String ClientUserIdOfSession, String[] ClientUserIdHistory, String AuthenticationMechanism, String Encoding, String TransportProtocol, MessageSecurityMode SecurityMode, String SecurityPolicyUri, byte[] ClientCertificate)
    {
        this.SessionId = SessionId;
        this.ClientUserIdOfSession = ClientUserIdOfSession;
        this.ClientUserIdHistory = ClientUserIdHistory;
        this.AuthenticationMechanism = AuthenticationMechanism;
        this.Encoding = Encoding;
        this.TransportProtocol = TransportProtocol;
        this.SecurityMode = SecurityMode;
        this.SecurityPolicyUri = SecurityPolicyUri;
        this.ClientCertificate = ClientCertificate;
    }
    
    public NodeId getSessionId()
    {
        return SessionId;
    }
    
    public void setSessionId(NodeId SessionId)
    {
        this.SessionId = SessionId;
    }
    
    public String getClientUserIdOfSession()
    {
        return ClientUserIdOfSession;
    }
    
    public void setClientUserIdOfSession(String ClientUserIdOfSession)
    {
        this.ClientUserIdOfSession = ClientUserIdOfSession;
    }
    
    public String[] getClientUserIdHistory()
    {
        return ClientUserIdHistory;
    }
    
    public void setClientUserIdHistory(String[] ClientUserIdHistory)
    {
        this.ClientUserIdHistory = ClientUserIdHistory;
    }
    
    public String getAuthenticationMechanism()
    {
        return AuthenticationMechanism;
    }
    
    public void setAuthenticationMechanism(String AuthenticationMechanism)
    {
        this.AuthenticationMechanism = AuthenticationMechanism;
    }
    
    public String getEncoding()
    {
        return Encoding;
    }
    
    public void setEncoding(String Encoding)
    {
        this.Encoding = Encoding;
    }
    
    public String getTransportProtocol()
    {
        return TransportProtocol;
    }
    
    public void setTransportProtocol(String TransportProtocol)
    {
        this.TransportProtocol = TransportProtocol;
    }
    
    public MessageSecurityMode getSecurityMode()
    {
        return SecurityMode;
    }
    
    public void setSecurityMode(MessageSecurityMode SecurityMode)
    {
        this.SecurityMode = SecurityMode;
    }
    
    public String getSecurityPolicyUri()
    {
        return SecurityPolicyUri;
    }
    
    public void setSecurityPolicyUri(String SecurityPolicyUri)
    {
        this.SecurityPolicyUri = SecurityPolicyUri;
    }
    
    public byte[] getClientCertificate()
    {
        return ClientCertificate;
    }
    
    public void setClientCertificate(byte[] ClientCertificate)
    {
        this.ClientCertificate = ClientCertificate;
    }
    
    /**
      * Deep clone
      *
      * @return cloned SessionSecurityDiagnosticsDataType
      */
    public SessionSecurityDiagnosticsDataType clone()
    {
        SessionSecurityDiagnosticsDataType result = new SessionSecurityDiagnosticsDataType();
        result.SessionId = SessionId;
        result.ClientUserIdOfSession = ClientUserIdOfSession;
        result.ClientUserIdHistory = ClientUserIdHistory==null ? null : ClientUserIdHistory.clone();
        result.AuthenticationMechanism = AuthenticationMechanism;
        result.Encoding = Encoding;
        result.TransportProtocol = TransportProtocol;
        result.SecurityMode = SecurityMode;
        result.SecurityPolicyUri = SecurityPolicyUri;
        result.ClientCertificate = ClientCertificate;
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
		return "SessionSecurityDiagnosticsDataType: "+ObjectUtils.printFieldsDeep(this);
	}

}
