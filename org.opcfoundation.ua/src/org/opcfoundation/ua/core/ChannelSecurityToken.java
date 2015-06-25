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
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;



public class ChannelSecurityToken extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ChannelSecurityToken;
	public static final NodeId BINARY = Identifiers.ChannelSecurityToken_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ChannelSecurityToken_Encoding_DefaultXml;	
	
    protected UnsignedInteger ChannelId;
    protected UnsignedInteger TokenId;
    protected DateTime CreatedAt;
    protected UnsignedInteger RevisedLifetime;
    
    public ChannelSecurityToken() {}
    
    public ChannelSecurityToken(UnsignedInteger ChannelId, UnsignedInteger TokenId, DateTime CreatedAt, UnsignedInteger RevisedLifetime)
    {
        this.ChannelId = ChannelId;
        this.TokenId = TokenId;
        this.CreatedAt = CreatedAt;
        this.RevisedLifetime = RevisedLifetime;
    }
    
    public UnsignedInteger getChannelId()
    {
        return ChannelId;
    }
    
    public void setChannelId(UnsignedInteger ChannelId)
    {
        this.ChannelId = ChannelId;
    }
    
    public UnsignedInteger getTokenId()
    {
        return TokenId;
    }
    
    public void setTokenId(UnsignedInteger TokenId)
    {
        this.TokenId = TokenId;
    }
    
    public DateTime getCreatedAt()
    {
        return CreatedAt;
    }
    
    public void setCreatedAt(DateTime CreatedAt)
    {
        this.CreatedAt = CreatedAt;
    }
    
    public UnsignedInteger getRevisedLifetime()
    {
        return RevisedLifetime;
    }
    
    public void setRevisedLifetime(UnsignedInteger RevisedLifetime)
    {
        this.RevisedLifetime = RevisedLifetime;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ChannelSecurityToken
      */
    public ChannelSecurityToken clone()
    {
        ChannelSecurityToken result = new ChannelSecurityToken();
        result.ChannelId = ChannelId;
        result.TokenId = TokenId;
        result.CreatedAt = CreatedAt;
        result.RevisedLifetime = RevisedLifetime;
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
		return "ChannelSecurityToken: "+ObjectUtils.printFieldsDeep(this);
	}

}
