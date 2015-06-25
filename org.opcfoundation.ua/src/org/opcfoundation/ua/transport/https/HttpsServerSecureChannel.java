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
package org.opcfoundation.ua.transport.https;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.opcfoundation.ua.application.Server;
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.MessageSecurityMode;
import org.opcfoundation.ua.transport.AsyncResult;
import org.opcfoundation.ua.transport.CloseableObjectState;
import org.opcfoundation.ua.transport.Endpoint;
import org.opcfoundation.ua.transport.EndpointBinding;
import org.opcfoundation.ua.transport.ServerConnection;
import org.opcfoundation.ua.transport.ServerSecureChannel;
import org.opcfoundation.ua.transport.endpoint.EndpointServiceRequest;
import org.opcfoundation.ua.transport.impl.AsyncResultImpl;
import org.opcfoundation.ua.transport.security.SecurityConfiguration;
import org.opcfoundation.ua.transport.security.SecurityPolicy;
import org.opcfoundation.ua.transport.tcp.nio.PendingRequest;
import org.opcfoundation.ua.utils.AbstractState;
import org.opcfoundation.ua.utils.StackUtils;

public class HttpsServerSecureChannel extends AbstractState<CloseableObjectState, ServiceResultException> implements ServerSecureChannel {

	/** Logger */
	static Logger log = Logger.getLogger(HttpsServerSecureChannel.class);
		
	/** Globally Unique Secure Channel ID */
	int secureChannelId = -1;
	/** Security Configuration */
	SecurityConfiguration securityConfiguration;
	/** Endpoint binding */
	EndpointBinding endpointBinding;
	/** Https Endpoint Handler */
	HttpsServerEndpointHandler httpsEndpointHandler;
	/** The error - In case the channel is in error state */
	Exception				error;
	/** Secure channel counter */
	AtomicInteger tokenIdCounter = new AtomicInteger();			
	/** Security Policy */
	SecurityPolicy securityPolicy;	
	/** The connection object that delivered the last message for this secure channel */
	HttpsServerConnection lastConnection;
	
	/**
	 * List on pending requests. All reads and writes are done by synchronizing to the
	 * requests object. 
	 */
	Map<Integer, PendingRequest> requests = new ConcurrentHashMap<Integer, PendingRequest>();
	
	public HttpsServerSecureChannel(HttpsServerEndpointHandler httpsEndpointHandler, int secureChannelId) {
		super(CloseableObjectState.Closed);
		this.endpointBinding = httpsEndpointHandler.endpointBinding;
		this.httpsEndpointHandler = httpsEndpointHandler;
		this.secureChannelId = secureChannelId;
	}

	public int getSecureChannelId() {
		return secureChannelId;
	}	

	public MessageSecurityMode getMessageSecurityMode() {
		return MessageSecurityMode.None;
	}

	public SecurityPolicy getSecurityPolicy() {
		return SecurityPolicy.NONE;
	}	
		
	public synchronized void setError(ServiceResultException e) {
		super.setError( e );
	}

	@Override
	protected void onListenerException(RuntimeException rte) {
		setError( StackUtils.toServiceResultException(rte) );
	}

	@Override
	public String toString() {
		CloseableObjectState s = getState();
		return "SecureChannel (state="+s+(s.isOpen()?", id="+secureChannelId:"")+")";
	}


	@Override
	public ServerConnection getConnection() {
		return lastConnection;
	}

	public String getConnectURL() {
		return endpointBinding.endpointAddress.getEndpointUrl();
	}

	@Override
	public boolean isOpen() {
		return getState().isOpen();
	}

	@Override
	public void close() {
		if (getState()!=CloseableObjectState.Open) return;				
		setState(CloseableObjectState.Closing);
		setState(CloseableObjectState.Closed);
		log.info("Channel closed: Id="+ secureChannelId);
		return;
	}

