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



public class EnumValueType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.EnumValueType;
	public static final NodeId BINARY = Identifiers.EnumValueType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.EnumValueType_Encoding_DefaultXml;	
	
    protected Long Value;
    protected LocalizedText DisplayName;
    protected LocalizedText Description;
    
    public EnumValueType() {}
    
    public EnumValueType(Long Value, LocalizedText DisplayName, LocalizedText Description)
    {
        this.Value = Value;
        this.DisplayName = DisplayName;
        this.Description = Description;
    }
    
    public Long getValue()
    {
        return Value;
    }
    
    public void setValue(Long Value)
    {
        this.Value = Value;
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
    
    /**
      * Deep clone
      *
      * @return cloned EnumValueType
      */
    public EnumValueType clone()
    {
        EnumValueType result = new EnumValueType();
        result.Value = Value;
        result.DisplayName = DisplayName;
        result.Description = Description;
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
		return "EnumValueType: "+ObjectUtils.printFieldsDeep(this);
	}

}
