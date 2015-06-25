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

import org.opcfoundation.ua.builtintypes.ServiceRequest;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.ContentFilter;
import org.opcfoundation.ua.core.NodeTypeDescription;
import org.opcfoundation.ua.core.RequestHeader;
import org.opcfoundation.ua.core.ViewDescription;


public class QueryFirstRequest extends Object implements ServiceRequest {

	public static final NodeId ID = Identifiers.QueryFirstRequest;
	public static final NodeId BINARY = Identifiers.QueryFirstRequest_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.QueryFirstRequest_Encoding_DefaultXml;	
	
    protected RequestHeader RequestHeader;
    protected ViewDescription View;
    protected NodeTypeDescription[] NodeTypes;
    protected ContentFilter Filter;
    protected UnsignedInteger MaxDataSetsToReturn;
    protected UnsignedInteger MaxReferencesToReturn;
    
    public QueryFirstRequest() {}
    
    public QueryFirstRequest(RequestHeader RequestHeader, ViewDescription View, NodeTypeDescription[] NodeTypes, ContentFilter Filter, UnsignedInteger MaxDataSetsToReturn, UnsignedInteger MaxReferencesToReturn)
    {
        this.RequestHeader = RequestHeader;
        this.View = View;
        this.NodeTypes = NodeTypes;
        this.Filter = Filter;
        this.MaxDataSetsToReturn = MaxDataSetsToReturn;
        this.MaxReferencesToReturn = MaxReferencesToReturn;
    }
    
    public RequestHeader getRequestHeader()
    {
        return RequestHeader;
    }
    
    public void setRequestHeader(RequestHeader RequestHeader)
    {
        this.RequestHeader = RequestHeader;
    }
    
    public ViewDescription getView()
    {
        return View;
    }
    
    public void setView(ViewDescription View)
    {
        this.View = View;
    }
    
    public NodeTypeDescription[] getNodeTypes()
    {
        return NodeTypes;
    }
    
    public void setNodeTypes(NodeTypeDescription[] NodeTypes)
    {
        this.NodeTypes = NodeTypes;
    }
    
    public ContentFilter getFilter()
    {
        return Filter;
    }
    
    public void setFilter(ContentFilter Filter)
    {
        this.Filter = Filter;
    }
    
    public UnsignedInteger getMaxDataSetsToReturn()
    {
        return MaxDataSetsToReturn;
    }
    
    public void setMaxDataSetsToReturn(UnsignedInteger MaxDataSetsToReturn)
    {
        this.MaxDataSetsToReturn = MaxDataSetsToReturn;
    }
    
    public UnsignedInteger getMaxReferencesToReturn()
    {
        return MaxReferencesToReturn;
    }
    
    public void setMaxReferencesToReturn(UnsignedInteger MaxReferencesToReturn)
    {
        this.MaxReferencesToReturn = MaxReferencesToReturn;
    }
    
    /**
      * Deep clone
      *
      * @return cloned QueryFirstRequest
      */
    public QueryFirstRequest clone()
    {
        QueryFirstRequest result = new QueryFirstRequest();
        result.RequestHeader = RequestHeader==null ? null : RequestHeader.clone();
        result.View = View==null ? null : View.clone();
        if (NodeTypes!=null) {
            result.NodeTypes = new NodeTypeDescription[NodeTypes.length];
            for (int i=0; i<NodeTypes.length; i++)
                result.NodeTypes[i] = NodeTypes[i].clone();
        }
        result.Filter = Filter==null ? null : Filter.clone();
        result.MaxDataSetsToReturn = MaxDataSetsToReturn;
        result.MaxReferencesToReturn = MaxReferencesToReturn;
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
		return ObjectUtils.printFieldsDeep(this);
	}
	
}
