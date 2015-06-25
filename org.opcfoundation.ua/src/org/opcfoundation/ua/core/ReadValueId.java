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
import org.opcfoundation.ua.builtintypes.ServiceResult;
import org.opcfoundation.ua.utils.AttributesUtil;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;



public class ReadValueId extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ReadValueId;
	public static final NodeId BINARY = Identifiers.ReadValueId_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ReadValueId_Encoding_DefaultXml;	
	
    protected NodeId NodeId;
    protected UnsignedInteger AttributeId;
    protected String IndexRange;
    protected QualifiedName DataEncoding;
    
    public ReadValueId() {}
    
    public ReadValueId(NodeId NodeId, UnsignedInteger AttributeId, String IndexRange, QualifiedName DataEncoding)
    {
        this.NodeId = NodeId;
        this.AttributeId = AttributeId;
        this.IndexRange = IndexRange;
        this.DataEncoding = DataEncoding;
    }
    
    public NodeId getNodeId()
    {
        return NodeId;
    }
    
    public void setNodeId(NodeId NodeId)
    {
        this.NodeId = NodeId;
    }
    
    public UnsignedInteger getAttributeId()
    {
        return AttributeId;
    }
    
    public void setAttributeId(UnsignedInteger AttributeId)
    {
        this.AttributeId = AttributeId;
    }
    
    public String getIndexRange()
    {
        return IndexRange;
    }
    
    public void setIndexRange(String IndexRange)
    {
        this.IndexRange = IndexRange;
    }
    
    public QualifiedName getDataEncoding()
    {
        return DataEncoding;
    }
    
    public void setDataEncoding(QualifiedName DataEncoding)
    {
        this.DataEncoding = DataEncoding;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ReadValueId
      */
    public ReadValueId clone()
    {
        ReadValueId result = new ReadValueId();
        result.NodeId = NodeId;
        result.AttributeId = AttributeId;
        result.IndexRange = IndexRange;
        result.DataEncoding = DataEncoding;
        return result;
    }
    
 

	// Added by Mikko 12.12.08, needed in masternodemanager / read
    public static ServiceResult validate(ReadValueId valueId) {
    	// Check for null structure.
    	if (valueId == null) {
    		return new ServiceResult(StatusCodes.Bad_StructureMissing);
    	}

    	// Null node ids are always invalid.
    	if (valueId.getNodeId() == null) {
    		return new ServiceResult(StatusCodes.Bad_NodeIdInvalid);
    	}

    	// must be a legitimate attribute value
    	if (!AttributesUtil.isValid(valueId.getAttributeId())) {
    		return new ServiceResult(StatusCodes.Bad_AttributeIdInvalid);
    	}
    	
    	// data encoding AND index range is only valid for value attributes
    	if (!valueId.getAttributeId().equals(Attributes.Value)) {
    		if (!QualifiedName.isNull(valueId.getDataEncoding())) {
    			return new ServiceResult(StatusCodes.Bad_DataEncodingInvalid);
    		}
    		if(!(valueId.getIndexRange() == null || valueId.getIndexRange().isEmpty())){
    			return new ServiceResult(StatusCodes.Bad_IndexRangeInvalid);
    		}
    	}

    	// Parse the index range if specified.
    	/*if (valueId.getIndexRange() != null && valueId.getIndexRange() != "") {
    		try {
    			valueId.setIndexRange(valueId.getIndexRange());
    		} catch (Exception e) {
    			return new ServiceResult(StatusCodes.Bad_IndexRangeInvalid, e);
    			//return ServiceResult.create(e, StatusCodes.BadIndexRangeInvalid);
    		}
    	} else {
    		//NumericRange range = NumericRange.getEmpty();
    		valueId.setIndexRange("");
    	}*/

    	// passed basic validation
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
		return "ReadValueId: "+ObjectUtils.printFieldsDeep(this);
	}

}