	@Override
	public AsyncResult closeAsync() {
		AsyncResultImpl result = new AsyncResultImpl(); 
		if (getState()!=CloseableObjectState.Open) {
			result.setResult(this);
			return result;				
		}
		setState(CloseableObjectState.Closing);
		setState(CloseableObjectState.Closed);
		result.setResult(this);
		return result;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void getPendingServiceRequests(Collection<EndpointServiceRequest<?, ?>> result) {
		Map<Integer, HttpsServerPendingRequest> snapshot = new HashMap<Integer, HttpsServerPendingRequest>( httpsEndpointHandler.pendingRequests );
		for (HttpsServerPendingRequest req : snapshot.values()) {
			if ( req.secureChannelId == secureChannelId ) result.add( req );
		}
	}

	@Override
	public Endpoint getEndpoint() {
		return endpointBinding.endpointAddress;
	}

	@Override
	public Server getServer() {
		return endpointBinding.serviceServer;
	}

	@Override
	public boolean needsCertificate() {
		return true;
	}
	
	// Removed - There are no proper secure channels in https //

	/*
	void handleOpenChannel(HttpsServerPendingRequest msgExchange, OpenSecureChannelRequest req) throws ServiceResultException {
		
		setState(CloseableObjectState.Opening);
		
		securityPolicy		= SecurityPolicy.getSecurityPolicy( msgExchange.securityPolicyUri );
		MessageSecurityMode messageMode		= req.getSecurityMode();
		SecurityMode securityMode			= new SecurityMode(securityPolicy, messageMode);
		if (!getEndpoint().supportsSecurityMode(securityMode)) {
			log.warn("The requested MessageSecurityMode("+messageMode+") is not supported by the endpoint");
			throw new ServiceResultException("The requested MessageSecurityMode("+messageMode+") is not supported by the endpoint");
		}
		
		securityConfiguration				= 
				new SecurityConfiguration(
					securityMode,
					endpointBinding.serviceServer.getApplicationInstanceCertificate(),
					null);
		
		SecurityToken token = createToken(msgExchange, req);		
		
		
		ChannelSecurityToken chanToken		= new ChannelSecurityToken();
		chanToken.setChannelId( UnsignedInteger.valueOf(secureChannelId) );
		chanToken.setCreatedAt( new DateTime() );
		chanToken.setRevisedLifetime(UnsignedInteger.valueOf(token.getLifeTime()));
		chanToken.setTokenId(UnsignedInteger.valueOf(token.getTokenId()));

		setState(CloseableObjectState.Open);	
		
		OpenSecureChannelResponse res = new OpenSecureChannelResponse();
		res.setResponseHeader(new ResponseHeader());
		res.setSecurityToken(chanToken);
		res.setServerNonce(token.getLocalNonce());
		res.setServerProtocolVersion( UnsignedInteger.valueOf( 0 ) );
		tokens.put( token.getTokenId(), token );
		setActiveSecurityToken(token);				
		msgExchange.sendResponse(res);
	}
	
	void handleRenewSecureChannelRequest(HttpsServerPendingRequest msgExchange, OpenSecureChannelRequest req) throws ServiceResultException {
		if ( !getState().isOpen() ) {
			msgExchange.sendException( new ServiceResultException( StatusCodes.Bad_SecureChannelClosed, "Failed to renew token, secure channel has already been closed." ) );
			return;
		}
		
		SecurityToken token = createToken(msgExchange, req);		
		ChannelSecurityToken chanToken		= new ChannelSecurityToken();
		chanToken.setChannelId( UnsignedInteger.valueOf(secureChannelId) );
		chanToken.setCreatedAt( new DateTime() );
		chanToken.setRevisedLifetime(UnsignedInteger.valueOf(token.getLifeTime()));
		chanToken.setTokenId(UnsignedInteger.valueOf(token.getTokenId()));
		
		
		OpenSecureChannelResponse res = new OpenSecureChannelResponse();
		res.setSecurityToken(chanToken);
		res.setServerNonce(token.getLocalNonce());
		res.setServerProtocolVersion( UnsignedInteger.valueOf( 0 ) );
		
		UnsignedInteger reqHandle = req.getRequestHeader() == null ? null : req.getRequestHeader().getRequestHandle();
		ResponseHeader header = new ResponseHeader();
		res.setResponseHeader( header );
		header.setRequestHandle( reqHandle );
		
		tokens.put( token.getTokenId(), token );
		msgExchange.sendResponse(res);
	}

	void handleCloseChannel(HttpsServerPendingRequest msgExchange, CloseSecureChannelRequest req) {
		close();	
		CloseSecureChannelResponse res = new CloseSecureChannelResponse();
		ResponseHeader header = new ResponseHeader();
		header.setRequestHandle( req.getRequestHeader().getRequestHandle() );
		res.setResponseHeader( header );
		msgExchange.sendResponse( res );
	}
	Map<Integer, SecurityToken> tokens = new ConcurrentHashMap<Integer, SecurityToken>();
	SecurityToken			activeToken;
	NodeId 				authenticationToken;

	public synchronized SecurityToken getSecurityToken(int tokenId) {
		if (log.isDebugEnabled())
			log.debug("tokens("+tokens.size()+")="+tokens.values());
		return tokens.get(tokenId);
	}
	
	private void pruneInvalidTokens()
	{	
		if (log.isDebugEnabled())
			log.debug("pruneInvalidTokens: tokens("+tokens.size()+")="+tokens.values());
		for (SecurityToken t : tokens.values())
			if (!t.isValid()) {
				if (log.isDebugEnabled())
					log.debug("pruneInvalidTokens: remove Id="+t.getTokenId());
				tokens.remove(t.getTokenId());
			}
	}	
	public synchronized SecurityToken getLatestNonExpiredToken()
	{
		SecurityToken result = null;
		for (SecurityToken t : tokens.values())
		{
			if (t.isExpired()) continue;
			if (result==null) result = t;
			if (t.getCreationTime() > result.getCreationTime()) result = t;
		}
		return result;
	}
	
	
	private SecurityToken createToken(HttpsServerPendingRequest msgExchange, OpenSecureChannelRequest req) throws ServiceResultException
	{
		byte[] clientNonce					= req.getClientNonce();
		int tokenId							= tokenIdCounter.incrementAndGet();				
		
		SecurityAlgorithm algo = securityPolicy.getAsymmetricEncryptionAlgorithm();
		int nonceLength = CryptoUtil.getNonceLength( algo );
		byte[] serverNonce = CryptoUtil.createNonce( nonceLength );
		
		final UnsignedInteger tokenLifetime = 
			req.getRequestedLifetime() != null && req.getRequestedLifetime().intValue() > 0 
				? req.getRequestedLifetime() 
				: StackUtils.SERVER_GIVEN_TOKEN_LIFETIME;
		log.debug("tokenLifetime: "+tokenLifetime);
		SecurityToken token = new SecurityToken(
				securityConfiguration, 
				secureChannelId,
				tokenId,
				System.currentTimeMillis(),
				tokenLifetime.longValue(),
				serverNonce,
				clientNonce
				);
		tokens.put(tokenId, token);

		return token;
	}
	
	public SecurityToken getActiveSecurityToken() {
		return activeToken;
	}
	
	public void setActiveSecurityToken(SecurityToken token) {
		if (token==null) 
			throw new IllegalArgumentException("null");
		if (log.isDebugEnabled())
			log.debug("Switching to new security token "+token.getTokenId());
		this.activeToken = token;
		pruneInvalidTokens();
	}
	public MessageSecurityMode getMessageSecurityMode() {
		SecurityToken token = getActiveSecurityToken();
		return token==null ? null : token.getMessageSecurityMode();
	}

	public SecurityPolicy getSecurityPolicy() {
		SecurityToken token = getActiveSecurityToken();
		return token==null ? null : token.getSecurityPolicy();
	}	
	public NodeId getAuthenticationToken() {
		return authenticationToken;
	}

	*/
}
