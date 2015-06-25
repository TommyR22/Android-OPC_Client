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
import org.opcfoundation.ua.core.RequestHeader;


public class SetTriggeringRequest extends Object implements ServiceRequest {

	public static final NodeId ID = Identifiers.SetTriggeringRequest;
	public static final NodeId BINARY = Identifiers.SetTriggeringRequest_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.SetTriggeringRequest_Encoding_DefaultXml;	
	
    protected RequestHeader RequestHeader;
    protected UnsignedInteger SubscriptionId;
    protected UnsignedInteger TriggeringItemId;
    protected UnsignedInteger[] LinksToAdd;
    protected UnsignedInteger[] LinksToRemove;
    
    public SetTriggeringRequest() {}
    
    public SetTriggeringRequest(RequestHeader RequestHeader, UnsignedInteger SubscriptionId, UnsignedInteger TriggeringItemId, UnsignedInteger[] LinksToAdd, UnsignedInteger[] LinksToRemove)
    {
        this.RequestHeader = RequestHeader;
        this.SubscriptionId = SubscriptionId;
        this.TriggeringItemId = TriggeringItemId;
        this.LinksToAdd = LinksToAdd;
        this.LinksToRemove = LinksToRemove;
    }
    
    public RequestHeader getRequestHeader()
    {
        return RequestHeader;
    }
    
    public void setRequestHeader(RequestHeader RequestHeader)
    {
        this.RequestHeader = RequestHeader;
    }
    
    public UnsignedInteger getSubscriptionId()
    {
        return SubscriptionId;
    }
    
    public void setSubscriptionId(UnsignedInteger SubscriptionId)
    {
        this.SubscriptionId = SubscriptionId;
    }
    
    public UnsignedInteger getTriggeringItemId()
    {
        return TriggeringItemId;
    }
    
    public void setTriggeringItemId(UnsignedInteger TriggeringItemId)
    {
        this.TriggeringItemId = TriggeringItemId;
    }
    
    public UnsignedInteger[] getLinksToAdd()
    {
        return LinksToAdd;
    }
    
    public void setLinksToAdd(UnsignedInteger[] LinksToAdd)
    {
        this.LinksToAdd = LinksToAdd;
    }
    
    public UnsignedInteger[] getLinksToRemove()
    {
        return LinksToRemove;
    }
    
    public void setLinksToRemove(UnsignedInteger[] LinksToRemove)
    {
        this.LinksToRemove = LinksToRemove;
    }
    
    /**
      * Deep clone
      *
      * @return cloned SetTriggeringRequest
      */
    public SetTriggeringRequest clone()
    {
        SetTriggeringRequest result = new SetTriggeringRequest();
        result.RequestHeader = RequestHeader==null ? null : RequestHeader.clone();
        result.SubscriptionId = SubscriptionId;
        result.TriggeringItemId = TriggeringItemId;
        result.LinksToAdd = LinksToAdd==null ? null : LinksToAdd.clone();
        result.LinksToRemove = LinksToRemove==null ? null : LinksToRemove.clone();
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
