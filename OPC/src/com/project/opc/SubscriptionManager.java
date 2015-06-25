package com.project.opc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.opcfoundation.ua.application.SessionChannel;
import org.opcfoundation.ua.builtintypes.ExtensionObject;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.DataChangeNotification;
import org.opcfoundation.ua.core.MonitoredItemNotification;
import org.opcfoundation.ua.core.NotificationMessage;
import org.opcfoundation.ua.core.PublishResponse;
import org.opcfoundation.ua.core.SubscriptionAcknowledgement;

import com.project.opc.CustomAdapter.ViewHolder;

import android.util.Log;
import android.view.TextureView;

public class SubscriptionManager {
	
	
	private static SubscriptionManager instance = null;
	List<SubscriptionAcknowledgement> sac =  new ArrayList<SubscriptionAcknowledgement>();
	private ArrayList<Subscription> mapSubscription;
	private long PRRate = 1000;
	
	public long getPRRate() {
		return PRRate;
	}

	public void setPRRate(Double pRRate) {
		//fare un controllo
		if(pRRate.longValue()<this.PRRate)
		PRRate = pRRate.longValue();
	}

	public ArrayList<Subscription> getMapSubscription() {
		return mapSubscription;
	}

	public void setMapSubscription(ArrayList<Subscription> mapSubscription) {
		this.mapSubscription = mapSubscription;
	}

	public static SubscriptionManager getInstance(SessionChannel mySession) {
	      if(instance == null) {
	         instance = new SubscriptionManager();
	         instance.mapSubscription = new ArrayList<Subscription>();
	         instance.active(mySession);
	      }
	      return instance;
	   }
	
	
	public void active(final SessionChannel mySession){
		try{
			Log.i("MMII","\n\nSUBSCRIPTION'S NOTIFICATION REPORT\n");
			new Thread(new Runnable() {
				@Override
				public void run() {
					ExecutorService cachedPool = Executors.newCachedThreadPool();
					while(true) {
						try {
							final SubscriptionAcknowledgement[] saca = new SubscriptionAcknowledgement[sac.size()];
							sac.toArray(saca);
							for(int x=0;x<sac.size();x++){
								Runnable aRunnable = new Runnable(){
						           @Override
						           public void run() {
						        	   try{
						        		   PublishResponse publishResponse = mySession.Publish(null, saca);
						        		   UnsignedInteger LastSubId = publishResponse.getSubscriptionId();
						        		   UnsignedInteger LastSeqNumber = publishResponse.getNotificationMessage().getSequenceNumber();
						        		   if(getCorrectAck(LastSubId)!=null){
						        			   getCorrectAck(LastSubId).setSequenceNumber(LastSubId);
						        			   Log.w("MMII","\n-----------------------------------------------------");
						        			   Log.w("MMII","Ho Ricevuto un NOTIFICATION MESSAGE relativo alla SUBSCRIPTION "+ LastSubId);
						        			   Log.w("MMII","Il suo Sequence number: " + LastSeqNumber);
						        		   }
						        		   
//						        		   if(publishResponse.getAvailableSequenceNumbers().length != 0){
//						        			   Log.w("MMII","\n-----------------------------------------------------");
//						        			   Log.w("MMII","[NOTIFICATION MESSAGE] SUBSCRIPTION "+ LastSubId);
//						        			   Log.w("MMII","Sequence number: " + LastSeqNumber);
//						        			   for (UnsignedInteger x : publishResponse.getAvailableSequenceNumbers()){
//						        				   Log.w("MMII","Available sequence number: " + x);
//						        			   }
//						        		   }
																
						        		   NotificationMessage nm = publishResponse.getNotificationMessage();
						        		   ExtensionObject[] ex = nm.getNotificationData();
						        		   for(ExtensionObject ob : ex) {
						        			   Object change = ob.decode();	
						        			   if(change instanceof DataChangeNotification) {
						        				   DataChangeNotification dataChange = (DataChangeNotification)change;
						        				   MonitoredItemNotification[] mnchange = dataChange.getMonitoredItems();
						        				   // vengono estratti tutti i Notification relativi ai monitoreItem DataChange										
						        				   for(MonitoredItemNotification monitoredItemNotification : mnchange){
						        					   Subscription tempsub = getCorrectSubscription(LastSubId);
						        					   MonitoredItem temp = tempsub.getCorrectItem(monitoredItemNotification.getClientHandle().toString());
						        					   if(temp!=null){
						        						   temp.setValue_M( monitoredItemNotification.getValue().getValue().toString());
						        						   temp.setTime_M(monitoredItemNotification.getValue().getServerTimestamp().toString());
						        						   temp.setStatus_M( monitoredItemNotification.getValue().getStatusCode().toString());
						        						   tempsub.getAdap().update();
						        					   }
						        					   Log.w("MMII","\n Client Handle del Monitored Item ricevuto : "+ monitoredItemNotification.getClientHandle());
						        					   Log.w("MMII","    Value: "	+ monitoredItemNotification.getValue().getValue());
						        					   Log.w("MMII","    Server TimeStamp:" + monitoredItemNotification.getValue().getServerTimestamp());
						        					   Log.w("MMII","    Status: " + monitoredItemNotification.getValue().getStatusCode());
						        				   }
						        			   }
						        		   }
							
						        	   }catch(Exception e){
						        		   
						        	   }
						           }
								};
							 
								//Future<?> runnableFuture = cachedPool.submit(aRunnable);
								cachedPool.submit(aRunnable);
							 
							}
							Thread.sleep(PRRate);
						}
						catch (InterruptedException e) {} 
						catch (Exception e) {};
					}
				}
			}).start();
		}catch(Exception e){}			
	}

	public SubscriptionAcknowledgement getCorrectAck(UnsignedInteger tmp){
		SubscriptionAcknowledgement ret=null;
		for(SubscriptionAcknowledgement s :sac){
			if(s.getSubscriptionId()==tmp){
				ret=s;
			}
		}
		return ret;
	}
	
	public Subscription getCorrectSubscription(UnsignedInteger tmp){
		Subscription ret=null;
		for(Subscription s :mapSubscription){
			if(s.getSubId()==tmp){
				ret=s;
			}
		}
		return ret;
	}
}
