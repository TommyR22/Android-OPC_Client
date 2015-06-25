package com.project.opc;

import java.util.ArrayList;

import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.CreateSubscriptionRequest;
import org.opcfoundation.ua.core.CreateSubscriptionResponse;
import android.widget.ListView;

public class Subscription {
	
		static UnsignedByte Default_Priority = new UnsignedByte(0);
	    static Double Default_RequestedPublishingInterval = Double.valueOf(1000);
	    static UnsignedInteger Default_RequestedMaxKeepAliveCount = new UnsignedInteger(5);
		// RequestedLifetimeCount > 3 * RequestedMaxKeepAliveCount
	    static UnsignedInteger Default_RequestedLifetimeCount = new UnsignedInteger(40); 
	    // Zero notifications per publish significa nessun limite
	    static UnsignedInteger Default_MaxNotificationsPerPublish = new UnsignedInteger(0);
	    static Boolean Default_PublishingEnabled = true;

	    private Double requestedPublishingInterval = Default_RequestedPublishingInterval;
	    private UnsignedInteger requestedLifetimeCount = Default_RequestedLifetimeCount;
	    private UnsignedInteger requestedMaxKeepAliveCount = Default_RequestedMaxKeepAliveCount;
	    private UnsignedInteger maxNotificationsPerPublish = Default_MaxNotificationsPerPublish;
	    private Boolean publishingEnabled = Default_PublishingEnabled;
	    
	    public ArrayList<MonitoredItem> listMonitoredItem = new ArrayList<MonitoredItem>();
		
	    
		static int Default_PRRate = 1000;
		
		private CreateSubscriptionRequest request;
		private CreateSubscriptionResponse response;
		  
		private UnsignedInteger subId;
		private CustomAdapterMonitoredItem adap;
		private ListView list_m;
		
		public CustomAdapterMonitoredItem getAdap() {
			return adap;
		}
		public void setAdap(CustomAdapterMonitoredItem adap) {
			this.adap = adap;
		}
		public Double getRequestedPublishingInterval() {
			return requestedPublishingInterval;
		}
		public void setRequestedPublishingInterval(Double requestedPublishingInterval) {
			this.requestedPublishingInterval = requestedPublishingInterval;
		}
		public UnsignedInteger getRequestedLifetimeCount() {
			return requestedLifetimeCount;
		}
		public void setRequestedLifetimeCount(UnsignedInteger requestedLifetimeCount) {
			this.requestedLifetimeCount = requestedLifetimeCount;
		}
		public UnsignedInteger getRequestedMaxKeepAliveCount() {
			return requestedMaxKeepAliveCount;
		}
		public void setRequestedMaxKeepAliveCount(
				UnsignedInteger requestedMaxKeepAliveCount) {
			this.requestedMaxKeepAliveCount = requestedMaxKeepAliveCount;
		}
		public UnsignedInteger getMaxNotificationsPerPublish() {
			return maxNotificationsPerPublish;
		}
		public void setMaxNotificationsPerPublish(
				UnsignedInteger maxNotificationsPerPublish) {
			this.maxNotificationsPerPublish = maxNotificationsPerPublish;
		}
		public Boolean getPublishingEnabled() {
			return publishingEnabled;
		}
		public void setPublishingEnabled(Boolean publishingEnabled) {
			this.publishingEnabled = publishingEnabled;
		}
		public UnsignedInteger getSubId() {
			return subId;
		}
		public void setSubId(UnsignedInteger subId) {
			this.subId = subId;
		}
		public CreateSubscriptionRequest getRequest() {
			return request;
		}
		public void setRequest(CreateSubscriptionRequest request) {
			this.request = request;
		}
		public CreateSubscriptionResponse getResponse() {
			return response;
		}
		public void setResponse(CreateSubscriptionResponse response) {
			this.response = response;
		}
		 
		public MonitoredItem getCorrectItem(String id){
			for(MonitoredItem mi :listMonitoredItem){
				if(mi.getId_M().equals(id)){
					return mi;
				}
			}
			return null;
		}

		public void setadapterM(CustomAdapterMonitoredItem adapter_m) {
			adap = adapter_m;
		}
		public ListView getList_m() {
			return list_m;
		}
		public void setList_m(ListView list_m) {
			this.list_m = list_m;
		}
}
