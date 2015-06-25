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
import org.opcfoundation.ua.builtintypes.ExpandedNodeId;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.core.NodeClass;



public class ReferenceDescription extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ReferenceDescription;
	public static final NodeId BINARY = Identifiers.ReferenceDescription_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ReferenceDescription_Encoding_DefaultXml;	
	
    protected NodeId ReferenceTypeId;
    protected Boolean IsForward;
    protected ExpandedNodeId NodeId;
    protected QualifiedName BrowseName;
    protected LocalizedText DisplayName;
    protected NodeClass NodeClass;
    protected ExpandedNodeId TypeDefinition;
    
    public ReferenceDescription() {}
    
    public ReferenceDescription(NodeId ReferenceTypeId, Boolean IsForward, ExpandedNodeId NodeId, QualifiedName BrowseName, LocalizedText DisplayName, NodeClass NodeClass, ExpandedNodeId TypeDefinition)
    {
        this.ReferenceTypeId = ReferenceTypeId;
        this.IsForward = IsForward;
        this.NodeId = NodeId;
        this.BrowseName = BrowseName;
        this.DisplayName = DisplayName;
        this.NodeClass = NodeClass;
        this.TypeDefinition = TypeDefinition;
    }
    
    public NodeId getReferenceTypeId()
    {
        return ReferenceTypeId;
    }
    
    public void setReferenceTypeId(NodeId ReferenceTypeId)
    {
        this.ReferenceTypeId = ReferenceTypeId;
    }
    
    public Boolean getIsForward()
    {
        return IsForward;
    }
    
    public void setIsForward(Boolean IsForward)
    {
        this.IsForward = IsForward;
    }
    
    public ExpandedNodeId getNodeId()
    {
        return NodeId;
    }
    
    public void setNodeId(ExpandedNodeId NodeId)
    {
        this.NodeId = NodeId;
    }
    
    public QualifiedName getBrowseName()
    {
        return BrowseName;
    }
    
    public void setBrowseName(QualifiedName BrowseName)
    {
        this.BrowseName = BrowseName;
    }
    
    public LocalizedText getDisplayName()
    {
        return DisplayName;
    }
    
    public void setDisplayName(LocalizedText DisplayName)
    {
        this.DisplayName = DisplayName;
    }
    
    public NodeClass getNodeClass()
    {
        return NodeClass;
    }
    
    public void setNodeClass(NodeClass NodeClass)
    {
        this.NodeClass = NodeClass;
    }
    
    public ExpandedNodeId getTypeDefinition()
    {
        return TypeDefinition;
    }
    
    public void setTypeDefinition(ExpandedNodeId TypeDefinition)
    {
        this.TypeDefinition = TypeDefinition;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ReferenceDescription
      */
    public ReferenceDescription clone()
    {
        ReferenceDescription result = new ReferenceDescription();
        result.ReferenceTypeId = ReferenceTypeId;
        result.IsForward = IsForward;
        result.NodeId = NodeId;
        result.BrowseName = BrowseName;
        result.DisplayName = DisplayName;
        result.NodeClass = NodeClass;
        result.TypeDefinition = TypeDefinition;
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
		return "ReferenceDescription: "+ObjectUtils.printFieldsDeep(this);
	}

}
