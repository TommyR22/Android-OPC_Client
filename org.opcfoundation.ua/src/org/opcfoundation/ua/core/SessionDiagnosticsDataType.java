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
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ServiceCounterDataType;



public class SessionDiagnosticsDataType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.SessionDiagnosticsDataType;
	public static final NodeId BINARY = Identifiers.SessionDiagnosticsDataType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.SessionDiagnosticsDataType_Encoding_DefaultXml;	
	
    protected NodeId SessionId;
    protected String SessionName;
    protected ApplicationDescription ClientDescription;
    protected String ServerUri;
    protected String EndpointUrl;
    protected String[] LocaleIds;
    protected Double ActualSessionTimeout;
    protected UnsignedInteger MaxResponseMessageSize;
    protected DateTime ClientConnectionTime;
    protected DateTime ClientLastContactTime;
    protected UnsignedInteger CurrentSubscriptionsCount;
    protected UnsignedInteger CurrentMonitoredItemsCount;
    protected UnsignedInteger CurrentPublishRequestsInQueue;
    protected ServiceCounterDataType TotalRequestCount;
    protected UnsignedInteger UnauthorizedRequestCount;
    protected ServiceCounterDataType ReadCount;
    protected ServiceCounterDataType HistoryReadCount;
    protected ServiceCounterDataType WriteCount;
    protected ServiceCounterDataType HistoryUpdateCount;
    protected ServiceCounterDataType CallCount;
    protected ServiceCounterDataType CreateMonitoredItemsCount;
    protected ServiceCounterDataType ModifyMonitoredItemsCount;
    protected ServiceCounterDataType SetMonitoringModeCount;
    protected ServiceCounterDataType SetTriggeringCount;
    protected ServiceCounterDataType DeleteMonitoredItemsCount;
    protected ServiceCounterDataType CreateSubscriptionCount;
    protected ServiceCounterDataType ModifySubscriptionCount;
    protected ServiceCounterDataType SetPublishingModeCount;
    protected ServiceCounterDataType PublishCount;
    protected ServiceCounterDataType RepublishCount;
    protected ServiceCounterDataType TransferSubscriptionsCount;
    protected ServiceCounterDataType DeleteSubscriptionsCount;
    protected ServiceCounterDataType AddNodesCount;
    protected ServiceCounterDataType AddReferencesCount;
    protected ServiceCounterDataType DeleteNodesCount;
    protected ServiceCounterDataType DeleteReferencesCount;
    protected ServiceCounterDataType BrowseCount;
    protected ServiceCounterDataType BrowseNextCount;
    protected ServiceCounterDataType TranslateBrowsePathsToNodeIdsCount;
    protected ServiceCounterDataType QueryFirstCount;
    protected ServiceCounterDataType QueryNextCount;
    protected ServiceCounterDataType RegisterNodesCount;
    protected ServiceCounterDataType UnregisterNodesCount;
    
    public SessionDiagnosticsDataType() {}
    
    public SessionDiagnosticsDataType(NodeId SessionId, String SessionName, ApplicationDescription ClientDescription, String ServerUri, String EndpointUrl, String[] LocaleIds, Double ActualSessionTimeout, UnsignedInteger MaxResponseMessageSize, DateTime ClientConnectionTime, DateTime ClientLastContactTime, UnsignedInteger CurrentSubscriptionsCount, UnsignedInteger CurrentMonitoredItemsCount, UnsignedInteger CurrentPublishRequestsInQueue, ServiceCounterDataType TotalRequestCount, UnsignedInteger UnauthorizedRequestCount, ServiceCounterDataType ReadCount, ServiceCounterDataType HistoryReadCount, ServiceCounterDataType WriteCount, ServiceCounterDataType HistoryUpdateCount, ServiceCounterDataType CallCount, ServiceCounterDataType CreateMonitoredItemsCount, ServiceCounterDataType ModifyMonitoredItemsCount, ServiceCounterDataType SetMonitoringModeCount, ServiceCounterDataType SetTriggeringCount, ServiceCounterDataType DeleteMonitoredItemsCount, ServiceCounterDataType CreateSubscriptionCount, ServiceCounterDataType ModifySubscriptionCount, ServiceCounterDataType SetPublishingModeCount, ServiceCounterDataType PublishCount, ServiceCounterDataType RepublishCount, ServiceCounterDataType TransferSubscriptionsCount, ServiceCounterDataType DeleteSubscriptionsCount, ServiceCounterDataType AddNodesCount, ServiceCounterDataType AddReferencesCount, ServiceCounterDataType DeleteNodesCount, ServiceCounterDataType DeleteReferencesCount, ServiceCounterDataType BrowseCount, ServiceCounterDataType BrowseNextCount, ServiceCounterDataType TranslateBrowsePathsToNodeIdsCount, ServiceCounterDataType QueryFirstCount, ServiceCounterDataType QueryNextCount, ServiceCounterDataType RegisterNodesCount, ServiceCounterDataType UnregisterNodesCount)
    {
        this.SessionId = SessionId;
        this.SessionName = SessionName;
        this.ClientDescription = ClientDescription;
        this.ServerUri = ServerUri;
        this.EndpointUrl = EndpointUrl;
        this.LocaleIds = LocaleIds;
        this.ActualSessionTimeout = ActualSessionTimeout;
        this.MaxResponseMessageSize = MaxResponseMessageSize;
        this.ClientConnectionTime = ClientConnectionTime;
        this.ClientLastContactTime = ClientLastContactTime;
        this.CurrentSubscriptionsCount = CurrentSubscriptionsCount;
        this.CurrentMonitoredItemsCount = CurrentMonitoredItemsCount;
        this.CurrentPublishRequestsInQueue = CurrentPublishRequestsInQueue;
        this.TotalRequestCount = TotalRequestCount;
        this.UnauthorizedRequestCount = UnauthorizedRequestCount;
        this.ReadCount = ReadCount;
        this.HistoryReadCount = HistoryReadCount;
        this.WriteCount = WriteCount;
        this.HistoryUpdateCount = HistoryUpdateCount;
        this.CallCount = CallCount;
        this.CreateMonitoredItemsCount = CreateMonitoredItemsCount;
        this.ModifyMonitoredItemsCount = ModifyMonitoredItemsCount;
        this.SetMonitoringModeCount = SetMonitoringModeCount;
        this.SetTriggeringCount = SetTriggeringCount;
        this.DeleteMonitoredItemsCount = DeleteMonitoredItemsCount;
        this.CreateSubscriptionCount = CreateSubscriptionCount;
        this.ModifySubscriptionCount = ModifySubscriptionCount;
        this.SetPublishingModeCount = SetPublishingModeCount;
        this.PublishCount = PublishCount;
        this.RepublishCount = RepublishCount;
        this.TransferSubscriptionsCount = TransferSubscriptionsCount;
        this.DeleteSubscriptionsCount = DeleteSubscriptionsCount;
        this.AddNodesCount = AddNodesCount;
        this.AddReferencesCount = AddReferencesCount;
        this.DeleteNodesCount = DeleteNodesCount;
        this.DeleteReferencesCount = DeleteReferencesCount;
        this.BrowseCount = BrowseCount;
        this.BrowseNextCount = BrowseNextCount;
        this.TranslateBrowsePathsToNodeIdsCount = TranslateBrowsePathsToNodeIdsCount;
        this.QueryFirstCount = QueryFirstCount;
        this.QueryNextCount = QueryNextCount;
        this.RegisterNodesCount = RegisterNodesCount;
        this.UnregisterNodesCount = UnregisterNodesCount;
    }
    
    public NodeId getSessionId()
    {
        return SessionId;
    }
    
    public void setSessionId(NodeId SessionId)
    {
        this.SessionId = SessionId;
    }
    
    public String getSessionName()
    {
        return SessionName;
    }
    
    public void setSessionName(String SessionName)
    {
        this.SessionName = SessionName;
    }
    
    public ApplicationDescription getClientDescription()
    {
        return ClientDescription;
    }
    
    public void setClientDescription(ApplicationDescription ClientDescription)
    {
        this.ClientDescription = ClientDescription;
    }
    
    public String getServerUri()
    {
        return ServerUri;
    }
    
    public void setServerUri(String ServerUri)
    {
        this.ServerUri = ServerUri;
    }
    
    public String getEndpointUrl()
    {
        return EndpointUrl;
    }
    
    public void setEndpointUrl(String EndpointUrl)
    {
        this.EndpointUrl = EndpointUrl;
    }
    
    public String[] getLocaleIds()
    {
        return LocaleIds;
    }
    
    public void setLocaleIds(String[] LocaleIds)
    {
        this.LocaleIds = LocaleIds;
    }
    
    public Double getActualSessionTimeout()
    {
        return ActualSessionTimeout;
    }
    
    public void setActualSessionTimeout(Double ActualSessionTimeout)
    {
        this.ActualSessionTimeout = ActualSessionTimeout;
    }
    
    public UnsignedInteger getMaxResponseMessageSize()
    {
        return MaxResponseMessageSize;
    }
    
    public void setMaxResponseMessageSize(UnsignedInteger MaxResponseMessageSize)
    {
        this.MaxResponseMessageSize = MaxResponseMessageSize;
    }
    
    public DateTime getClientConnectionTime()
    {
        return ClientConnectionTime;
    }
    
    public void setClientConnectionTime(DateTime ClientConnectionTime)
    {
        this.ClientConnectionTime = ClientConnectionTime;
    }
    
    public DateTime getClientLastContactTime()
    {
        return ClientLastContactTime;
    }
    
    public void setClientLastContactTime(DateTime ClientLastContactTime)
    {
        this.ClientLastContactTime = ClientLastContactTime;
    }
    
    public UnsignedInteger getCurrentSubscriptionsCount()
    {
        return CurrentSubscriptionsCount;
    }
    
    public void setCurrentSubscriptionsCount(UnsignedInteger CurrentSubscriptionsCount)
    {
        this.CurrentSubscriptionsCount = CurrentSubscriptionsCount;
    }
    
    public UnsignedInteger getCurrentMonitoredItemsCount()
    {
        return CurrentMonitoredItemsCount;
    }
    
    public void setCurrentMonitoredItemsCount(UnsignedInteger CurrentMonitoredItemsCount)
    {
        this.CurrentMonitoredItemsCount = CurrentMonitoredItemsCount;
    }
    
    public UnsignedInteger getCurrentPublishRequestsInQueue()
    {
        return CurrentPublishRequestsInQueue;
    }
    
    public void setCurrentPublishRequestsInQueue(UnsignedInteger CurrentPublishRequestsInQueue)
    {
        this.CurrentPublishRequestsInQueue = CurrentPublishRequestsInQueue;
    }
    
    public ServiceCounterDataType getTotalRequestCount()
    {
        return TotalRequestCount;
    }
    
    public void setTotalRequestCount(ServiceCounterDataType TotalRequestCount)
    {
        this.TotalRequestCount = TotalRequestCount;
    }
    
    public UnsignedInteger getUnauthorizedRequestCount()
    {
        return UnauthorizedRequestCount;
    }
    
    public void setUnauthorizedRequestCount(UnsignedInteger UnauthorizedRequestCount)
    {
        this.UnauthorizedRequestCount = UnauthorizedRequestCount;
    }
    
    public ServiceCounterDataType getReadCount()
    {
        return ReadCount;
    }
    
    public void setReadCount(ServiceCounterDataType ReadCount)
    {
        this.ReadCount = ReadCount;
    }
    
    public ServiceCounterDataType getHistoryReadCount()
    {
        return HistoryReadCount;
    }
    
    public void setHistoryReadCount(ServiceCounterDataType HistoryReadCount)
    {
        this.HistoryReadCount = HistoryReadCount;
    }
    
    public ServiceCounterDataType getWriteCount()
    {
        return WriteCount;
    }
    
    public void setWriteCount(ServiceCounterDataType WriteCount)
    {
        this.WriteCount = WriteCount;
    }
    
    public ServiceCounterDataType getHistoryUpdateCount()
    {
        return HistoryUpdateCount;
    }
    
    public void setHistoryUpdateCount(ServiceCounterDataType HistoryUpdateCount)
    {
        this.HistoryUpdateCount = HistoryUpdateCount;
    }
    
    public ServiceCounterDataType getCallCount()
    {
        return CallCount;
    }
    
    public void setCallCount(ServiceCounterDataType CallCount)
    {
        this.CallCount = CallCount;
    }
    
    public ServiceCounterDataType getCreateMonitoredItemsCount()
    {
        return CreateMonitoredItemsCount;
    }
    
    public void setCreateMonitoredItemsCount(ServiceCounterDataType CreateMonitoredItemsCount)
    {
        this.CreateMonitoredItemsCount = CreateMonitoredItemsCount;
    }
    
    public ServiceCounterDataType getModifyMonitoredItemsCount()
    {
        return ModifyMonitoredItemsCount;
    }
    
    public void setModifyMonitoredItemsCount(ServiceCounterDataType ModifyMonitoredItemsCount)
    {
        this.ModifyMonitoredItemsCount = ModifyMonitoredItemsCount;
    }
    
    public ServiceCounterDataType getSetMonitoringModeCount()
    {
        return SetMonitoringModeCount;
    }
    
    public void setSetMonitoringModeCount(ServiceCounterDataType SetMonitoringModeCount)
    {
        this.SetMonitoringModeCount = SetMonitoringModeCount;
    }
    
    public ServiceCounterDataType getSetTriggeringCount()
    {
        return SetTriggeringCount;
    }
    
    public void setSetTriggeringCount(ServiceCounterDataType SetTriggeringCount)
    {
        this.SetTriggeringCount = SetTriggeringCount;
    }
    
    public ServiceCounterDataType getDeleteMonitoredItemsCount()
    {
        return DeleteMonitoredItemsCount;
    }
    
    public void setDeleteMonitoredItemsCount(ServiceCounterDataType DeleteMonitoredItemsCount)
    {
        this.DeleteMonitoredItemsCount = DeleteMonitoredItemsCount;
    }
    
    public ServiceCounterDataType getCreateSubscriptionCount()
    {
        return CreateSubscriptionCount;
    }
    
    public void setCreateSubscriptionCount(ServiceCounterDataType CreateSubscriptionCount)
    {
        this.CreateSubscriptionCount = CreateSubscriptionCount;
    }
    
    public ServiceCounterDataType getModifySubscriptionCount()
    {
        return ModifySubscriptionCount;
    }
    
    public void setModifySubscriptionCount(ServiceCounterDataType ModifySubscriptionCount)
    {
        this.ModifySubscriptionCount = ModifySubscriptionCount;
    }
    
    public ServiceCounterDataType getSetPublishingModeCount()
    {
        return SetPublishingModeCount;
    }
    
    public void setSetPublishingModeCount(ServiceCounterDataType SetPublishingModeCount)
    {
        this.SetPublishingModeCount = SetPublishingModeCount;
    }
    
    public ServiceCounterDataType getPublishCount()
    {
        return PublishCount;
    }
    
    public void setPublishCount(ServiceCounterDataType PublishCount)
    {
        this.PublishCount = PublishCount;
    }
    
    public ServiceCounterDataType getRepublishCount()
    {
        return RepublishCount;
    }
    
    public void setRepublishCount(ServiceCounterDataType RepublishCount)
    {
        this.RepublishCount = RepublishCount;
    }
    
    public ServiceCounterDataType getTransferSubscriptionsCount()
    {
        return TransferSubscriptionsCount;
    }
    
    public void setTransferSubscriptionsCount(ServiceCounterDataType TransferSubscriptionsCount)
    {
        this.TransferSubscriptionsCount = TransferSubscriptionsCount;
    }
    
    public ServiceCounterDataType getDeleteSubscriptionsCount()
    {
        return DeleteSubscriptionsCount;
    }
    
    public void setDeleteSubscriptionsCount(ServiceCounterDataType DeleteSubscriptionsCount)
    {
        this.DeleteSubscriptionsCount = DeleteSubscriptionsCount;
    }
    
    public ServiceCounterDataType getAddNodesCount()
    {
        return AddNodesCount;
    }
    
    public void setAddNodesCount(ServiceCounterDataType AddNodesCount)
    {
        this.AddNodesCount = AddNodesCount;
    }
    
    public ServiceCounterDataType getAddReferencesCount()
    {
        return AddReferencesCount;
    }
    
    public void setAddReferencesCount(ServiceCounterDataType AddReferencesCount)
    {
        this.AddReferencesCount = AddReferencesCount;
    }
    
    public ServiceCounterDataType getDeleteNodesCount()
    {
        return DeleteNodesCount;
    }
    
    public void setDeleteNodesCount(ServiceCounterDataType DeleteNodesCount)
    {
        this.DeleteNodesCount = DeleteNodesCount;
    }
    
    public ServiceCounterDataType getDeleteReferencesCount()
    {
        return DeleteReferencesCount;
    }
    
    public void setDeleteReferencesCount(ServiceCounterDataType DeleteReferencesCount)
    {
        this.DeleteReferencesCount = DeleteReferencesCount;
    }
    
    public ServiceCounterDataType getBrowseCount()
    {
        return BrowseCount;
    }
    
    public void setBrowseCount(ServiceCounterDataType BrowseCount)
    {
        this.BrowseCount = BrowseCount;
    }
    
    public ServiceCounterDataType getBrowseNextCount()
    {
        return BrowseNextCount;
    }
    
    public void setBrowseNextCount(ServiceCounterDataType BrowseNextCount)
    {
        this.BrowseNextCount = BrowseNextCount;
    }
    
    public ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount()
    {
        return TranslateBrowsePathsToNodeIdsCount;
    }
    
    public void setTranslateBrowsePathsToNodeIdsCount(ServiceCounterDataType TranslateBrowsePathsToNodeIdsCount)
    {
        this.TranslateBrowsePathsToNodeIdsCount = TranslateBrowsePathsToNodeIdsCount;
    }
    
    public ServiceCounterDataType getQueryFirstCount()
    {
        return QueryFirstCount;
    }
    
    public void setQueryFirstCount(ServiceCounterDataType QueryFirstCount)
    {
        this.QueryFirstCount = QueryFirstCount;
    }
    
    public ServiceCounterDataType getQueryNextCount()
    {
        return QueryNextCount;
    }
    
    public void setQueryNextCount(ServiceCounterDataType QueryNextCount)
    {
        this.QueryNextCount = QueryNextCount;
    }
    
    public ServiceCounterDataType getRegisterNodesCount()
    {
        return RegisterNodesCount;
    }
    
    public void setRegisterNodesCount(ServiceCounterDataType RegisterNodesCount)
    {
        this.RegisterNodesCount = RegisterNodesCount;
    }
    
    public ServiceCounterDataType getUnregisterNodesCount()
    {
        return UnregisterNodesCount;
    }
    
    public void setUnregisterNodesCount(ServiceCounterDataType UnregisterNodesCount)
    {
        this.UnregisterNodesCount = UnregisterNodesCount;
    }
    
    /**
      * Deep clone
      *
      * @return cloned SessionDiagnosticsDataType
      */
    public SessionDiagnosticsDataType clone()
    {
        SessionDiagnosticsDataType result = new SessionDiagnosticsDataType();
        result.SessionId = SessionId;
        result.SessionName = SessionName;
        result.ClientDescription = ClientDescription==null ? null : ClientDescription.clone();
        result.ServerUri = ServerUri;
        result.EndpointUrl = EndpointUrl;
        result.LocaleIds = LocaleIds==null ? null : LocaleIds.clone();
        result.ActualSessionTimeout = ActualSessionTimeout;
        result.MaxResponseMessageSize = MaxResponseMessageSize;
        result.ClientConnectionTime = ClientConnectionTime;
        result.ClientLastContactTime = ClientLastContactTime;
        result.CurrentSubscriptionsCount = CurrentSubscriptionsCount;
        result.CurrentMonitoredItemsCount = CurrentMonitoredItemsCount;
        result.CurrentPublishRequestsInQueue = CurrentPublishRequestsInQueue;
        result.TotalRequestCount = TotalRequestCount==null ? null : TotalRequestCount.clone();
        result.UnauthorizedRequestCount = UnauthorizedRequestCount;
        result.ReadCount = ReadCount==null ? null : ReadCount.clone();
        result.HistoryReadCount = HistoryReadCount==null ? null : HistoryReadCount.clone();
        result.WriteCount = WriteCount==null ? null : WriteCount.clone();
        result.HistoryUpdateCount = HistoryUpdateCount==null ? null : HistoryUpdateCount.clone();
        result.CallCount = CallCount==null ? null : CallCount.clone();
        result.CreateMonitoredItemsCount = CreateMonitoredItemsCount==null ? null : CreateMonitoredItemsCount.clone();
        result.ModifyMonitoredItemsCount = ModifyMonitoredItemsCount==null ? null : ModifyMonitoredItemsCount.clone();
        result.SetMonitoringModeCount = SetMonitoringModeCount==null ? null : SetMonitoringModeCount.clone();
        result.SetTriggeringCount = SetTriggeringCount==null ? null : SetTriggeringCount.clone();
        result.DeleteMonitoredItemsCount = DeleteMonitoredItemsCount==null ? null : DeleteMonitoredItemsCount.clone();
        result.CreateSubscriptionCount = CreateSubscriptionCount==null ? null : CreateSubscriptionCount.clone();
        result.ModifySubscriptionCount = ModifySubscriptionCount==null ? null : ModifySubscriptionCount.clone();
        result.SetPublishingModeCount = SetPublishingModeCount==null ? null : SetPublishingModeCount.clone();
        result.PublishCount = PublishCount==null ? null : PublishCount.clone();
        result.RepublishCount = RepublishCount==null ? null : RepublishCount.clone();
        result.TransferSubscriptionsCount = TransferSubscriptionsCount==null ? null : TransferSubscriptionsCount.clone();
        result.DeleteSubscriptionsCount = DeleteSubscriptionsCount==null ? null : DeleteSubscriptionsCount.clone();
        result.AddNodesCount = AddNodesCount==null ? null : AddNodesCount.clone();
        result.AddReferencesCount = AddReferencesCount==null ? null : AddReferencesCount.clone();
        result.DeleteNodesCount = DeleteNodesCount==null ? null : DeleteNodesCount.clone();
        result.DeleteReferencesCount = DeleteReferencesCount==null ? null : DeleteReferencesCount.clone();
        result.BrowseCount = BrowseCount==null ? null : BrowseCount.clone();
        result.BrowseNextCount = BrowseNextCount==null ? null : BrowseNextCount.clone();
        result.TranslateBrowsePathsToNodeIdsCount = TranslateBrowsePathsToNodeIdsCount==null ? null : TranslateBrowsePathsToNodeIdsCount.clone();
        result.QueryFirstCount = QueryFirstCount==null ? null : QueryFirstCount.clone();
        result.QueryNextCount = QueryNextCount==null ? null : QueryNextCount.clone();
        result.RegisterNodesCount = RegisterNodesCount==null ? null : RegisterNodesCount.clone();
        result.UnregisterNodesCount = UnregisterNodesCount==null ? null : UnregisterNodesCount.clone();
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
		return "SessionDiagnosticsDataType: "+ObjectUtils.printFieldsDeep(this);
	}

}
