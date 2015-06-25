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

package org.opcfoundation.ua.examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Arrays;

import org.apache.log4j.PropertyConfigurator;
import org.opcfoundation.ua.application.Client;
import org.opcfoundation.ua.application.SessionChannel;
import org.opcfoundation.ua.builtintypes.ExtensionObject;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.common.ServiceFaultException;
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.Attributes;
import org.opcfoundation.ua.core.BrowseDescription;
import org.opcfoundation.ua.core.BrowseDirection;
import org.opcfoundation.ua.core.BrowseResponse;
import org.opcfoundation.ua.core.BrowseResultMask;
import org.opcfoundation.ua.core.CreateMonitoredItemsRequest;
import org.opcfoundation.ua.core.CreateMonitoredItemsResponse;
import org.opcfoundation.ua.core.CreateSubscriptionRequest;
import org.opcfoundation.ua.core.CreateSubscriptionResponse;
import org.opcfoundation.ua.core.DataChangeFilter;
import org.opcfoundation.ua.core.DataChangeNotification;
import org.opcfoundation.ua.core.DataChangeTrigger;
import org.opcfoundation.ua.core.DeadbandType;
import org.opcfoundation.ua.core.EndpointDescription;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.core.MessageSecurityMode;
import org.opcfoundation.ua.core.MonitoredItemCreateRequest;
import org.opcfoundation.ua.core.MonitoredItemNotification;
import org.opcfoundation.ua.core.MonitoringMode;
import org.opcfoundation.ua.core.MonitoringParameters;
import org.opcfoundation.ua.core.NodeClass;
import org.opcfoundation.ua.core.NotificationMessage;
import org.opcfoundation.ua.core.PublishResponse;
import org.opcfoundation.ua.core.ReadResponse;
import org.opcfoundation.ua.core.ReadValueId;
import org.opcfoundation.ua.core.SubscriptionAcknowledgement;
import org.opcfoundation.ua.core.TimestampsToReturn;
import org.opcfoundation.ua.examples.certs.ExampleKeys;
import org.opcfoundation.ua.transport.security.KeyPair;

import static org.opcfoundation.ua.utils.EndpointUtil.*;

/**
 * In this example, a client connects to a server, discovers endpoints, 
 * chooses one endpoint and connects to it.
 */
public class Cavalieri {

	
	//Parametro MaxAge usdato nella lettura delle variabili
    static Double MaxAge = new Double(500);

	//	FREQUENZA DI INVIO PUBLISH REQUEST  
	static int Default_PRRate = 1000;
	
	static SessionChannel mySession;
	static UnsignedInteger LastSeqNumber = new UnsignedInteger();
	static UnsignedInteger LastSubId = new UnsignedInteger();
	static UnsignedInteger subId = new UnsignedInteger();
	// il client handle viene usato per la creazione dei monitored item, viene assegnato a ogni monitoreditem
	//e' un intero > 0, non puo' assumere il valore 0
	static UnsignedInteger chandle=new UnsignedInteger(); 

	/*PARAMETRI SUBSCRIPTION
	 * 
	 * RequestedPublishingInterval : Intervallo di tempo, proposto dal client, 
	 *                               per la pubblicazione periodica dei messaggi di notifica.
	 * RequestedLifetimeCount : quante volte il publishing interval può trascorrere senza 
	 *                               che sia disponibile alcuna connessione verso il client 
	 *                               su cui inviare le notifiche. Passato questo lasso di tempo, 
	 *                               il server cancella la Subscription e libera le risorse. 
	 *                               Questo parametro dev’essere grande almeno 3 volte il keep alive 
	 *                               count, ed entrambi i parametri vengono negoziati da client e 
	 *                               server. 
	 * RequestedMaxKeepAlive : quante volte il publishing interval deve trascorrere senza che 
	 *                               sia disponibile alcuna notifica, perché il server mandi un 
	 *                               messaggio vuoto al client che gli comunichi di essere ancora 
	 *                               in esecuzione, ma senza notifiche disponibili. 
	 * CountMaxNotificationsPerPublish : serve a limitare il numero di notifiche inviate dal server 
	 *                               al client all’interno di un messaggio.
	 * PublishingEnabled : abilita o no il publishing
	 * Priority : priorità della sottoscrizione rispetto alle altre sottoscrizioni già create dal 
	 *                               client
	 */
    //Un valore di priorita' uguale a zero significa che il client non ha esigenze 
    // di priorita' circa la subscription che sta creando
    static UnsignedByte Default_Priority = new UnsignedByte(0);
    static Double Default_RequestedPublishingInterval = new Double(1000);
    static UnsignedInteger Default_RequestedMaxKeepAliveCount = new UnsignedInteger(5);
	// RequestedLifetimeCount > 3 * RequestedMaxKeepAliveCount
    static UnsignedInteger Default_RequestedLifetimeCount = new UnsignedInteger(40); 
    // Zero notifications per publish significa nessun limite
    static UnsignedInteger Default_MaxNotificationsPerPublish = new UnsignedInteger(0);
    static Boolean Default_PublishingEnabled = true;

