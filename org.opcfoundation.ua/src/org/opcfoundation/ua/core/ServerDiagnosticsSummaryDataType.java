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
import org.opcfoundation.ua.builtintypes.UnsignedInteger;



public class ServerDiagnosticsSummaryDataType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ServerDiagnosticsSummaryDataType;
	public static final NodeId BINARY = Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ServerDiagnosticsSummaryDataType_Encoding_DefaultXml;	
	
    protected UnsignedInteger ServerViewCount;
    protected UnsignedInteger CurrentSessionCount;
    protected UnsignedInteger CumulatedSessionCount;
    protected UnsignedInteger SecurityRejectedSessionCount;
    protected UnsignedInteger RejectedSessionCount;
    protected UnsignedInteger SessionTimeoutCount;
    protected UnsignedInteger SessionAbortCount;
    protected UnsignedInteger CurrentSubscriptionCount;
    protected UnsignedInteger CumulatedSubscriptionCount;
    protected UnsignedInteger PublishingIntervalCount;
    protected UnsignedInteger SecurityRejectedRequestsCount;
    protected UnsignedInteger RejectedRequestsCount;
    
    public ServerDiagnosticsSummaryDataType() {}
    
    public ServerDiagnosticsSummaryDataType(UnsignedInteger ServerViewCount, UnsignedInteger CurrentSessionCount, UnsignedInteger CumulatedSessionCount, UnsignedInteger SecurityRejectedSessionCount, UnsignedInteger RejectedSessionCount, UnsignedInteger SessionTimeoutCount, UnsignedInteger SessionAbortCount, UnsignedInteger CurrentSubscriptionCount, UnsignedInteger CumulatedSubscriptionCount, UnsignedInteger PublishingIntervalCount, UnsignedInteger SecurityRejectedRequestsCount, UnsignedInteger RejectedRequestsCount)
    {
        this.ServerViewCount = ServerViewCount;
        this.CurrentSessionCount = CurrentSessionCount;
        this.CumulatedSessionCount = CumulatedSessionCount;
        this.SecurityRejectedSessionCount = SecurityRejectedSessionCount;
        this.RejectedSessionCount = RejectedSessionCount;
        this.SessionTimeoutCount = SessionTimeoutCount;
        this.SessionAbortCount = SessionAbortCount;
        this.CurrentSubscriptionCount = CurrentSubscriptionCount;
        this.CumulatedSubscriptionCount = CumulatedSubscriptionCount;
        this.PublishingIntervalCount = PublishingIntervalCount;
        this.SecurityRejectedRequestsCount = SecurityRejectedRequestsCount;
        this.RejectedRequestsCount = RejectedRequestsCount;
    }
    
    public UnsignedInteger getServerViewCount()
    {
        return ServerViewCount;
    }
    
    public void setServerViewCount(UnsignedInteger ServerViewCount)
    {
        this.ServerViewCount = ServerViewCount;
    }
    
    public UnsignedInteger getCurrentSessionCount()
    {
        return CurrentSessionCount;
    }
    
    public void setCurrentSessionCount(UnsignedInteger CurrentSessionCount)
    {
        this.CurrentSessionCount = CurrentSessionCount;
    }
    
    public UnsignedInteger getCumulatedSessionCount()
    {
        return CumulatedSessionCount;
    }
    
    public void setCumulatedSessionCount(UnsignedInteger CumulatedSessionCount)
    {
        this.CumulatedSessionCount = CumulatedSessionCount;
    }
    
    public UnsignedInteger getSecurityRejectedSessionCount()
    {
        return SecurityRejectedSessionCount;
    }
    
    public void setSecurityRejectedSessionCount(UnsignedInteger SecurityRejectedSessionCount)
    {
        this.SecurityRejectedSessionCount = SecurityRejectedSessionCount;
    }
    
    public UnsignedInteger getRejectedSessionCount()
    {
        return RejectedSessionCount;
    }
    
    public void setRejectedSessionCount(UnsignedInteger RejectedSessionCount)
    {
        this.RejectedSessionCount = RejectedSessionCount;
    }
    
    public UnsignedInteger getSessionTimeoutCount()
    {
        return SessionTimeoutCount;
    }
    
    public void setSessionTimeoutCount(UnsignedInteger SessionTimeoutCount)
    {
        this.SessionTimeoutCount = SessionTimeoutCount;
    }
    
    public UnsignedInteger getSessionAbortCount()
    {
        return SessionAbortCount;
    }
    
    public void setSessionAbortCount(UnsignedInteger SessionAbortCount)
    {
        this.SessionAbortCount = SessionAbortCount;
    }
    
    public UnsignedInteger getCurrentSubscriptionCount()
    {
        return CurrentSubscriptionCount;
    }
    
    public void setCurrentSubscriptionCount(UnsignedInteger CurrentSubscriptionCount)
    {
        this.CurrentSubscriptionCount = CurrentSubscriptionCount;
    }
    
    public UnsignedInteger getCumulatedSubscriptionCount()
    {
        return CumulatedSubscriptionCount;
    }
    
    public void setCumulatedSubscriptionCount(UnsignedInteger CumulatedSubscriptionCount)
    {
        this.CumulatedSubscriptionCount = CumulatedSubscriptionCount;
    }
    
    public UnsignedInteger getPublishingIntervalCount()
    {
        return PublishingIntervalCount;
    }
    
    public void setPublishingIntervalCount(UnsignedInteger PublishingIntervalCount)
    {
        this.PublishingIntervalCount = PublishingIntervalCount;
    }
    
    public UnsignedInteger getSecurityRejectedRequestsCount()
    {
        return SecurityRejectedRequestsCount;
    }
    
    public void setSecurityRejectedRequestsCount(UnsignedInteger SecurityRejectedRequestsCount)
    {
        this.SecurityRejectedRequestsCount = SecurityRejectedRequestsCount;
    }
    
    public UnsignedInteger getRejectedRequestsCount()
    {
        return RejectedRequestsCount;
    }
    
    public void setRejectedRequestsCount(UnsignedInteger RejectedRequestsCount)
    {
        this.RejectedRequestsCount = RejectedRequestsCount;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ServerDiagnosticsSummaryDataType
      */
    public ServerDiagnosticsSummaryDataType clone()
    {
        ServerDiagnosticsSummaryDataType result = new ServerDiagnosticsSummaryDataType();
        result.ServerViewCount = ServerViewCount;
        result.CurrentSessionCount = CurrentSessionCount;
        result.CumulatedSessionCount = CumulatedSessionCount;
        result.SecurityRejectedSessionCount = SecurityRejectedSessionCount;
        result.RejectedSessionCount = RejectedSessionCount;
        result.SessionTimeoutCount = SessionTimeoutCount;
        result.SessionAbortCount = SessionAbortCount;
        result.CurrentSubscriptionCount = CurrentSubscriptionCount;
        result.CumulatedSubscriptionCount = CumulatedSubscriptionCount;
        result.PublishingIntervalCount = PublishingIntervalCount;
        result.SecurityRejectedRequestsCount = SecurityRejectedRequestsCount;
        result.RejectedRequestsCount = RejectedRequestsCount;
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
		return "ServerDiagnosticsSummaryDataType: "+ObjectUtils.printFieldsDeep(this);
	}

}
