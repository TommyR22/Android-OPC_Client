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
import org.opcfoundation.ua.builtintypes.UnsignedByte;



public class ModelChangeStructureDataType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ModelChangeStructureDataType;
	public static final NodeId BINARY = Identifiers.ModelChangeStructureDataType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ModelChangeStructureDataType_Encoding_DefaultXml;	
	
    protected NodeId Affected;
    protected NodeId AffectedType;
    protected UnsignedByte Verb;
    
    public ModelChangeStructureDataType() {}
    
    public ModelChangeStructureDataType(NodeId Affected, NodeId AffectedType, UnsignedByte Verb)
    {
        this.Affected = Affected;
        this.AffectedType = AffectedType;
        this.Verb = Verb;
    }
    
    public NodeId getAffected()
    {
        return Affected;
    }
    
    public void setAffected(NodeId Affected)
    {
        this.Affected = Affected;
    }
    
    public NodeId getAffectedType()
    {
        return AffectedType;
    }
    
    public void setAffectedType(NodeId AffectedType)
    {
        this.AffectedType = AffectedType;
    }
    
    public UnsignedByte getVerb()
    {
        return Verb;
    }
    
    public void setVerb(UnsignedByte Verb)
    {
        this.Verb = Verb;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ModelChangeStructureDataType
      */
    public ModelChangeStructureDataType clone()
    {
        ModelChangeStructureDataType result = new ModelChangeStructureDataType();
        result.Affected = Affected;
        result.AffectedType = AffectedType;
        result.Verb = Verb;
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
		return "ModelChangeStructureDataType: "+ObjectUtils.printFieldsDeep(this);
	}

}