    static Double requestedPublishingInterval = Default_RequestedPublishingInterval;
    static UnsignedInteger requestedLifetimeCount = Default_RequestedLifetimeCount;
    static UnsignedInteger requestedMaxKeepAliveCount = Default_RequestedMaxKeepAliveCount;
    static UnsignedInteger maxNotificationsPerPublish = Default_MaxNotificationsPerPublish;
    static Boolean publishingEnabled = Default_PublishingEnabled;

	/*PARAMETRI MONITORITED ITEM
	 * 	QueueSize : grandezza della coda per le notifiche
	 *  SamplingInterval : intervallo di campionamento della variabile da parte del server 
	 *  DiscardOldest : politica di gestione di buffer overflow
	 *  ClientHandle : identificativo per il riconoscimento del monitorited item
	 *  Deadband : variazione da notificare sul valore de monitored item
	 */
	static UnsignedInteger Default_QueueSize = new UnsignedInteger(4);
	static Double Default_AbsoluteDeadBand = new Double(0.01);
	static Double Default_PercentDeadBand = new Double(0.50);
	static Double Default_SamplingInterval = new Double(1000);
	static Boolean Default_DiscardOldest = false;

	static UnsignedInteger queueSize=Default_QueueSize;
	static Double  absoluteDeadBand=Default_AbsoluteDeadBand;
	static Double percentDeadBand=Default_PercentDeadBand;
	static Double samplingInterval=Default_SamplingInterval;
	static Boolean discardOldest=Default_DiscardOldest;
	
	static InputStreamReader reader = new InputStreamReader (System.in);
    static BufferedReader in = new BufferedReader (reader);
    
//  La funzione serve per produrre un particolare ReferenceType da utilizzare nel Browse
//  
    public static NodeId fromString_ToReferenceTypeId (String rT){
   	 // ReferenceType
   	
   	switch(rT){
   	  case "Aggregates": return(new NodeId(0,44));
   	  case "AlwaysGeneratesEvent": return(new NodeId(0,3056));
   	  case "FromState":return(new NodeId(0,51));
      case "GeneratesEvent": return(new NodeId(0,41));
      case "HasCause": return(new NodeId(0,53));
      case "HasChild": return(new NodeId(0,34));
      case "HasComponent": return(new NodeId(0,47));
      case "HasCondition": return(new NodeId(0,9006));
      case "HasDescription": return(new NodeId(0,39));
      case "HasEffect": return(new NodeId(0,54));
      case "HasEncoding": return(new NodeId(0,38));
      case "HasEventSource": return(new NodeId(0,36));
      case "HasFalseSubState": return(new NodeId(0,9005));
      case "HasHistoricalConfiguration": return(new NodeId(0,56));
      case "HasModellingRule": return(new NodeId(0,37));
      case "HasNotifier": return(new NodeId(0,48));
      case "HasOrderedComponent": return(new NodeId(0,49));
      case "HasProperty": return(new NodeId(0,46));
      case "HasSubStateMachine": return(new NodeId(0,117));
      case "HasSubtype": return(new NodeId(0,45));
      case "HasTrueSubState": return(new NodeId(0,9004));
      case "HasTypeDefinition": return(new NodeId(0,40));
      case "HierarchicalReferences": return(new NodeId(0,33));
      case "NonHierarchicalReferences": return(new NodeId(0,32));
      case "Organizes": return(new NodeId(0,35));
      case "References": return(new NodeId(0,31));
      case "ToState": return(new NodeId(0,52));
     
   	}
   	
   	return null;
   	
   }

