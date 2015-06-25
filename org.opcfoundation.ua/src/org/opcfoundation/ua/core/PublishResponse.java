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

import org.opcfoundation.ua.builtintypes.ServiceResponse;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.builtintypes.DiagnosticInfo;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.NotificationMessage;
import org.opcfoundation.ua.core.ResponseHeader;


public class PublishResponse extends Object implements ServiceResponse {

	public static final NodeId ID = Identifiers.PublishResponse;
	public static final NodeId BINARY = Identifiers.PublishResponse_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.PublishResponse_Encoding_DefaultXml;	
	
    protected ResponseHeader ResponseHeader;
    protected UnsignedInteger SubscriptionId;
    protected UnsignedInteger[] AvailableSequenceNumbers;
    protected Boolean MoreNotifications;
    protected NotificationMessage NotificationMessage;
    protected StatusCode[] Results;
    protected DiagnosticInfo[] DiagnosticInfos;
    
    public PublishResponse() {}
    
    public PublishResponse(ResponseHeader ResponseHeader, UnsignedInteger SubscriptionId, UnsignedInteger[] AvailableSequenceNumbers, Boolean MoreNotifications, NotificationMessage NotificationMessage, StatusCode[] Results, DiagnosticInfo[] DiagnosticInfos)
    {
        this.ResponseHeader = ResponseHeader;
        this.SubscriptionId = SubscriptionId;
        this.AvailableSequenceNumbers = AvailableSequenceNumbers;
        this.MoreNotifications = MoreNotifications;
        this.NotificationMessage = NotificationMessage;
        this.Results = Results;
        this.DiagnosticInfos = DiagnosticInfos;
    }
    
    public ResponseHeader getResponseHeader()
    {
        return ResponseHeader;
    }
    
    public void setResponseHeader(ResponseHeader ResponseHeader)
    {
        this.ResponseHeader = ResponseHeader;
    }
    
    public UnsignedInteger getSubscriptionId()
    {
        return SubscriptionId;
    }
    
    public void setSubscriptionId(UnsignedInteger SubscriptionId)
    {
        this.SubscriptionId = SubscriptionId;
    }
    
    public UnsignedInteger[] getAvailableSequenceNumbers()
    {
        return AvailableSequenceNumbers;
    }
    
    public void setAvailableSequenceNumbers(UnsignedInteger[] AvailableSequenceNumbers)
    {
        this.AvailableSequenceNumbers = AvailableSequenceNumbers;
    }
    
    public Boolean getMoreNotifications()
    {
        return MoreNotifications;
    }
    
    public void setMoreNotifications(Boolean MoreNotifications)
    {
        this.MoreNotifications = MoreNotifications;
    }
    
    public NotificationMessage getNotificationMessage()
    {
        return NotificationMessage;
    }
    
    public void setNotificationMessage(NotificationMessage NotificationMessage)
    {
        this.NotificationMessage = NotificationMessage;
    }
    
    public StatusCode[] getResults()
    {
        return Results;
    }
    
    public void setResults(StatusCode[] Results)
    {
        this.Results = Results;
    }
    
    public DiagnosticInfo[] getDiagnosticInfos()
    {
        return DiagnosticInfos;
    }
    
    public void setDiagnosticInfos(DiagnosticInfo[] DiagnosticInfos)
    {
        this.DiagnosticInfos = DiagnosticInfos;
    }
    
    /**
      * Deep clone
      *
      * @return cloned PublishResponse
      */
    public PublishResponse clone()
    {
        PublishResponse result = new PublishResponse();
        result.ResponseHeader = ResponseHeader==null ? null : ResponseHeader.clone();
        result.SubscriptionId = SubscriptionId;
        result.AvailableSequenceNumbers = AvailableSequenceNumbers==null ? null : AvailableSequenceNumbers.clone();
        result.MoreNotifications = MoreNotifications;
        result.NotificationMessage = NotificationMessage==null ? null : NotificationMessage.clone();
        result.Results = Results==null ? null : Results.clone();
        result.DiagnosticInfos = DiagnosticInfos==null ? null : DiagnosticInfos.clone();
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
