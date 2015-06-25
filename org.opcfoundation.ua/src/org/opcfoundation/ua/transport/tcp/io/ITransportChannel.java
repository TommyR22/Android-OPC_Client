/* ========================================================================
 * Copyright (c) 2005-2013 The OPC Foundation, Inc. All rights reserved.
 *
 * OPC Reciprocal Community License ("RCL") Version 1.00
 * 
 * Unless explicitly acquired and licensed from Licensor under another 
 * license, the contents of this file are subject to the Reciprocal 
 * Community License ("RCL") Version 1.00, or subsequent versions as 
 * allowed by the RCL, and You may not copy or use this file in either 
 * source code or executable form, except in compliance with the terms and 
 * conditions of the RCL.
 * 
 * All software distributed under the RCL is provided strictly on an 
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, 
 * AND LICENSOR HEREBY DISCLAIMS ALL SUCH WARRANTIES, INCLUDING WITHOUT 
 * LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE, QUIET ENJOYMENT, OR NON-INFRINGEMENT. See the RCL for specific 
 * language governing rights and limitations under the RCL.
 *
 * The complete license agreement can be found here:
 * http://opcfoundation.org/License/RCL/1.00/
 * ======================================================================*/

package org.opcfoundation.ua.transport.tcp.io;

import java.util.EnumSet;

import org.opcfoundation.ua.builtintypes.ServiceRequest;
import org.opcfoundation.ua.common.ServiceMessageContext;
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.EndpointConfiguration;
import org.opcfoundation.ua.core.EndpointDescription;
import org.opcfoundation.ua.core.StatusCodes;
import org.opcfoundation.ua.encoding.IEncodeable;
import org.opcfoundation.ua.transport.AsyncResult;
import org.opcfoundation.ua.transport.TransportChannelSettings;

public interface ITransportChannel {
	
	/**
	 * Initialize a secure channel with endpoint identified by the URL.
	 * 
	 * @param url
	 * @param settings
	 */
	void initialize(String url, TransportChannelSettings settings) throws ServiceResultException;
	
	/**
	 * Open the secure channel with the endpoint identified by the URL.
	 * Once the channel is open if will be assigned a secure channel id.
	 * 
	 * The channel must be initialzied before hand.
	 * 
	 * If the operation timeouts or the thread is interrupted a 
	 * ServiceResultException is thrown with {@link StatusCodes#Bad_Timeout}.<p>
	 * 
	 * @throws ServiceResultException
	 */
	void open() throws ServiceResultException;
	
	/**
	 * Open the secure channel asynchronously. 
	 *  
	 * @return async result 
	 */
	AsyncResult openAsync();
	
	/**
	 * Closes any existing secure channel and opens a new one. 
	 * Calling this method will cause outstanding requests over the current 
	 * secure channel to fail. <p> 
	 * 
	 * If the operation timeouts or the thread is interrupted a 
	 * ServiceResultException is thrown with {@link StatusCodes#Bad_Timeout}.<p>
	 * 
	 * @throws ServiceResultException communication error occurs
	 */
	void reconnect() throws ServiceResultException;
	
	/**
	 * Closes any existing secure channel and opens a new one. 
	 * Calling this method will cause outstanding requests over the current 
	 * secure channel to fail. 

	 * @return async result
	 */
	AsyncResult reconnectAsync();

	/**
	 * Send a service request over the secure channel. <p>
	 *  
	 * If the operation timeouts or the thread is interrupted a 
	 * ServiceResultException is thrown with {@link StatusCodes#Bad_Timeout}.<p>
	 * 
	 * @param request
	 * @return
	 * @throws ServiceResultException
	 */
	IEncodeable serviceRequest(ServiceRequest request) throws ServiceResultException;
	
	/**
	 * Asynchronous operation to send a request over the secure channel. 
	 * 
	 * @param request the request
	 * @return the result
	 */
	AsyncResult serviceRequestAsync(ServiceRequest request);

	/**
	 * Send a service request over the secure channel. <p>
	 *  
	 * If the operation timeouts or the operation is interrupted and a 
	 * ServiceResultException is thrown with {@link StatusCodes#Bad_Timeout}.<p>
	 * 
	 * @param request
	 * @param operationTimeout timeout time in milliseconds
	 * @return
	 * @throws ServiceResultException
	 */
	IEncodeable serviceRequest(ServiceRequest request, long operationTimeout) throws ServiceResultException;
	
	/**
	 * Asynchronous operation to send a request over the secure channel. 
	 * 
	 * @param request the request
	 * @param operationTimeout timeout time
	 * @return the result
	 */
	AsyncResult serviceRequestAsync(ServiceRequest request, long operationTimeout);
	
	/**
	 * Closes any existing secure channel and opens a new one. 
	 * Calling this method will cause outstanding requests over the current 
	 * secure channel to fail. <p> 
	 * 
	 * If the operation timeouts or the thread is interrupted a 
	 * ServiceResultException is thrown with {@link StatusCodes#Bad_Timeout}.<p>
	 * 
	 * @throws ServiceResultException communication error occurs
	 * @throws RuntimeInterruptedException the user interrupted the operation with {@link Thread#interrupt()}
	 */
	void close() throws ServiceResultException;
	
	/**
	 * Closes any existing secure channel and opens a new one. 
	 * Calling this method will cause outstanding requests over the current 
	 * secure channel to fail. 

	 * @return async result
	 */
	AsyncResult closeAsync();
	
	
	/**
	 * Get a list of features supported by the channel. 
	 * 
	 * @return
	 */
	EnumSet<TransportChannelFeature> getSupportedFeatures();
	public enum TransportChannelFeature { open, openAsync, reconnect, reconnectAsync, sendRequest, sendRequestAsync, close, closeAync }	

	EndpointDescription getEndpointDescription();
	
	EndpointConfiguration getEndpointConfiguration();
	
	ServiceMessageContext getMessageContext();
	
	/**
	 * Set operation timeout
	 * 
	 * @param timeout in milliseconds
	 */
	void setOperationTimeout(int timeout);
	
	/**
	 * Get operation timeout
	 * 
	 * @return timeout in milliseconds
	 */
	int getOperationTimeout();
	
	void dispose();
	
}
