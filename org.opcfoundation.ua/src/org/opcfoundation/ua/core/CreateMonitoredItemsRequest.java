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
import org.opcfoundation.ua.core.MonitoredItemCreateRequest;
import org.opcfoundation.ua.core.RequestHeader;
import org.opcfoundation.ua.core.TimestampsToReturn;


public class CreateMonitoredItemsRequest extends Object implements ServiceRequest {

	public static final NodeId ID = Identifiers.CreateMonitoredItemsRequest;
	public static final NodeId BINARY = Identifiers.CreateMonitoredItemsRequest_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.CreateMonitoredItemsRequest_Encoding_DefaultXml;	
	
    protected RequestHeader RequestHeader;
    protected UnsignedInteger SubscriptionId;
    protected TimestampsToReturn TimestampsToReturn;
    protected MonitoredItemCreateRequest[] ItemsToCreate;
    
    public CreateMonitoredItemsRequest() {}
    
    public CreateMonitoredItemsRequest(RequestHeader RequestHeader, UnsignedInteger SubscriptionId, TimestampsToReturn TimestampsToReturn, MonitoredItemCreateRequest[] ItemsToCreate)
    {
        this.RequestHeader = RequestHeader;
        this.SubscriptionId = SubscriptionId;
        this.TimestampsToReturn = TimestampsToReturn;
        this.ItemsToCreate = ItemsToCreate;
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
    
    public TimestampsToReturn getTimestampsToReturn()
    {
        return TimestampsToReturn;
    }
    
    public void setTimestampsToReturn(TimestampsToReturn TimestampsToReturn)
    {
        this.TimestampsToReturn = TimestampsToReturn;
    }
    
    public MonitoredItemCreateRequest[] getItemsToCreate()
    {
        return ItemsToCreate;
    }
    
    public void setItemsToCreate(MonitoredItemCreateRequest[] ItemsToCreate)
    {
        this.ItemsToCreate = ItemsToCreate;
    }
    
    /**
      * Deep clone
      *
      * @return cloned CreateMonitoredItemsRequest
      */
    public CreateMonitoredItemsRequest clone()
    {
        CreateMonitoredItemsRequest result = new CreateMonitoredItemsRequest();
        result.RequestHeader = RequestHeader==null ? null : RequestHeader.clone();
        result.SubscriptionId = SubscriptionId;
        result.TimestampsToReturn = TimestampsToReturn;
        if (ItemsToCreate!=null) {
            result.ItemsToCreate = new MonitoredItemCreateRequest[ItemsToCreate.length];
            for (int i=0; i<ItemsToCreate.length; i++)
                result.ItemsToCreate[i] = ItemsToCreate[i].clone();
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
