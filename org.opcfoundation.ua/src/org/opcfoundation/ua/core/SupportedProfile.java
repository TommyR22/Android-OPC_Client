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
import org.opcfoundation.ua.core.ComplianceLevel;



public class SupportedProfile extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.SupportedProfile;
	public static final NodeId BINARY = Identifiers.SupportedProfile_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.SupportedProfile_Encoding_DefaultXml;	
	
    protected String OrganizationUri;
    protected String ProfileId;
    protected String ComplianceTool;
    protected DateTime ComplianceDate;
    protected ComplianceLevel ComplianceLevel;
    protected String[] UnsupportedUnitIds;
    
    public SupportedProfile() {}
    
    public SupportedProfile(String OrganizationUri, String ProfileId, String ComplianceTool, DateTime ComplianceDate, ComplianceLevel ComplianceLevel, String[] UnsupportedUnitIds)
    {
        this.OrganizationUri = OrganizationUri;
        this.ProfileId = ProfileId;
        this.ComplianceTool = ComplianceTool;
        this.ComplianceDate = ComplianceDate;
        this.ComplianceLevel = ComplianceLevel;
        this.UnsupportedUnitIds = UnsupportedUnitIds;
    }
    
    public String getOrganizationUri()
    {
        return OrganizationUri;
    }
    
    public void setOrganizationUri(String OrganizationUri)
    {
        this.OrganizationUri = OrganizationUri;
    }
    
    public String getProfileId()
    {
        return ProfileId;
    }
    
    public void setProfileId(String ProfileId)
    {
        this.ProfileId = ProfileId;
    }
    
    public String getComplianceTool()
    {
        return ComplianceTool;
    }
    
    public void setComplianceTool(String ComplianceTool)
    {
        this.ComplianceTool = ComplianceTool;
    }
    
    public DateTime getComplianceDate()
    {
        return ComplianceDate;
    }
    
    public void setComplianceDate(DateTime ComplianceDate)
    {
        this.ComplianceDate = ComplianceDate;
    }
    
    public ComplianceLevel getComplianceLevel()
    {
        return ComplianceLevel;
    }
    
    public void setComplianceLevel(ComplianceLevel ComplianceLevel)
    {
        this.ComplianceLevel = ComplianceLevel;
    }
    
    public String[] getUnsupportedUnitIds()
    {
        return UnsupportedUnitIds;
    }
    
    public void setUnsupportedUnitIds(String[] UnsupportedUnitIds)
    {
        this.UnsupportedUnitIds = UnsupportedUnitIds;
    }
    
    /**
      * Deep clone
      *
      * @return cloned SupportedProfile
      */
    public SupportedProfile clone()
    {
        SupportedProfile result = new SupportedProfile();
        result.OrganizationUri = OrganizationUri;
        result.ProfileId = ProfileId;
        result.ComplianceTool = ComplianceTool;
        result.ComplianceDate = ComplianceDate;
        result.ComplianceLevel = ComplianceLevel;
        result.UnsupportedUnitIds = UnsupportedUnitIds==null ? null : UnsupportedUnitIds.clone();
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
		return "SupportedProfile: "+ObjectUtils.printFieldsDeep(this);
	}

}
