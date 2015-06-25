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

import java.util.ArrayList;

import org.opcfoundation.ua.builtintypes.ServiceResult;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.Structure;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.Attributes;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.core.ReferenceNode;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.builtintypes.ExpandedNodeId;
import org.opcfoundation.ua.builtintypes.Variant;
import org.opcfoundation.ua.utils.AttributesUtil;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.NodeClass;
import org.opcfoundation.ua.core.ReferenceNode;



public class Node extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.Node;
	public static final NodeId BINARY = Identifiers.Node_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.Node_Encoding_DefaultXml;	

    protected NodeId NodeId;
    protected NodeClass NodeClass;
    protected QualifiedName BrowseName;
    protected LocalizedText DisplayName;
    protected LocalizedText Description;
    protected UnsignedInteger WriteMask;
    protected UnsignedInteger UserWriteMask;
    protected ReferenceNode[] References;
    
    public Node() {}
    
    public Node(NodeId NodeId, NodeClass NodeClass, QualifiedName BrowseName, LocalizedText DisplayName, LocalizedText Description, UnsignedInteger WriteMask, UnsignedInteger UserWriteMask, ReferenceNode[] References)
    {
        this.NodeId = NodeId;
        this.NodeClass = NodeClass;
        this.BrowseName = BrowseName;
        this.DisplayName = DisplayName;
        this.Description = Description;
        this.WriteMask = WriteMask;
        this.UserWriteMask = UserWriteMask;
        this.References = References;
    }
    
    public NodeId getNodeId()
    {
        return NodeId;
    }
    
    public void setNodeId(NodeId NodeId)
    {
        this.NodeId = NodeId;
    }
    
    public NodeClass getNodeClass()
    {
        return NodeClass;
    }
    
    public void setNodeClass(NodeClass NodeClass)
    {
        this.NodeClass = NodeClass;
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
    
    public ReferenceNode[] getReferences()
    {
        return References;
    }
    
    public void setReferences(ReferenceNode[] References)
    {
        this.References = References;
    }
    
    /**
      * Deep clone
      *
      * @return cloned Node
      */
    public Node clone()
    {
        Node result = new Node();
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
        return result;
    }
    
 

	// TODO Move or modify (generator?)
    private Object handle;
    public Object getHandle(){
    	return handle;
    }
    public void setHandle(Object handle){
    	this.handle = handle;
    }
    
    public ExpandedNodeId findProperty(QualifiedName browseName){
    	for(ReferenceNode reference : this.getReferences()){
    		if(!reference.getIsInverse() && reference.getReferenceTypeId().equals(Identifiers.HasProperty)){
    			return reference.getTargetId();
    		}
    	}
    	return null;
    }
    
    public void deleteReferences(ExpandedNodeId nodeId){
    	boolean found = false;
    	
    	ArrayList<ReferenceNode> referencesList = new ArrayList<ReferenceNode>(References.length);
    	
    	for(ReferenceNode reference : References){
    		if(nodeId.equals(reference.getTargetId())){
    			found = true;
    			continue;
    		}
    		
    		referencesList.add(reference);
    	}
    	if(found){
    		ReferenceNode[] nodes = new ReferenceNode[referencesList.size()];
    		for(int i = 0; i < nodes.length; i++){
    			nodes[i] = referencesList.get(i);
    			
    		}
    		References = nodes;
    	}
    	
    }
    
    //Added (again) on 21.1.09 by Mikko
    /**
     * Finds all the targets of the specified reference types.
     * 
     * @param referenceTypeId
     * @param isInverse
     * @return an array of nodes referenced with the given type
     */
    public org.opcfoundation.ua.builtintypes.ExpandedNodeId[] findTargets(NodeId referenceTypeId, boolean isInverse) {
        java.util.ArrayList<org.opcfoundation.ua.builtintypes.ExpandedNodeId> result = new java.util.ArrayList<org.opcfoundation.ua.builtintypes.ExpandedNodeId>();

        if (References!=null)
        for (ReferenceNode reference : References) {
            if (reference.getReferenceTypeId().equals(referenceTypeId) && 
            	org.opcfoundation.ua.utils.ObjectUtils.objectEquals( reference.getIsInverse(), isInverse )) {
            	result.add(reference.getTargetId());
            }
        }

        return result.toArray(new org.opcfoundation.ua.builtintypes.ExpandedNodeId[ result.size() ]);
    }
    
    //TODO added by Mikko 1.12, needed in CorenodeManager
    /**
     * Finds the targets for the first reference of the specified reference type.
     * 
     * @param referenceTypeId
     * @param isInverse
     * @return target reference node or null
     */
    public org.opcfoundation.ua.builtintypes.ExpandedNodeId findTarget(NodeId referenceTypeId, boolean isInverse) {
        if (References==null) return null;
    	for (ReferenceNode reference : References) {
    		if (reference.getReferenceTypeId().equals(referenceTypeId) && 
    			org.opcfoundation.ua.utils.ObjectUtils.objectEquals( reference.getIsInverse(), isInverse )) {
    			return reference.getTargetId();
    		}
    	}

    	//TODO what should we do??
    	return null;
    }
    
    //Added by Mikko 12.12.2008
    public boolean supportsAttribute(UnsignedInteger attributeId) {
    	
    	if (attributeId.equals(Attributes.NodeId)  || 
    		attributeId.equals(Attributes.NodeClass) ||
    		attributeId.equals(Attributes.BrowseName) ||
    		attributeId.equals(Attributes.DisplayName) ||
    		attributeId.equals(Attributes.Description) ||
    		attributeId.equals(Attributes.WriteMask) ||
    		attributeId.equals(Attributes.UserWriteMask) )   {
    			
    			return true;
    	}
    		
    	return false;
    }
    
  	//Added by Mikko 12.12.2008
    public ServiceResult readAttributeValue(UnsignedInteger attributeId, org.opcfoundation.ua.builtintypes.DataValue value) {
    	// Validate attribute id
    	if (!AttributesUtil.isValid(this.getNodeClass(), attributeId)) {
    		return new ServiceResult(StatusCodes.Bad_AttributeIdInvalid);
    	}

    	// Read the attribute value.
    	value.setValue(read(attributeId));
    	//TODO 
    	return new ServiceResult(StatusCode.getFromBits(StatusCode.SEVERITY_GOOD));
    }
    
    protected Variant read(UnsignedInteger attributeId) {
    	if (attributeId.equals(Attributes.NodeId)) {
    		return new Variant(this.getNodeId());
    	} else if (attributeId.equals(Attributes.NodeClass)) {
    		return new Variant(this.getNodeClass().getValue());
    	} else if (attributeId.equals(Attributes.BrowseName)) {
    		return new Variant(this.getBrowseName());
    	} else if (attributeId.equals(Attributes.DisplayName)) {
    		return new Variant(this.getDisplayName());
    	} else if (attributeId.equals(Attributes.Description)) {
    		return new Variant(this.getDescription());
    	}
    	else if (attributeId.equals(Attributes.WriteMask)) {
    		return new Variant(this.getWriteMask());
    	} else if (attributeId.equals(Attributes.UserWriteMask)) {
    		return new Variant(this.getUserWriteMask());
    	}    	

    	return new Variant(false);
    }
    
    public ServiceResult write(UnsignedInteger attributeId, Object value){
    	if (attributeId.equals(Attributes.BrowseName)) {
    		BrowseName = (QualifiedName) value;
    		return new ServiceResult(StatusCode.GOOD);
    	}
    	
    	if (attributeId.equals(Attributes.DisplayName)) {
    		DisplayName = (LocalizedText) value;
    		return new ServiceResult(StatusCode.GOOD);
    	}
    	
    	if (attributeId.equals(Attributes.Description)) {
    		Description = (LocalizedText) value;
    		return new ServiceResult(StatusCode.GOOD);
    	}
    	if(attributeId.equals(Attributes.WriteMask)){
    		WriteMask = (UnsignedInteger) value;
    		return new ServiceResult(StatusCode.GOOD);
    	}
    	if(attributeId.equals(Attributes.UserWriteMask)){
    		UserWriteMask = (UnsignedInteger) value;
    		return new ServiceResult(StatusCode.GOOD);
    		
    	}
    	
    	return new ServiceResult(StatusCodes.Bad_AttributeIdInvalid);
    }

    //TODO Added by Mikko 3.11.2008
    /**
     * Checks if a reference already exists. If not adds passed reference to node.
     */
    public void ensureReferenceExists(ReferenceNode referenceToAdd) {

    	// Check if reference already exists.
    	for (ReferenceNode reference : References) {

    		if (reference.getIsInverse().equals(referenceToAdd.getIsInverse())) {

    			if (reference.getReferenceTypeId().equals(referenceToAdd.getReferenceTypeId())) {

    				if (reference.getTargetId().equals(referenceToAdd.getTargetId())) {
    					return;
    				}
    			}
    		}
    	}

    	// Add reference to node's reference nodes.
    	ReferenceNode[] referenceNodes = new ReferenceNode[References.length + 1];
    	for (int i = 0; i < References.length; i++) {
    		referenceNodes[i] = References[i];
    	}
    	referenceNodes[References.length] = referenceToAdd;
    	References = referenceNodes;
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
		return "Node: "+ObjectUtils.printFieldsDeep(this);
	}

}
