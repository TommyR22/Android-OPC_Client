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
import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.builtintypes.Variant;
import org.opcfoundation.ua.core.NodeAttributes;



public class VariableAttributes extends NodeAttributes implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.VariableAttributes;
	public static final NodeId BINARY = Identifiers.VariableAttributes_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.VariableAttributes_Encoding_DefaultXml;	
	
    protected Variant Value;
    protected NodeId DataType;
    protected Integer ValueRank;
    protected UnsignedInteger[] ArrayDimensions;
    protected UnsignedByte AccessLevel;
    protected UnsignedByte UserAccessLevel;
    protected Double MinimumSamplingInterval;
    protected Boolean Historizing;
    
    public VariableAttributes() {}
    
    public VariableAttributes(UnsignedInteger SpecifiedAttributes, LocalizedText DisplayName, LocalizedText Description, UnsignedInteger WriteMask, UnsignedInteger UserWriteMask, Variant Value, NodeId DataType, Integer ValueRank, UnsignedInteger[] ArrayDimensions, UnsignedByte AccessLevel, UnsignedByte UserAccessLevel, Double MinimumSamplingInterval, Boolean Historizing)
    {
        super(SpecifiedAttributes, DisplayName, Description, WriteMask, UserWriteMask);
        this.Value = Value;
        this.DataType = DataType;
        this.ValueRank = ValueRank;
        this.ArrayDimensions = ArrayDimensions;
        this.AccessLevel = AccessLevel;
        this.UserAccessLevel = UserAccessLevel;
        this.MinimumSamplingInterval = MinimumSamplingInterval;
        this.Historizing = Historizing;
    }
    
    public Variant getValue()
    {
        return Value;
    }
    
    public void setValue(Variant Value)
    {
        this.Value = Value;
    }
    
    public NodeId getDataType()
    {
        return DataType;
    }
    
    public void setDataType(NodeId DataType)
    {
        this.DataType = DataType;
    }
    
    public Integer getValueRank()
    {
        return ValueRank;
    }
    
    public void setValueRank(Integer ValueRank)
    {
        this.ValueRank = ValueRank;
    }
    
    public UnsignedInteger[] getArrayDimensions()
    {
        return ArrayDimensions;
    }
    
    public void setArrayDimensions(UnsignedInteger[] ArrayDimensions)
    {
        this.ArrayDimensions = ArrayDimensions;
    }
    
    public UnsignedByte getAccessLevel()
    {
        return AccessLevel;
    }
    
    public void setAccessLevel(UnsignedByte AccessLevel)
    {
        this.AccessLevel = AccessLevel;
    }
    
    public UnsignedByte getUserAccessLevel()
    {
        return UserAccessLevel;
    }
    
    public void setUserAccessLevel(UnsignedByte UserAccessLevel)
    {
        this.UserAccessLevel = UserAccessLevel;
    }
    
    public Double getMinimumSamplingInterval()
    {
        return MinimumSamplingInterval;
    }
    
    public void setMinimumSamplingInterval(Double MinimumSamplingInterval)
    {
        this.MinimumSamplingInterval = MinimumSamplingInterval;
    }
    
    public Boolean getHistorizing()
    {
        return Historizing;
    }
    
    public void setHistorizing(Boolean Historizing)
    {
        this.Historizing = Historizing;
    }
    
    /**
      * Deep clone
      *
      * @return cloned VariableAttributes
      */
    public VariableAttributes clone()
    {
        VariableAttributes result = new VariableAttributes();
        result.SpecifiedAttributes = SpecifiedAttributes;
        result.DisplayName = DisplayName;
        result.Description = Description;
        result.WriteMask = WriteMask;
        result.UserWriteMask = UserWriteMask;
        result.Value = Value;
        result.DataType = DataType;
        result.ValueRank = ValueRank;
        result.ArrayDimensions = ArrayDimensions==null ? null : ArrayDimensions.clone();
        result.AccessLevel = AccessLevel;
        result.UserAccessLevel = UserAccessLevel;
        result.MinimumSamplingInterval = MinimumSamplingInterval;
        result.Historizing = Historizing;
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
		return "VariableAttributes: "+ObjectUtils.printFieldsDeep(this);
	}

}
