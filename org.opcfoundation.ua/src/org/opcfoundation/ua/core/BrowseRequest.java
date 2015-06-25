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
import org.opcfoundation.ua.core.BrowseDescription;
import org.opcfoundation.ua.core.RequestHeader;
import org.opcfoundation.ua.core.ViewDescription;


public class BrowseRequest extends Object implements ServiceRequest {

	public static final NodeId ID = Identifiers.BrowseRequest;
	public static final NodeId BINARY = Identifiers.BrowseRequest_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.BrowseRequest_Encoding_DefaultXml;	
	
    protected RequestHeader RequestHeader;
    protected ViewDescription View;
    protected UnsignedInteger RequestedMaxReferencesPerNode;
    protected BrowseDescription[] NodesToBrowse;
    
    public BrowseRequest() {}
    
    public BrowseRequest(RequestHeader RequestHeader, ViewDescription View, UnsignedInteger RequestedMaxReferencesPerNode, BrowseDescription[] NodesToBrowse)
    {
        this.RequestHeader = RequestHeader;
        this.View = View;
        this.RequestedMaxReferencesPerNode = RequestedMaxReferencesPerNode;
        this.NodesToBrowse = NodesToBrowse;
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
    
    public UnsignedInteger getRequestedMaxReferencesPerNode()
    {
        return RequestedMaxReferencesPerNode;
    }
    
    public void setRequestedMaxReferencesPerNode(UnsignedInteger RequestedMaxReferencesPerNode)
    {
        this.RequestedMaxReferencesPerNode = RequestedMaxReferencesPerNode;
    }
    
    public BrowseDescription[] getNodesToBrowse()
    {
        return NodesToBrowse;
    }
    
    public void setNodesToBrowse(BrowseDescription[] NodesToBrowse)
    {
        this.NodesToBrowse = NodesToBrowse;
    }
    
    /**
      * Deep clone
      *
      * @return cloned BrowseRequest
      */
    public BrowseRequest clone()
    {
        BrowseRequest result = new BrowseRequest();
        result.RequestHeader = RequestHeader==null ? null : RequestHeader.clone();
        result.View = View==null ? null : View.clone();
        result.RequestedMaxReferencesPerNode = RequestedMaxReferencesPerNode;
        if (NodesToBrowse!=null) {
            result.NodesToBrowse = new BrowseDescription[NodesToBrowse.length];
            for (int i=0; i<NodesToBrowse.length; i++)
                result.NodesToBrowse[i] = NodesToBrowse[i].clone();
        }
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
