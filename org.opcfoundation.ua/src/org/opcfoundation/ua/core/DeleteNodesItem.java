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



public class DeleteNodesItem extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.DeleteNodesItem;
	public static final NodeId BINARY = Identifiers.DeleteNodesItem_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.DeleteNodesItem_Encoding_DefaultXml;	
	
    protected NodeId NodeId;
    protected Boolean DeleteTargetReferences;
    
    public DeleteNodesItem() {}
    
    public DeleteNodesItem(NodeId NodeId, Boolean DeleteTargetReferences)
    {
        this.NodeId = NodeId;
        this.DeleteTargetReferences = DeleteTargetReferences;
    }
    
    public NodeId getNodeId()
    {
        return NodeId;
    }
    
    public void setNodeId(NodeId NodeId)
    {
        this.NodeId = NodeId;
    }
    
    public Boolean getDeleteTargetReferences()
    {
        return DeleteTargetReferences;
    }
    
    public void setDeleteTargetReferences(Boolean DeleteTargetReferences)
    {
        this.DeleteTargetReferences = DeleteTargetReferences;
    }
    
    /**
      * Deep clone
      *
      * @return cloned DeleteNodesItem
      */
    public DeleteNodesItem clone()
    {
        DeleteNodesItem result = new DeleteNodesItem();
        result.NodeId = NodeId;
        result.DeleteTargetReferences = DeleteTargetReferences;
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
		return "DeleteNodesItem: "+ObjectUtils.printFieldsDeep(this);
	}

}
