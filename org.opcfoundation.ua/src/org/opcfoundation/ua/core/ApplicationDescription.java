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
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.core.ApplicationType;



public class ApplicationDescription extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ApplicationDescription;
	public static final NodeId BINARY = Identifiers.ApplicationDescription_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ApplicationDescription_Encoding_DefaultXml;	
	
    protected String ApplicationUri;
    protected String ProductUri;
    protected LocalizedText ApplicationName;
    protected ApplicationType ApplicationType;
    protected String GatewayServerUri;
    protected String DiscoveryProfileUri;
    protected String[] DiscoveryUrls;
    
    public ApplicationDescription() {}
    
    public ApplicationDescription(String ApplicationUri, String ProductUri, LocalizedText ApplicationName, ApplicationType ApplicationType, String GatewayServerUri, String DiscoveryProfileUri, String[] DiscoveryUrls)
    {
        this.ApplicationUri = ApplicationUri;
        this.ProductUri = ProductUri;
        this.ApplicationName = ApplicationName;
        this.ApplicationType = ApplicationType;
        this.GatewayServerUri = GatewayServerUri;
        this.DiscoveryProfileUri = DiscoveryProfileUri;
        this.DiscoveryUrls = DiscoveryUrls;
    }
    
    public String getApplicationUri()
    {
        return ApplicationUri;
    }
    
    public void setApplicationUri(String ApplicationUri)
    {
        this.ApplicationUri = ApplicationUri;
    }
    
    public String getProductUri()
    {
        return ProductUri;
    }
    
    public void setProductUri(String ProductUri)
    {
        this.ProductUri = ProductUri;
    }
    
    public LocalizedText getApplicationName()
    {
        return ApplicationName;
    }
    
    public void setApplicationName(LocalizedText ApplicationName)
    {
        this.ApplicationName = ApplicationName;
    }
    
    public ApplicationType getApplicationType()
    {
        return ApplicationType;
    }
    
    public void setApplicationType(ApplicationType ApplicationType)
    {
        this.ApplicationType = ApplicationType;
    }
    
    public String getGatewayServerUri()
    {
        return GatewayServerUri;
    }
    
    public void setGatewayServerUri(String GatewayServerUri)
    {
        this.GatewayServerUri = GatewayServerUri;
    }
    
    public String getDiscoveryProfileUri()
    {
        return DiscoveryProfileUri;
    }
    
    public void setDiscoveryProfileUri(String DiscoveryProfileUri)
    {
        this.DiscoveryProfileUri = DiscoveryProfileUri;
    }
    
    public String[] getDiscoveryUrls()
    {
        return DiscoveryUrls;
    }
    
    public void setDiscoveryUrls(String[] DiscoveryUrls)
    {
        this.DiscoveryUrls = DiscoveryUrls;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ApplicationDescription
      */
    public ApplicationDescription clone()
    {
        ApplicationDescription result = new ApplicationDescription();
        result.ApplicationUri = ApplicationUri;
        result.ProductUri = ProductUri;
        result.ApplicationName = ApplicationName;
        result.ApplicationType = ApplicationType;
        result.GatewayServerUri = GatewayServerUri;
        result.DiscoveryProfileUri = DiscoveryProfileUri;
        result.DiscoveryUrls = DiscoveryUrls==null ? null : DiscoveryUrls.clone();
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
		return "ApplicationDescription: "+ObjectUtils.printFieldsDeep(this);
	}

}
