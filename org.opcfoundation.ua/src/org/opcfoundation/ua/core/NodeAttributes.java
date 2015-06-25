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
import org.opcfoundation.ua.builtintypes.UnsignedInteger;



public class NodeAttributes extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.NodeAttributes;
	public static final NodeId BINARY = Identifiers.NodeAttributes_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.NodeAttributes_Encoding_DefaultXml;	
	
    protected UnsignedInteger SpecifiedAttributes;
    protected LocalizedText DisplayName;
    protected LocalizedText Description;
    protected UnsignedInteger WriteMask;
    protected UnsignedInteger UserWriteMask;
    
    public NodeAttributes() {}
    
    public NodeAttributes(UnsignedInteger SpecifiedAttributes, LocalizedText DisplayName, LocalizedText Description, UnsignedInteger WriteMask, UnsignedInteger UserWriteMask)
    {
        this.SpecifiedAttributes = SpecifiedAttributes;
        this.DisplayName = DisplayName;
        this.Description = Description;
        this.WriteMask = WriteMask;
        this.UserWriteMask = UserWriteMask;
    }
    
    public UnsignedInteger getSpecifiedAttributes()
    {
        return SpecifiedAttributes;
    }
    
    public void setSpecifiedAttributes(UnsignedInteger SpecifiedAttributes)
    {
        this.SpecifiedAttributes = SpecifiedAttributes;
    }
    
    public LocalizedText getDisplayName()
    {
        return DisplayName;
    }
    
    public void setDisplayName(LocalizedText DisplayName)
    {
        this.DisplayName = DisplayName;
    }
    
    public LocalizedText getDescription()
    {
        return Description;
    }
    
    public void setDescription(LocalizedText Description)
    {
        this.Description = Description;
    }
    
    public UnsignedInteger getWriteMask()
    {
        return WriteMask;
    }
    
    public void setWriteMask(UnsignedInteger WriteMask)
    {
        this.WriteMask = WriteMask;
    }
    
    public UnsignedInteger getUserWriteMask()
    {
        return UserWriteMask;
    }
    
    public void setUserWriteMask(UnsignedInteger UserWriteMask)
    {
        this.UserWriteMask = UserWriteMask;
    }
    
    /**
      * Deep clone
      *
      * @return cloned NodeAttributes
      */
    public NodeAttributes clone()
    {
        NodeAttributes result = new NodeAttributes();
        result.SpecifiedAttributes = SpecifiedAttributes;
        result.DisplayName = DisplayName;
        result.Description = Description;
        result.WriteMask = WriteMask;
        result.UserWriteMask = UserWriteMask;
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
		return "NodeAttributes: "+ObjectUtils.printFieldsDeep(this);
	}

}
