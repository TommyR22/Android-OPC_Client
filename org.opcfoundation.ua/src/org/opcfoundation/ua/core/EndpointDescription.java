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
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.core.UserTokenPolicy;
import org.opcfoundation.ua.transport.security.SecurityPolicy;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.utils.EndpointUtil;
import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.MessageSecurityMode;
import org.opcfoundation.ua.core.UserTokenPolicy;


/**
 * Endpoint Description
 * 
 * @See {@link EndpointUtil} for utility methods
 */

public class EndpointDescription extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.EndpointDescription;
	public static final NodeId BINARY = Identifiers.EndpointDescription_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.EndpointDescription_Encoding_DefaultXml;	
	
    protected String EndpointUrl;
    protected ApplicationDescription Server;
    protected byte[] ServerCertificate;
    protected MessageSecurityMode SecurityMode;
    protected String SecurityPolicyUri;
    protected UserTokenPolicy[] UserIdentityTokens;
    protected String TransportProfileUri;
    protected UnsignedByte SecurityLevel;
    
    public EndpointDescription() {}
    
    public EndpointDescription(String EndpointUrl, ApplicationDescription Server, byte[] ServerCertificate, MessageSecurityMode SecurityMode, String SecurityPolicyUri, UserTokenPolicy[] UserIdentityTokens, String TransportProfileUri, UnsignedByte SecurityLevel)
    {
        this.EndpointUrl = EndpointUrl;
        this.Server = Server;
        this.ServerCertificate = ServerCertificate;
        this.SecurityMode = SecurityMode;
        this.SecurityPolicyUri = SecurityPolicyUri;
        this.UserIdentityTokens = UserIdentityTokens;
        this.TransportProfileUri = TransportProfileUri;
        this.SecurityLevel = SecurityLevel;
    }
    
    public String getEndpointUrl()
    {
        return EndpointUrl;
    }
    
    public void setEndpointUrl(String EndpointUrl)
    {
        this.EndpointUrl = EndpointUrl;
    }
    
    public ApplicationDescription getServer()
    {
        return Server;
    }
    
    public void setServer(ApplicationDescription Server)
    {
        this.Server = Server;
    }
    
    public byte[] getServerCertificate()
    {
        return ServerCertificate;
    }
    
    public void setServerCertificate(byte[] ServerCertificate)
    {
        this.ServerCertificate = ServerCertificate;
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
    
    public UserTokenPolicy[] getUserIdentityTokens()
    {
        return UserIdentityTokens;
    }
    
    public void setUserIdentityTokens(UserTokenPolicy[] UserIdentityTokens)
    {
        this.UserIdentityTokens = UserIdentityTokens;
    }
    
    public String getTransportProfileUri()
    {
        return TransportProfileUri;
    }
    
    public void setTransportProfileUri(String TransportProfileUri)
    {
        this.TransportProfileUri = TransportProfileUri;
    }
    
    public UnsignedByte getSecurityLevel()
    {
        return SecurityLevel;
    }
    
    public void setSecurityLevel(UnsignedByte SecurityLevel)
    {
        this.SecurityLevel = SecurityLevel;
    }
    
    /**
      * Deep clone
      *
      * @return cloned EndpointDescription
      */
    public EndpointDescription clone()
    {
        EndpointDescription result = new EndpointDescription();
        result.EndpointUrl = EndpointUrl;
        result.Server = Server==null ? null : Server.clone();
        result.ServerCertificate = ServerCertificate;
        result.SecurityMode = SecurityMode;
        result.SecurityPolicyUri = SecurityPolicyUri;
        if (UserIdentityTokens!=null) {
            result.UserIdentityTokens = new UserTokenPolicy[UserIdentityTokens.length];
            for (int i=0; i<UserIdentityTokens.length; i++)
                result.UserIdentityTokens[i] = UserIdentityTokens[i].clone();
        }
        result.TransportProfileUri = TransportProfileUri;
        result.SecurityLevel = SecurityLevel;
        return result;
    }
    

	
	/**
	 * Tests whether the stack and the endpoint supports given token type.
	 * This verifies that the stack knows the encryption algorithms of the
	 * token type. 
	 *  
	 * @param endpoint
	 * @param type
	 * @return true, if token type is supported
	 */
	public boolean supportsUserTokenType(EndpointDescription endpoint, UserTokenType type)
	{
		return findUserTokenPolicy(type) != null;
	}

	/**
	 * Finds UserTokenPolicy of given type that this stack can encrypt
	 * 
	 * @param endpoint
	 * @param type
	 * @return user token policy or null 
	 */
	public UserTokenPolicy findUserTokenPolicy(UserTokenType type)
	{
		if (UserIdentityTokens==null) return null;
		for (UserTokenPolicy p : UserIdentityTokens)
		{
		
			// Ensure the stack knows the policy
			try {
				String securityPolicyUri = p.getSecurityPolicyUri();
				SecurityPolicy.getSecurityPolicy(securityPolicyUri);
			} catch (ServiceResultException e) {
				continue;
			}

			if (p.getTokenType() != type) continue;
		
			return p;
		}
		return null;
	}

    /**
     * Finds the user token policy with the specified id.
     * 
     * @return user token policy or null
     */
    public UserTokenPolicy findUserTokenPolicy(String policyId)
    {
		if (UserIdentityTokens==null) return null;
    	//TODO how to determine right policyId's? Now policyId == Token name
		for (UserTokenPolicy policy : UserIdentityTokens)
			if (policy != null) {
				final String p = policy.getPolicyId();
				if (p != null && p.equals(policyId))
					return policy;
			}
        return null;
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
        return "EndpointDescription: "+ObjectUtils.printFieldsDeep(this);
    }

	public boolean needsCertificate() {
		return getSecurityMode().hasSigning() ||
			EndpointUtil.containsSecureUserTokenPolicy(getUserIdentityTokens());
	}
    
}
