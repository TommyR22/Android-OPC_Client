package com.project.opc;

import org.opcfoundation.ua.application.SessionChannel;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.CreateMonitoredItemsRequest;

public class MonitoredItem {
	static int Default_PRRate = 1000;
	static UnsignedInteger Default_QueueSize = new UnsignedInteger(4);
	static Double Default_AbsoluteDeadBand = Double.valueOf(0.01);
	static Double Default_PercentDeadBand = Double.valueOf(0.50);
	static Double Default_SamplingInterval = Double.valueOf(1000);
	static Boolean Default_DiscardOldest = false;
	
	private int namespace;
	private int nodeid;
	private Double input_sampling;
	private UnsignedInteger input_queue;
	private Boolean input_discard;
	private String input_deadband;
	private Double percentDeadBand;
	private Double absoluteDeadBand;
	
	private CreateMonitoredItemsRequest mir;
	private UnsignedInteger subId;
	private UnsignedInteger LastSubId;
	private UnsignedInteger LastSeqNumber;
	
	private SessionChannel mySession;	
	private Object viewholder;

	private String id_M;
	private String status_M;
	private String value_M;
	private String time_M;

	
	
	public int getNamespace() {
		return namespace;
	}
	public void setNamespace(int namespace) {
		this.namespace = namespace;
	}
	public int getNodeid() {
		return nodeid;
	}
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
	public Double getInput_sampling() {
		return input_sampling;
	}
	public void setInput_sampling(Double input_sampling) {
		this.input_sampling = input_sampling;
	}
	public UnsignedInteger getInput_queue() {
		return input_queue;
	}
	public void setInput_queue(UnsignedInteger input_queue) {
		this.input_queue = input_queue;
	}
	public Boolean getInput_discard() {
		return input_discard;
	}
	public void setInput_discard(Boolean input_discard) {
		this.input_discard = input_discard;
	}
	public String getInput_deadband() {
		return input_deadband;
	}
	public void setInput_deadband(String input_deadband) {
		this.input_deadband = input_deadband;
	}
	public UnsignedInteger getSubId() {
		return subId;
	}
	public void setSubId(UnsignedInteger subId) {
		this.subId = subId;
	}
	public Double getPercentDeadBand() {
		return percentDeadBand;
	}
	public void setPercentDeadBand(Double percentDeadBand) {
		this.percentDeadBand = percentDeadBand;
	}
	public Double getAbsoluteDeadBand() {
		return absoluteDeadBand;
	}
	public void setAbsoluteDeadBand(Double absoluteDeadBand) {
		this.absoluteDeadBand = absoluteDeadBand;
	}
	public SessionChannel getMySession() {
		return mySession;
	}
	public void setMySession(SessionChannel mySession) {
		this.mySession = mySession;
	}
	public CreateMonitoredItemsRequest getMir() {
		return mir;
	}
	public void setMir(CreateMonitoredItemsRequest mir) {
		this.mir=mir;	
	}
	public String getId_M() {
		return id_M;
	}
	public void setId_M(String id_M) {
		this.id_M = id_M;
	}
	public String getStatus_M() {
		return status_M;
	}
	public void setStatus_M(String status_M) {
		this.status_M = status_M;
	}
	public String getValue_M() {
		return value_M;
	}
	public void setValue_M(String value_M) {
		this.value_M = value_M;
	}
	public String getTime_M() {
		return time_M;
	}
	public void setTime_M(String time_M) {
		this.time_M = time_M;
	}
	public Object getViewholder() {
		return viewholder;
	}
	public void setViewholder(Object holder) {
		this.viewholder = viewholder;	
	}
}