	public static void main(String[] args) throws Exception {
	    String r, aux;
        int x, nc, ns, i,j;
	    
		
		// Load Log4j configurations from external file
		PropertyConfigurator.configure( Cavalieri.class.getResource("log.properties") );
		
		//////////////  CLIENT  //////////////
		// Load Client's Application Instance Certificate from file
		KeyPair myClientApplicationInstanceCertificate = ExampleKeys.getCert("Client");
		// Create Client
		Client myClient = Client.createClientApplication( myClientApplicationInstanceCertificate );
		//////////////////////////////////////		
		
		
		////////// DISCOVER ENDPOINT /////////
		// Discover server's endpoints, and choose one
		String publicHostname = InetAddress.getLocalHost().getHostName();	
		String url = "opc.tcp://"+publicHostname+":62541/QuickStarts"; // QuickStart Server (poichè conosco già l'indirizzo del server)
 
		EndpointDescription[] endpoints = myClient.discoverEndpoints(url);

   	    System.out.println("Ho contattato il Server "+url);
   	    System.out.println("Ho trovato l'elenco degli endpoint del Server ");
   	    System.out.println("Il numero di endpoint e' " + endpoints.length);
		// Sort endpoints by security level. The lowest level at the
		// beginning, the highest at the end of the array
	    endpoints = sortBySecurityLevel(endpoints);
   	    System.out.println("Ho ordinato gli endopoint per livello di sicurezza crescente ");
	    System.out.println("Visualizzazione degli endpoint ordinati in modo crescente per livello di sicurezza");
   	    for(nc = 0; nc < endpoints.length; nc++){
   	        System.out.println("Informazioni dell'endpoint n."+nc);
   	        System.out.println("URL  e' " + endpoints[nc].getEndpointUrl());
   	   /*   System.out.println("Server Certificate " + endpoints[nc].getServerCertificate());
   	        System.out.println("User Identity Tokens " + endpoints[nc].getUserIdentityTokens()); */
   	        System.out.println("Transport Protocol " + endpoints[nc].getTransportProfileUri());
   	        System.out.println("Security Mode " + endpoints[nc].getSecurityMode());
   	        System.out.println("Security Policy " + endpoints[nc].getSecurityPolicyUri());
   	        System.out.println("Security Level  " + endpoints[nc].getSecurityLevel());
   	    }	    
   	    // Filter out all but opc.tcp protocols
		endpoints = selectByProtocol(endpoints, "opc.tcp");
		System.out.println("Ho filtrato gli endopoints, lasciando solo quelli con Transport Protocol = opc.tcp");
   	    System.out.println("Il numero di endpoint rimasti e' " + endpoints.length);
	    System.out.println("Visualizzazione degli endpoint con Transport Protocol = opc.tcp");
 	    for(nc = 0; nc < endpoints.length; nc++){
	   	        System.out.println("Informazioni dell'endpoint n."+nc);
	   	        System.out.println("URL  e' " + endpoints[nc].getEndpointUrl());
	   	        System.out.println("Transport Protocol " + endpoints[nc].getTransportProfileUri());
	   	        System.out.println("Security Mode " + endpoints[nc].getSecurityMode());
	   	        System.out.println("Security Policy " + endpoints[nc].getSecurityPolicyUri());
	   	        System.out.println("Security Level  " + endpoints[nc].getSecurityLevel());
   	    }
		 
		 
		// Filter out all but None Security Mode endpoints
		endpoints = selectByMessageSecurityMode(endpoints,MessageSecurityMode.None);
		System.out.println("Ho filtrato gli endopoints, lasciando solo quelli con Security Mode = None ");
	    System.out.println("Il numero di endpoint rimasti e' " + endpoints.length);
	    System.out.println("Visualizzazione degli endpoint rimasti ");
   	    for(nc = 0; nc < endpoints.length; nc++){
   	        System.out.println("        Informazioni dell'endpoint n."+nc);
   	        System.out.println("        URL  e' " + endpoints[nc].getEndpointUrl());
   	        System.out.println("        Transport Protocol " + endpoints[nc].getTransportProfileUri());
   	        System.out.println("        Security Mode " + endpoints[nc].getSecurityMode());
   	        System.out.println("        Security Policy " + endpoints[nc].getSecurityPolicyUri());
   	        System.out.println("        Security Level  " + endpoints[nc].getSecurityLevel());
   	    }


   	    System.out.println("Quale endpoint scegli  ? ");
   	    x = Integer.parseInt(in.readLine());

   	    System.out.println("Ho selezionato l'endpoint n."+x);

   	    EndpointDescription endpoint = endpoints[x]; 
		
        ////////////CREATE SESSION  ////////////
		mySession = myClient.createSessionChannel(url,endpoint);
		System.out.println("Sessione Creata ");

		////////////ACTIVATE SESSION  ////////////
	    mySession.activate();
	
		System.out.println("Sessione Attivata");

       /////////////  EXECUTE  //////////////		

	       /////////////  LETTURA NAMESPACE ARRAY   //////////////		
		
	    System.out.println("Vuoi Leggere il Namespace Array  (y/n) ?");
 	    r = in.readLine();
		if (r.matches("y")) {
		       // Read Namespace Array
				ReadResponse res = mySession.Read(	//leggo il nodo del NamespaceArray
					null, 
					null, 
					TimestampsToReturn.Neither, 				
					new ReadValueId(Identifiers.Server_NamespaceArray, Attributes.Value, null, null ) 
				);
				String[] namespaceArray = (String[]) res.getResults()[0].getValue().getValue();
				System.out.println(Arrays.toString(namespaceArray));
		
		}
		

	       /////////////  BROWSING  //////////////		

	    System.out.println("Vuoi Fare il Browsing  (y/n) ?");
 	    r = in.readLine();
		if (r.matches("y")) {

		     BrowseDescription browse = new BrowseDescription();
		     //ObjectsFolder va alla cartella degli Oggetti, dove sono messi i Nodi
		     
		     browse.setNodeId( Identifiers.ObjectsFolder);
		     
//		     browse.setNodeId( Identifiers.RootFolder );
		     browse.setBrowseDirection( BrowseDirection.Forward );
		     browse.setIncludeSubtypes( true );
		     browse.setNodeClassMask( NodeClass.Object );	//solo i nodi Object

//		     browse.setNodeClassMask( NodeClass.ALL );
		     browse.setResultMask( BrowseResultMask.All );	//quali attributi vedere
	   
		     BrowseResponse res = mySession.Browse( null, null, null, browse );				
		     
	    	 System.out.println("Numero Totale di Results =  " + res.getResults().length);

  	         for (j=0; j< res.getResults().length; j++){
		     
	      	     System.out.println("Numero di Results =  " + j);
			     for(x = 0; x < res.getResults()[j].getReferences().length; x++){
		      	    System.out.println();
		    	    System.out.println("Name Space Index  " + res.getResults()[j].getReferences()[x].getNodeId().getNamespaceIndex());
		    	    System.out.println("Name Space URI " + res.getResults()[j].getReferences()[x].getNodeId().getNamespaceUri());
		    	    System.out.println("NodeID (namespace,id) " + res.getResults()[j].getReferences()[x].getNodeId());
		    	    System.out.println("BrowseName "+ res.getResults()[j].getReferences()[x].getBrowseName());
				    System.out.println("DisplayName " + res.getResults()[j].getReferences()[x].getDisplayName());
		        }
	        }
		     // Browsing as you want
        
		     do {
		    	 System.out.println("Inserisci namespace  ? ");
		    	 ns = Integer.parseInt(in.readLine());
		    	 System.out.println("Inserisci nodeId  ? ");
		    	 i = Integer.parseInt(in.readLine());

		    	 browse.setNodeId(new NodeId(ns,i));
		    	 
//		    	 browse.setBrowseDirection( BrowseDirection.Both);
//		    	 browse.setBrowseDirection( BrowseDirection.Inverse);
		    	 browse.setBrowseDirection( BrowseDirection.Forward );

 		         aux = "HasChild";
                 browse.setReferenceTypeId(fromString_ToReferenceTypeId(aux));
		    	 browse.setIncludeSubtypes( true );

		    	 //		         browse.setNodeClassMask( NodeClass.ALL );
		    	 browse.setNodeClassMask( NodeClass.Object, NodeClass.Variable );	//vedere solo nodi object e variable
		    	 browse.setResultMask( BrowseResultMask.All );
                 
		    	 res = mySession.Browse( null, null, null, browse );				
		
		    	 System.out.println("Numero Totale di Results =  " + res.getResults().length);
					 
		    	 for (j=0; j< res.getResults().length; j++){
				     
		      	     System.out.println("Numero di Results =  " + j);
				     for(x = 0; x < res.getResults()[j].getReferences().length; x++){
			    	    System.out.println();
			    	    System.out.println("Name Space Index  " + res.getResults()[j].getReferences()[x].getNodeId().getNamespaceIndex());
			    	    System.out.println("Name Space URI " + res.getResults()[j].getReferences()[x].getNodeId().getNamespaceUri());
			    	    System.out.println("NodeID (namespace,id) " + res.getResults()[j].getReferences()[x].getNodeId());
			    	    System.out.println("BrowseName "+ res.getResults()[j].getReferences()[x].getBrowseName());
					    System.out.println("DisplayName " + res.getResults()[j].getReferences()[x].getDisplayName());
			         }
		         }

		    	 System.out.println("Vuoi Continuare a fare il Browsing (y/n) ?");
		    	 r = in.readLine();
		     } while (r.equalsIgnoreCase("y"));

		}

		
	       /////////////  LETTURA VARIABILI   //////////////		

		
		System.out.println("Vuoi Fare la Lettura delle Variabili  (y/n) ?");
	    r = in.readLine();
		if (r.matches("y")) 
		     do {
		    	 // Variabili da Leggere 2,156 e 3,11
		    	 System.out.println("Lettura Valori di Variabili  ");
		    	 System.out.println("Inserisci namespace  ? ");
		    	 ns = Integer.parseInt(in.readLine());
		    	 System.out.println("Inserisci nodeId  ? ");
		    	 i = Integer.parseInt(in.readLine());
		    	 // Read a variable with namespaceindex ns, nodeId i 
		    	 ReadResponse res = mySession.Read(
					null, 
					MaxAge, 
					TimestampsToReturn.Source, 
					new ReadValueId(new NodeId(ns, i), Attributes.Value, null, null ) 
		    	 );		
		    	 System.out.println(res.getResults()[0].getValue());

		
		    	 System.out.println("Vuoi Continuare a Leggere (y/n) ?");
		    	 r = in.readLine();
		     } while (r.equalsIgnoreCase("y"));
		
	    
		/////////////  CREAZIONE DELLA SUBSCRIPTION  /////////////
	    
		
	    //Creo una subscription, in response ho l'identificativo assegnato dal server a ciascuna sottoscrizione
		
		System.out.println("\nInserisci i Parametri di Sottoscrizione :");
		System.out.println("Requested Publishing Interval [DEFAULT: " +
					Default_RequestedPublishingInterval + "]:");
		try{
			requestedPublishingInterval = Double.parseDouble(in.readLine());
		}catch(Exception e){
			requestedPublishingInterval = Default_RequestedPublishingInterval;
		}
		
		
		System.out.println("Requested Max Keep Alive Count [DEFAULT: " +
				Default_RequestedMaxKeepAliveCount + "]:");
		try{
			requestedMaxKeepAliveCount = UnsignedInteger.parseUnsignedInteger(in.readLine());
		}catch(Exception e){
			requestedMaxKeepAliveCount = Default_RequestedMaxKeepAliveCount;
		}
		
		// RequestedLifetimeCount > 3 * RequestedMaxKeepAliveCount
		
		do {
		   System.out.println("Requested Lifetime Count [DEFAULT: " +
				Default_RequestedLifetimeCount + "]:");
		   System.out.println("Nota: deve essere superiore a 3 volte il RequestedMaxKeepAliveCount ");
		   			
		   try{
			  requestedLifetimeCount = UnsignedInteger.parseUnsignedInteger(in.readLine());
		   }catch(Exception e){
			  requestedLifetimeCount = Default_RequestedLifetimeCount;
		   }
		} while (requestedLifetimeCount.compareTo(requestedMaxKeepAliveCount.intValue()*3)<=0);

		System.out.println("Maximum Number of Notifications in a Single Publish response [DEFAULT: " +
				Default_MaxNotificationsPerPublish + "]:");
		try{
			maxNotificationsPerPublish = UnsignedInteger.parseUnsignedInteger(in.readLine());
		}catch(Exception e){
			maxNotificationsPerPublish = Default_MaxNotificationsPerPublish;
		}

		System.out.println("Il parametro publishing Enabled viene fissato a True ");
		System.out.println("La Priorita' assegnata alla Subscription e' : " +
				Default_Priority);

		CreateSubscriptionRequest subscription = new CreateSubscriptionRequest(null, 
	  				requestedPublishingInterval, requestedLifetimeCount, 
	  				requestedMaxKeepAliveCount, maxNotificationsPerPublish, 
	  				Default_PublishingEnabled, Default_Priority);
  		CreateSubscriptionResponse response = mySession.CreateSubscription(subscription);
	  		
  		subId=response.getSubscriptionId();
  		
 	    System.out.println("Subscription Creata, il suo Id (SubscriptionId) e' :" + 
	  		  		subId);
	  		  		
  		System.out.println("    Il Revised Requested Publishing Interval:" + 
	  		response.getRevisedPublishingInterval());
	  		
  		System.out.println("    Il Revised Lifetime Count :" + 
	  		  		response.getRevisedLifetimeCount());
	  		
  		System.out.println("    Il Revised Max Keep Alive Count:" + 
	  		  		response.getRevisedMaxKeepAliveCount());
		
		//Creo una lista di monitored items 

		UnsignedInteger NumberMonitoredItems;
		UnsignedInteger Default_NumberMonitoredItems = new UnsignedInteger(1);
		
		System.out.println("Inserisci il Numero di Monitored Items da Creare [DEFAULT = 1 ]:");
		try{
			NumberMonitoredItems = UnsignedInteger.parseUnsignedInteger(in.readLine());
		}catch(Exception e){
			NumberMonitoredItems = Default_NumberMonitoredItems;
		}
		
		System.out.println("Numero di Monitored Items da creare "+NumberMonitoredItems.intValue());
		
		MonitoredItemCreateRequest[] monitoredItems = new MonitoredItemCreateRequest[NumberMonitoredItems.intValue()];
		
		for(nc = 0; nc < NumberMonitoredItems.intValue(); nc++){

			monitoredItems[nc] = new MonitoredItemCreateRequest();
		
			//Procedura di Inserimento dei valori del parametro itemToMonitor		
            //prima creo l'oggetto nodeId di tipo NodeId da inserire nel parametro itemToMonitor
   		    System.out.println("Inserisci namespace  ");
			ns = Integer.parseInt(in.readLine());
		    System.out.println("Inserisci nodeId  ");
			i = Integer.parseInt(in.readLine());
	    	NodeId nodeId=new NodeId(ns,i);
			//Inserisco i valori del parametro itemToMonitor
	    	//e' di tipo ReadValueId, vedi tabella 154 e ha 4 parametri: nodeId, AttributeId, indexRange, dataEncoding
	    	//indexRange e dataEncoding vengono messe a null (si veda il significato di null nella tabella 154
			monitoredItems[nc].setItemToMonitor(new ReadValueId(nodeId , Attributes.Value, null, null ) );

	    	//Inserisco i valori del parametro monitoringMode
			//Viene settato nello stato di Reporting_2 (vedi tabella 136)
			//Si ricorda che nella modalita' Reporting_2 si ha sempre sia il campionamento che l’inoltro delle Notifications
			
			System.out.println("Il Monitoring Mode viene settato allo stato Reporting ");
			monitoredItems[nc].setMonitoringMode(MonitoringMode.Reporting);	

			
			//Procedura di Inserimento dei valori del parametro RequestedParameters		
			//Viene allocato un oggetto reqParams di tipo MonitoringParameters (vedi tabella 128)		

			MonitoringParameters reqParams = new MonitoringParameters();

			System.out.println("Sampling Interval [DEFAULT: 1000]:");
			try{
				samplingInterval = Double.parseDouble(in.readLine());
			}catch(Exception e){
				samplingInterval = Default_SamplingInterval;
			}
		
			System.out.println("Queue Size: [DEFAULT: 4]");
			try{
				queueSize = new UnsignedInteger(in.readLine());
			}catch(Exception e){
				queueSize = Default_QueueSize;
			}

			System.out.println("Discard Oldest (true or false) [DEFAULT: false]:");
			try{
				discardOldest = Boolean.parseBoolean(in.readLine());
			}catch(Exception e){
			    discardOldest = Default_DiscardOldest;
			}
		
            //Il ClientHandle viene settato pari a nc+1, solo per semplificare il codice
	        chandle=new UnsignedInteger(nc+1);
			reqParams.setClientHandle(chandle);	//identificativo per il riconoscimento del monitorited items
	        System.out.println("Il Client Handle assegnato al Monitored Item e' : " + chandle);
	        reqParams.setSamplingInterval(samplingInterval); //intervallo di campionamento
	        reqParams.setQueueSize(queueSize);	//grandezza della coda per le notifiche
	        reqParams.setDiscardOldest(discardOldest);	//politica di gestione di buffer overflow

	        //manca ancora il riempimento del parametro filter, in accordo al DataChangeFilter (deadband, trigger)
			System.out.println("Deadband: (1.00 -> Absolute / 0.5% -> Percent ) [DEFAULT: 0.01]");
			try{
				String a = in.readLine();
				if(a.contains("%")){
					percentDeadBand = Double.parseDouble(a.split("%")[0]);
					absoluteDeadBand = null;
				}else{
					absoluteDeadBand = Double.parseDouble(a);
					percentDeadBand = null;
				}
			}catch(Exception e){
						absoluteDeadBand = Default_AbsoluteDeadBand;
						percentDeadBand = null;
			}

			DataChangeFilter filter = new DataChangeFilter(); 
			if(absoluteDeadBand != null){
				filter.setDeadbandValue(absoluteDeadBand);
				filter.setTrigger(DataChangeTrigger.StatusValue);
				filter.setDeadbandType(UnsignedInteger.valueOf(DeadbandType.Absolute.getValue()));
			}else{
				filter.setDeadbandValue(percentDeadBand);
				filter.setTrigger(DataChangeTrigger.StatusValue);
				filter.setDeadbandType(UnsignedInteger.valueOf(DeadbandType.Percent.getValue()));
			}
			ExtensionObject fil = ExtensionObject.binaryEncode(filter);
			reqParams.setFilter(fil);

		 	//Inserisco i valori del parametro RequestedParameters
			monitoredItems[nc].setRequestedParameters(reqParams);
	        
		}

  	   //adesso posso invocare il servizio CreateMonitoredItems
	   //alloco l'oggetto mi per contenere tutti i parametri della request: subscriptionId, timestampsToReturn, itemsToCreate[]
		
       CreateMonitoredItemsRequest mi = new CreateMonitoredItemsRequest();
  	   mi.setSubscriptionId(subId);
       // viene richiesto il timestamp del server
	   // in alternativa si poteva chiedere il timestamp della sorgente (source) o entrambi e nulla
	   mi.setTimestampsToReturn(TimestampsToReturn.Server); 
	   mi.setItemsToCreate(monitoredItems);
		  
	   System.out.println("Sto per creare i seguenti Monitored Items Creati nell'ambito della SubscritionId " + mi.getSubscriptionId());
	   for(nc = 0; nc < NumberMonitoredItems.intValue(); nc++){
   		    System.out.println("     Item           = "+ monitoredItems[nc].getItemToMonitor().getNodeId());
   		    System.out.println("     Client Handle  = "+ monitoredItems[nc].getRequestedParameters().getClientHandle());
		}

  	   do {
  		   System.out.println("Continuo  (y/n) ?");
  	       r = in.readLine();
       } while (r.equalsIgnoreCase("n"));

	   CreateMonitoredItemsResponse w = mySession.CreateMonitoredItems(mi);
	
       // Nel seguito il thread per la  ricezione dei notifications.
	   // Non vengono gestite le informazioni inviate dal Server circa le Notifications non ancora riconosciute 
	   // dal Client. Scelta fatta solo per semplificare il codice
	   
	   try{
		   System.out.println("\n\nSUBSCRIPTION'S NOTIFICATION REPORT\n");

		   new Thread(new Runnable() {
			  @Override
			  public void run() {
 				  
				  SubscriptionAcknowledgement subAck = new SubscriptionAcknowledgement();
		          
				  while(true) {

					  // il Client invia come ack l'ultima Seq.Number Ricevuto e la relativa Subscription Id
					  // inizialmente LastSubId e LastSeqNumber sono null
					  // in questo modo il server non notifichera' mai che vi sono Notifications in attesa di essere riconosciute
					  
					        subAck.setSubscriptionId(LastSubId);
							subAck.setSequenceNumber(LastSeqNumber);
							
							try {
								PublishResponse publishResponse = mySession.Publish(null, subAck);
								
								LastSubId = publishResponse.getSubscriptionId();
								LastSeqNumber = publishResponse.getNotificationMessage().getSequenceNumber();

								System.out.println("\n-----------------------------------------------------");
								System.out.println("Ho Ricevuto un NOTIFICATION MESSAGE relativo alla SUBSCRIPTION "+ LastSubId);
								System.out.println("Il suo Sequence number: " + LastSeqNumber);
//					
						//		if(publishResponse.getAvailableSequenceNumbers().length != 0){
						//			System.out.println("\n-----------------------------------------------------");
						//			System.out.println("[NOTIFICATION MESSAGE] SUBSCRIPTION "+ LastSubId);
						//			System.out.println("Sequence number: " + LastSeqNumber);
//									for (UnsignedInteger x : publishResponse.getAvailableSequenceNumbers()){
//										System.out.println("Available sequence number: " + x);
//									}
//								}
								
								NotificationMessage nm = publishResponse.getNotificationMessage();
								ExtensionObject[] ex = nm.getNotificationData();
								for(ExtensionObject ob : ex) {
									Object change = ob.decode();	
									if(change instanceof DataChangeNotification) {
										DataChangeNotification dataChange = (DataChangeNotification)change;
										MonitoredItemNotification[] mnchange = dataChange.getMonitoredItems();
									
// vengono estratti tutti i Notification relativi ai monitoreItem DataChange										
										for(MonitoredItemNotification monitoredItemNotification : mnchange){
											   System.out.println("\n Client Handle del Monitored Item ricevuto : "+ monitoredItemNotification.getClientHandle());
                                               System.out.println("    Value: "	+ monitoredItemNotification.getValue().getValue());
		                                       System.out.println("    Server TimeStamp:" + monitoredItemNotification.getValue().getServerTimestamp());
		                                       System.out.println("    Status: " + monitoredItemNotification.getValue().getStatusCode());
									    }
								
		

									}
							   }
								
							   Thread.sleep(Default_PRRate);
								
							} catch (ServiceFaultException e) {
								e.printStackTrace();
							} catch (ServiceResultException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								e.printStackTrace();
		                    } catch (Exception e) {
								
							};
						}
					}
				}).start();
				//////////////////////////////////////
				// 	Press enter to shutdown

				System.in.read();
				//////////////////////////////////////

			}catch(Exception e){}
		

		
		
		/////////////  SHUTDOWN  /////////////
		// Close channel
		mySession.closeAsync();
		//////////////////////////////////////		
		System.exit(0);
	}
	
}
