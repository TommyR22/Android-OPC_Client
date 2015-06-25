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
import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;



public class SubscriptionDiagnosticsDataType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.SubscriptionDiagnosticsDataType;
	public static final NodeId BINARY = Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.SubscriptionDiagnosticsDataType_Encoding_DefaultXml;	
	
    protected NodeId SessionId;
    protected UnsignedInteger SubscriptionId;
    protected UnsignedByte Priority;
    protected Double PublishingInterval;
    protected UnsignedInteger MaxKeepAliveCount;
    protected UnsignedInteger MaxLifetimeCount;
    protected UnsignedInteger MaxNotificationsPerPublish;
    protected Boolean PublishingEnabled;
    protected UnsignedInteger ModifyCount;
    protected UnsignedInteger EnableCount;
    protected UnsignedInteger DisableCount;
    protected UnsignedInteger RepublishRequestCount;
    protected UnsignedInteger RepublishMessageRequestCount;
    protected UnsignedInteger RepublishMessageCount;
    protected UnsignedInteger TransferRequestCount;
    protected UnsignedInteger TransferredToAltClientCount;
    protected UnsignedInteger TransferredToSameClientCount;
    protected UnsignedInteger PublishRequestCount;
    protected UnsignedInteger DataChangeNotificationsCount;
    protected UnsignedInteger EventNotificationsCount;
    protected UnsignedInteger NotificationsCount;
    protected UnsignedInteger LatePublishRequestCount;
    protected UnsignedInteger CurrentKeepAliveCount;
    protected UnsignedInteger CurrentLifetimeCount;
    protected UnsignedInteger UnacknowledgedMessageCount;
    protected UnsignedInteger DiscardedMessageCount;
    protected UnsignedInteger MonitoredItemCount;
    protected UnsignedInteger DisabledMonitoredItemCount;
    protected UnsignedInteger MonitoringQueueOverflowCount;
    protected UnsignedInteger NextSequenceNumber;
    protected UnsignedInteger EventQueueOverFlowCount;
    
    public SubscriptionDiagnosticsDataType() {}
    
    public SubscriptionDiagnosticsDataType(NodeId SessionId, UnsignedInteger SubscriptionId, UnsignedByte Priority, Double PublishingInterval, UnsignedInteger MaxKeepAliveCount, UnsignedInteger MaxLifetimeCount, UnsignedInteger MaxNotificationsPerPublish, Boolean PublishingEnabled, UnsignedInteger ModifyCount, UnsignedInteger EnableCount, UnsignedInteger DisableCount, UnsignedInteger RepublishRequestCount, UnsignedInteger RepublishMessageRequestCount, UnsignedInteger RepublishMessageCount, UnsignedInteger TransferRequestCount, UnsignedInteger TransferredToAltClientCount, UnsignedInteger TransferredToSameClientCount, UnsignedInteger PublishRequestCount, UnsignedInteger DataChangeNotificationsCount, UnsignedInteger EventNotificationsCount, UnsignedInteger NotificationsCount, UnsignedInteger LatePublishRequestCount, UnsignedInteger CurrentKeepAliveCount, UnsignedInteger CurrentLifetimeCount, UnsignedInteger UnacknowledgedMessageCount, UnsignedInteger DiscardedMessageCount, UnsignedInteger MonitoredItemCount, UnsignedInteger DisabledMonitoredItemCount, UnsignedInteger MonitoringQueueOverflowCount, UnsignedInteger NextSequenceNumber, UnsignedInteger EventQueueOverFlowCount)
    {
        this.SessionId = SessionId;
        this.SubscriptionId = SubscriptionId;
        this.Priority = Priority;
        this.PublishingInterval = PublishingInterval;
        this.MaxKeepAliveCount = MaxKeepAliveCount;
        this.MaxLifetimeCount = MaxLifetimeCount;
        this.MaxNotificationsPerPublish = MaxNotificationsPerPublish;
        this.PublishingEnabled = PublishingEnabled;
        this.ModifyCount = ModifyCount;
        this.EnableCount = EnableCount;
        this.DisableCount = DisableCount;
        this.RepublishRequestCount = RepublishRequestCount;
        this.RepublishMessageRequestCount = RepublishMessageRequestCount;
        this.RepublishMessageCount = RepublishMessageCount;
        this.TransferRequestCount = TransferRequestCount;
        this.TransferredToAltClientCount = TransferredToAltClientCount;
        this.TransferredToSameClientCount = TransferredToSameClientCount;
        this.PublishRequestCount = PublishRequestCount;
        this.DataChangeNotificationsCount = DataChangeNotificationsCount;
        this.EventNotificationsCount = EventNotificationsCount;
        this.NotificationsCount = NotificationsCount;
        this.LatePublishRequestCount = LatePublishRequestCount;
        this.CurrentKeepAliveCount = CurrentKeepAliveCount;
        this.CurrentLifetimeCount = CurrentLifetimeCount;
        this.UnacknowledgedMessageCount = UnacknowledgedMessageCount;
        this.DiscardedMessageCount = DiscardedMessageCount;
        this.MonitoredItemCount = MonitoredItemCount;
        this.DisabledMonitoredItemCount = DisabledMonitoredItemCount;
        this.MonitoringQueueOverflowCount = MonitoringQueueOverflowCount;
        this.NextSequenceNumber = NextSequenceNumber;
        this.EventQueueOverFlowCount = EventQueueOverFlowCount;
    }
    
    public NodeId getSessionId()
    {
        return SessionId;
    }
    
    public void setSessionId(NodeId SessionId)
    {
        this.SessionId = SessionId;
    }
    
    public UnsignedInteger getSubscriptionId()
    {
        return SubscriptionId;
    }
    
    public void setSubscriptionId(UnsignedInteger SubscriptionId)
    {
        this.SubscriptionId = SubscriptionId;
    }
    
    public UnsignedByte getPriority()
    {
        return Priority;
    }
    
    public void setPriority(UnsignedByte Priority)
    {
        this.Priority = Priority;
    }
    
    public Double getPublishingInterval()
    {
        return PublishingInterval;
    }
    
    public void setPublishingInterval(Double PublishingInterval)
    {
        this.PublishingInterval = PublishingInterval;
    }
    
    public UnsignedInteger getMaxKeepAliveCount()
    {
        return MaxKeepAliveCount;
    }
    
    public void setMaxKeepAliveCount(UnsignedInteger MaxKeepAliveCount)
    {
        this.MaxKeepAliveCount = MaxKeepAliveCount;
    }
    
    public UnsignedInteger getMaxLifetimeCount()
    {
        return MaxLifetimeCount;
    }
    
    public void setMaxLifetimeCount(UnsignedInteger MaxLifetimeCount)
    {
        this.MaxLifetimeCount = MaxLifetimeCount;
    }
    
    public UnsignedInteger getMaxNotificationsPerPublish()
    {
        return MaxNotificationsPerPublish;
    }
    
    public void setMaxNotificationsPerPublish(UnsignedInteger MaxNotificationsPerPublish)
    {
        this.MaxNotificationsPerPublish = MaxNotificationsPerPublish;
    }
    
    public Boolean getPublishingEnabled()
    {
        return PublishingEnabled;
    }
    
    public void setPublishingEnabled(Boolean PublishingEnabled)
    {
        this.PublishingEnabled = PublishingEnabled;
    }
    
    public UnsignedInteger getModifyCount()
    {
        return ModifyCount;
    }
    
    public void setModifyCount(UnsignedInteger ModifyCount)
    {
        this.ModifyCount = ModifyCount;
    }
    
    public UnsignedInteger getEnableCount()
    {
        return EnableCount;
    }
    
    public void setEnableCount(UnsignedInteger EnableCount)
    {
        this.EnableCount = EnableCount;
    }
    
    public UnsignedInteger getDisableCount()
    {
        return DisableCount;
    }
    
    public void setDisableCount(UnsignedInteger DisableCount)
    {
        this.DisableCount = DisableCount;
    }
    
    public UnsignedInteger getRepublishRequestCount()
    {
        return RepublishRequestCount;
    }
    
    public void setRepublishRequestCount(UnsignedInteger RepublishRequestCount)
    {
        this.RepublishRequestCount = RepublishRequestCount;
    }
    
    public UnsignedInteger getRepublishMessageRequestCount()
    {
        return RepublishMessageRequestCount;
    }
    
    public void setRepublishMessageRequestCount(UnsignedInteger RepublishMessageRequestCount)
    {
        this.RepublishMessageRequestCount = RepublishMessageRequestCount;
    }
    
    public UnsignedInteger getRepublishMessageCount()
    {
        return RepublishMessageCount;
    }
    
    public void setRepublishMessageCount(UnsignedInteger RepublishMessageCount)
    {
        this.RepublishMessageCount = RepublishMessageCount;
    }
    
    public UnsignedInteger getTransferRequestCount()
    {
        return TransferRequestCount;
    }
    
    public void setTransferRequestCount(UnsignedInteger TransferRequestCount)
    {
        this.TransferRequestCount = TransferRequestCount;
    }
    
    public UnsignedInteger getTransferredToAltClientCount()
    {
        return TransferredToAltClientCount;
    }
    
    public void setTransferredToAltClientCount(UnsignedInteger TransferredToAltClientCount)
    {
        this.TransferredToAltClientCount = TransferredToAltClientCount;
    }
    
    public UnsignedInteger getTransferredToSameClientCount()
    {
        return TransferredToSameClientCount;
    }
    
    public void setTransferredToSameClientCount(UnsignedInteger TransferredToSameClientCount)
    {
        this.TransferredToSameClientCount = TransferredToSameClientCount;
    }
    
    public UnsignedInteger getPublishRequestCount()
    {
        return PublishRequestCount;
    }
    
    public void setPublishRequestCount(UnsignedInteger PublishRequestCount)
    {
        this.PublishRequestCount = PublishRequestCount;
    }
    
    public UnsignedInteger getDataChangeNotificationsCount()
    {
        return DataChangeNotificationsCount;
    }
    
    public void setDataChangeNotificationsCount(UnsignedInteger DataChangeNotificationsCount)
    {
        this.DataChangeNotificationsCount = DataChangeNotificationsCount;
    }
    
    public UnsignedInteger getEventNotificationsCount()
    {
        return EventNotificationsCount;
    }
    
    public void setEventNotificationsCount(UnsignedInteger EventNotificationsCount)
    {
        this.EventNotificationsCount = EventNotificationsCount;
    }
    
    public UnsignedInteger getNotificationsCount()
    {
        return NotificationsCount;
    }
    
    public void setNotificationsCount(UnsignedInteger NotificationsCount)
    {
        this.NotificationsCount = NotificationsCount;
    }
    
    public UnsignedInteger getLatePublishRequestCount()
    {
        return LatePublishRequestCount;
    }
    
    public void setLatePublishRequestCount(UnsignedInteger LatePublishRequestCount)
    {
        this.LatePublishRequestCount = LatePublishRequestCount;
    }
    
    public UnsignedInteger getCurrentKeepAliveCount()
    {
        return CurrentKeepAliveCount;
    }
    
    public void setCurrentKeepAliveCount(UnsignedInteger CurrentKeepAliveCount)
    {
        this.CurrentKeepAliveCount = CurrentKeepAliveCount;
    }
    
    public UnsignedInteger getCurrentLifetimeCount()
    {
        return CurrentLifetimeCount;
    }
    
    public void setCurrentLifetimeCount(UnsignedInteger CurrentLifetimeCount)
    {
        this.CurrentLifetimeCount = CurrentLifetimeCount;
    }
    
    public UnsignedInteger getUnacknowledgedMessageCount()
    {
        return UnacknowledgedMessageCount;
    }
    
    public void setUnacknowledgedMessageCount(UnsignedInteger UnacknowledgedMessageCount)
    {
        this.UnacknowledgedMessageCount = UnacknowledgedMessageCount;
    }
    
    public UnsignedInteger getDiscardedMessageCount()
    {
        return DiscardedMessageCount;
    }
    
    public void setDiscardedMessageCount(UnsignedInteger DiscardedMessageCount)
    {
        this.DiscardedMessageCount = DiscardedMessageCount;
    }
    
    public UnsignedInteger getMonitoredItemCount()
    {
        return MonitoredItemCount;
    }
    
    public void setMonitoredItemCount(UnsignedInteger MonitoredItemCount)
    {
        this.MonitoredItemCount = MonitoredItemCount;
    }
    
    public UnsignedInteger getDisabledMonitoredItemCount()
    {
        return DisabledMonitoredItemCount;
    }
    
    public void setDisabledMonitoredItemCount(UnsignedInteger DisabledMonitoredItemCount)
    {
        this.DisabledMonitoredItemCount = DisabledMonitoredItemCount;
    }
    
    public UnsignedInteger getMonitoringQueueOverflowCount()
    {
        return MonitoringQueueOverflowCount;
    }
    
    public void setMonitoringQueueOverflowCount(UnsignedInteger MonitoringQueueOverflowCount)
    {
        this.MonitoringQueueOverflowCount = MonitoringQueueOverflowCount;
    }
    
    public UnsignedInteger getNextSequenceNumber()
    {
        return NextSequenceNumber;
    }
    
    public void setNextSequenceNumber(UnsignedInteger NextSequenceNumber)
    {
        this.NextSequenceNumber = NextSequenceNumber;
    }
    
    public UnsignedInteger getEventQueueOverFlowCount()
    {
        return EventQueueOverFlowCount;
    }
    
    public void setEventQueueOverFlowCount(UnsignedInteger EventQueueOverFlowCount)
    {
        this.EventQueueOverFlowCount = EventQueueOverFlowCount;
    }
    
    /**
      * Deep clone
      *
      * @return cloned SubscriptionDiagnosticsDataType
      */
    public SubscriptionDiagnosticsDataType clone()
    {
        SubscriptionDiagnosticsDataType result = new SubscriptionDiagnosticsDataType();
        result.SessionId = SessionId;
        result.SubscriptionId = SubscriptionId;
        result.Priority = Priority;
        result.PublishingInterval = PublishingInterval;
        result.MaxKeepAliveCount = MaxKeepAliveCount;
        result.MaxLifetimeCount = MaxLifetimeCount;
        result.MaxNotificationsPerPublish = MaxNotificationsPerPublish;
        result.PublishingEnabled = PublishingEnabled;
        result.ModifyCount = ModifyCount;
        result.EnableCount = EnableCount;
        result.DisableCount = DisableCount;
        result.RepublishRequestCount = RepublishRequestCount;
        result.RepublishMessageRequestCount = RepublishMessageRequestCount;
        result.RepublishMessageCount = RepublishMessageCount;
        result.TransferRequestCount = TransferRequestCount;
        result.TransferredToAltClientCount = TransferredToAltClientCount;
        result.TransferredToSameClientCount = TransferredToSameClientCount;
        result.PublishRequestCount = PublishRequestCount;
        result.DataChangeNotificationsCount = DataChangeNotificationsCount;
        result.EventNotificationsCount = EventNotificationsCount;
        result.NotificationsCount = NotificationsCount;
        result.LatePublishRequestCount = LatePublishRequestCount;
        result.CurrentKeepAliveCount = CurrentKeepAliveCount;
        result.CurrentLifetimeCount = CurrentLifetimeCount;
        result.UnacknowledgedMessageCount = UnacknowledgedMessageCount;
        result.DiscardedMessageCount = DiscardedMessageCount;
        result.MonitoredItemCount = MonitoredItemCount;
        result.DisabledMonitoredItemCount = DisabledMonitoredItemCount;
        result.MonitoringQueueOverflowCount = MonitoringQueueOverflowCount;
        result.NextSequenceNumber = NextSequenceNumber;
        result.EventQueueOverFlowCount = EventQueueOverFlowCount;
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
		return "SubscriptionDiagnosticsDataType: "+ObjectUtils.printFieldsDeep(this);
	}

}
