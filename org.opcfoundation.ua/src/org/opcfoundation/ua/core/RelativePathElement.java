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
import org.opcfoundation.ua.builtintypes.QualifiedName;



public class RelativePathElement extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.RelativePathElement;
	public static final NodeId BINARY = Identifiers.RelativePathElement_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.RelativePathElement_Encoding_DefaultXml;	
	
    protected NodeId ReferenceTypeId;
    protected Boolean IsInverse;
    protected Boolean IncludeSubtypes;
    protected QualifiedName TargetName;
    
    public RelativePathElement() {}
    
    public RelativePathElement(NodeId ReferenceTypeId, Boolean IsInverse, Boolean IncludeSubtypes, QualifiedName TargetName)
    {
        this.ReferenceTypeId = ReferenceTypeId;
        this.IsInverse = IsInverse;
        this.IncludeSubtypes = IncludeSubtypes;
        this.TargetName = TargetName;
    }
    
    public NodeId getReferenceTypeId()
    {
        return ReferenceTypeId;
    }
    
    public void setReferenceTypeId(NodeId ReferenceTypeId)
    {
        this.ReferenceTypeId = ReferenceTypeId;
    }
    
    public Boolean getIsInverse()
    {
        return IsInverse;
    }
    
    public void setIsInverse(Boolean IsInverse)
    {
        this.IsInverse = IsInverse;
    }
    
    public Boolean getIncludeSubtypes()
    {
        return IncludeSubtypes;
    }
    
    public void setIncludeSubtypes(Boolean IncludeSubtypes)
    {
        this.IncludeSubtypes = IncludeSubtypes;
    }
    
    public QualifiedName getTargetName()
    {
        return TargetName;
    }
    
    public void setTargetName(QualifiedName TargetName)
    {
        this.TargetName = TargetName;
    }
    
    /**
      * Deep clone
      *
      * @return cloned RelativePathElement
      */
    public RelativePathElement clone()
    {
        RelativePathElement result = new RelativePathElement();
        result.ReferenceTypeId = ReferenceTypeId;
        result.IsInverse = IsInverse;
        result.IncludeSubtypes = IncludeSubtypes;
        result.TargetName = TargetName;
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
		return "RelativePathElement: "+ObjectUtils.printFieldsDeep(this);
	}

}
