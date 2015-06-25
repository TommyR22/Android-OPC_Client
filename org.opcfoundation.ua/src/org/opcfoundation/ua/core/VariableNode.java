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

import org.opcfoundation.ua.builtintypes.ServiceResult;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.Structure;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.builtintypes.Variant;
import org.opcfoundation.ua.core.Attributes;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.builtintypes.Variant;
import org.opcfoundation.ua.core.InstanceNode;
import org.opcfoundation.ua.core.NodeClass;
import org.opcfoundation.ua.core.ReferenceNode;



public class VariableNode extends InstanceNode implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.VariableNode;
	public static final NodeId BINARY = Identifiers.VariableNode_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.VariableNode_Encoding_DefaultXml;	
	
    protected Variant Value;
    protected NodeId DataType;
    protected Integer ValueRank;
    protected UnsignedInteger[] ArrayDimensions;
    protected UnsignedByte AccessLevel;
    protected UnsignedByte UserAccessLevel;
    protected Double MinimumSamplingInterval;
    protected Boolean Historizing;
    
    public VariableNode() {}
    
    public VariableNode(NodeId NodeId, NodeClass NodeClass, QualifiedName BrowseName, LocalizedText DisplayName, LocalizedText Description, UnsignedInteger WriteMask, UnsignedInteger UserWriteMask, ReferenceNode[] References, Variant Value, NodeId DataType, Integer ValueRank, UnsignedInteger[] ArrayDimensions, UnsignedByte AccessLevel, UnsignedByte UserAccessLevel, Double MinimumSamplingInterval, Boolean Historizing)
    {
        super(NodeId, NodeClass, BrowseName, DisplayName, Description, WriteMask, UserWriteMask, References);
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
      * @return cloned VariableNode
      */
    public VariableNode clone()
    {
        VariableNode result = new VariableNode();
        result.NodeId = NodeId;
        result.NodeClass = NodeClass;
        result.BrowseName = BrowseName;
        result.DisplayName = DisplayName;
        result.Description = Description;
        result.WriteMask = WriteMask;
        result.UserWriteMask = UserWriteMask;
        if (References!=null) {
            result.References = new ReferenceNode[References.length];
            for (int i=0; i<References.length; i++)
                result.References[i] = References[i].clone();
        }
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
    
 
   
    public boolean supportsAttribute(UnsignedInteger attributeId) {
    	
    	if (attributeId.equals(Attributes.Value) || 
    		attributeId.equals(Attributes.ValueRank) ||
    		attributeId.equals(Attributes.DataType) ||
    		attributeId.equals(Attributes.AccessLevel) ||
    		attributeId.equals(Attributes.UserAccessLevel) ||
    		attributeId.equals(Attributes.MinimumSamplingInterval) ||
    		attributeId.equals(Attributes.Historizing)) {
    			
    			return true;
    	}
    		
    	return super.supportsAttribute(attributeId);
    }
    
    public Variant read(UnsignedInteger attributeId) {
    	if (attributeId.equals(Attributes.DataType))                return new Variant(DataType);
    	if (attributeId.equals(Attributes.AccessLevel))             return new Variant(AccessLevel);
    	if (attributeId.equals(Attributes.UserAccessLevel))         return new Variant(UserAccessLevel);
    	if (attributeId.equals(Attributes.MinimumSamplingInterval)) return new Variant(MinimumSamplingInterval);
    	if(attributeId.equals(Attributes.Historizing))				return new Variant(Historizing);
    	// values are copied when the are written so then can be safely returned.
    	if (attributeId.equals(Attributes.Value)) {
    		// assign a default value on first time read.
    		if (Value.getValue() == null) {
    			//???
    			Variant valuevariant = new Variant(null);
    			//Value.set(org.opcfoundation.ua.builtintypes.DataTypes.getDefaultValue(DataType, ValueRank));
    			Value = valuevariant;

    			// update status and timestamp on write.
    			//statusCode = new StatusCode(StatusCode.SEVERITY_GOOD);
    			//timestamp = new DateTime(GregorianCalendar.getInstance());
    		}

    		return new Variant(Value.getValue());
    	}

    	// array size always comes from the value.
    	if (attributeId.equals(Attributes.ValueRank)) {
    		
    		//TODO check this...
    		/*try {
    			java.util.ArrayList array = (java.util.ArrayList)Value.getValue();
    			
    			if (array != null) {
    				return new Variant(array.size()); // TODO: check this out!!!
    			}
    			
    		} catch (ClassCastException cce) {
    			if (Value.getValue() != null) {
    				return new Variant(-1);
    			}
    		}*/
    		
    		return new Variant(ValueRank);
    	}

    	return super.read(attributeId);
    }
    
   	public ServiceResult write(UnsignedInteger attributeId, Object value){
    	
    	if (attributeId.equals(Attributes.AccessLevel)) {
     		AccessLevel = (UnsignedByte)value;
     		return new ServiceResult(StatusCode.GOOD);
     	}
    	if (attributeId.equals(Attributes.UserAccessLevel)) {
     		UserAccessLevel = (UnsignedByte)value;
     		return new ServiceResult(StatusCode.GOOD);
     	}
    	if (attributeId.equals(Attributes.MinimumSamplingInterval)) {
     		MinimumSamplingInterval = (Double)value;
     		return new ServiceResult(StatusCode.GOOD);
     	}
    	if (attributeId.equals(Attributes.Historizing)) {
     		Historizing = (Boolean)value;
     		return new ServiceResult(StatusCode.GOOD);
     	}
     	if (attributeId.equals(Attributes.Value)) {
     		Value = new Variant(value);
     		return new ServiceResult(StatusCode.GOOD);
     	}
     	
     	if (attributeId.equals(Attributes.DataType)) {
     		//check that value is is the correct datatype
     		NodeId dataType = (NodeId) value;
     		if(dataType != DataType){
     			//TODO in this case we should put default value to Variant's value
     			Value = new Variant(null);
     		}
     		
     		DataType = dataType;
     		return new ServiceResult(StatusCode.GOOD);
     	}
     	
     	if(attributeId.equals(Attributes.ValueRank)){
     		int valueRank = (Integer)value;
     		
     		//check valuerank 
     		if(valueRank != ValueRank){
     			//TODO in this case we should put default value to Variant's value
     			Value = new Variant(null);
     		}
     		
     		ValueRank = valueRank;
     		return new ServiceResult(StatusCode.GOOD);
     	}
     	
    	if(attributeId.equals(Attributes.ArrayDimensions) ){
        	ArrayDimensions = (UnsignedInteger[])value;

        	// ensure number of dimensions is correct.
        	if (ArrayDimensions.length > 0)
        	{
        		if (ValueRank != ArrayDimensions.length){  
            	
        			ValueRank = ArrayDimensions.length;   
        			//TODO in this case we should put default value to Variant's value
        			Value = new Variant(null);
        		}
        	}
        	return new ServiceResult(StatusCode.GOOD);
    	}
     	
     	return super.write(attributeId, value);
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
		return "VariableNode: "+ObjectUtils.printFieldsDeep(this);
	}

}
