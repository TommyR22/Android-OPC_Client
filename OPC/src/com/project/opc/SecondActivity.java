package com.project.opc;

import static org.opcfoundation.ua.utils.EndpointUtil.selectByMessageSecurityMode;
import static org.opcfoundation.ua.utils.EndpointUtil.selectByProtocol;
import static org.opcfoundation.ua.utils.EndpointUtil.sortBySecurityLevel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.opcfoundation.ua.application.Client;
import org.opcfoundation.ua.application.SessionChannel;
import org.opcfoundation.ua.builtintypes.ExtensionObject;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.common.ServiceFaultException;
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.Attributes;
import org.opcfoundation.ua.core.BrowseDescription;
import org.opcfoundation.ua.core.BrowseDirection;
import org.opcfoundation.ua.core.BrowseResponse;
import org.opcfoundation.ua.core.BrowseResultMask;
import org.opcfoundation.ua.core.CreateMonitoredItemsRequest;
import org.opcfoundation.ua.core.CreateSubscriptionRequest;
import org.opcfoundation.ua.core.CreateSubscriptionResponse;
import org.opcfoundation.ua.core.DataChangeFilter;
import org.opcfoundation.ua.core.DataChangeTrigger;
import org.opcfoundation.ua.core.DeadbandType;
import org.opcfoundation.ua.core.EndpointDescription;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.core.MessageSecurityMode;
import org.opcfoundation.ua.core.MonitoredItemCreateRequest;
import org.opcfoundation.ua.core.MonitoringMode;
import org.opcfoundation.ua.core.MonitoringParameters;
import org.opcfoundation.ua.core.NodeClass;
import org.opcfoundation.ua.core.ReadResponse;
import org.opcfoundation.ua.core.ReadValueId;
import org.opcfoundation.ua.core.SubscriptionAcknowledgement;
import org.opcfoundation.ua.core.TimestampsToReturn;
import org.opcfoundation.ua.encoding.EncodingException;
import org.opcfoundation.ua.transport.security.Cert;
import org.opcfoundation.ua.transport.security.KeyPair;
import org.opcfoundation.ua.transport.security.PrivKey;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

public class SecondActivity extends Activity {
	
    private ListView list;
    private CustomAdapter adapter;
    private CustomAdapterSubscription adapter_sub;
    public SecondActivity CustomListView = null;
    public ArrayList<Endpoint> array_list = new ArrayList<Endpoint>();
	private String[] namespaceArray;
	private SubscriptionManager subscriptionManager = null;
	//parameter MaxAge used for read variables
    static Double MaxAge = new Double(500);
    private int menuType=0;
    
	static SessionChannel mySession;
	private Client myClient;
	private EndpointDescription[] endpoints;
	private EndpointDescription currentendpoint;
	
	private static final String PRIVKEY_PASSWORD = "Opc.Ua";
	TextView testo1,testo_session;
	BootstrapEditText input_ns,input_i;

	File certFile;
	File privKeyFile;
	String hostName="";					
	private String applicationUri;
	private String url;
	
	private ProgressDialog progress;
	
	private BrowseDescription browse;
	private BrowseResponse res;
    ReadResponse resp;
    private BootstrapButton subscriptionButton;
	private ListView list_sub;
	
	
	//Monitored Item
	private CustomAdapterMonitoredItem adapter_m;
	int id_mitem=0;
	private ArrayList<MonitoredItem> listMonitoredItem = new ArrayList<MonitoredItem>(); 
	private ListView list_m;
	//Subscription
	private ArrayList<Subscription> mapSubscription = new ArrayList<Subscription>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		
		CustomListView = this;
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#546fb4")));
		bar.setIcon(R.drawable.opc_logo);
		bar.setTitle("");
		bar.setDisplayHomeAsUpEnabled(false);
		
		progress = new ProgressDialog(this);
        progress.setMessage("Connecting ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
		
		AssetManager asm = getBaseContext().getAssets();
		InputStream i;
		InputStream l;
		
		certFile = new File(getFilesDir(), "Client.der");
		privKeyFile = new File(getFilesDir(), "Client.pem");
		try {			
			i = asm.open("certificate/Client.der");
			writeBytesToFile(i, certFile);
			l = asm.open("certificate/Client.pem");
			writeBytesToFile(l, privKeyFile);
		} catch (IOException e1) {
			Log.w("MZ","errore"+e1.getMessage());
			e1.printStackTrace();
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("OPC Connection");
		builder.setMessage("Insert Server's Address OPC");
		
		final EditText input = new EditText(this);
		input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_TEXT );
		input.setHint("es:192.168.0.1");
		
		builder.setView(input);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
		    public void onClick(DialogInterface dialog, int which) {
		    	try{	
			    		hostName=input.getText().toString();		
			    		progress.show();
			    		new Connessione().execute();
					}catch(Exception e){}
	    	}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		        finish();
		    }
		});	
		builder.show();	
	}
	
	public static void writeBytesToFile(InputStream is, File file) throws IOException{
	    FileOutputStream fos = null;
	    try {   
	        byte[] data = new byte[2048];
	        int nbread = 0;
	        fos = new FileOutputStream(file);
	        while((nbread=is.read(data))>-1){
	            fos.write(data,0,nbread);               
	        }
	    }
	    catch (Exception ex) {
	        ex.getStackTrace();
	    }
	    finally{
	        if (fos!=null){
	            fos.close();
	        }
	    }
	}
	
	public void start(File certFile,File privKeyFile) throws Exception{
		String r, aux;
		int x, nc, ns, i,j;
		array_list.clear();
		//////////////  CLIENT  //////////////
		// Load Client's Application Instance Certificate from file
        Cert myServerCertificate = Cert.load( certFile );
		PrivKey myServerPrivateKey = PrivKey.load( privKeyFile, PRIVKEY_PASSWORD );
		final KeyPair  myClientApplicationInstanceCertificate = new KeyPair(myServerCertificate, myServerPrivateKey);
		
        //URL
		applicationUri = "urn:"+hostName+":Client";
    	url = "opc.tcp://"+hostName+":62541/QuickStarts";	//URL Server used in this project 
    	//Create Client
    	myClient = Client.createClientApplication(myClientApplicationInstanceCertificate);
    	//Discovery service
		endpoints = myClient.discoverEndpoints(url);
	}
	
	public void FilterByALL(){		//ALL
		array_list.clear();
		if(endpoints!=null){
			testo1.append("List all endpoints\n");
			testo1.append("Number endpoint: "+ endpoints.length +"\n");
			testo1.append("____________________________________\n");
			int nc;
			for(nc = 0; nc < endpoints.length; nc++){
				Endpoint e=new Endpoint(endpoints[nc].getEndpointUrl(),endpoints[nc].getTransportProfileUri(),endpoints[nc].getSecurityMode().toString(),endpoints[nc].getSecurityPolicyUri(),endpoints[nc].getSecurityLevel().toString());
				array_list.add(e);
			}
			Resources res =getResources();
	        adapter=new CustomAdapter( CustomListView, array_list,res );
	        list.setAdapter( adapter );
        }else{
        	testo1.append("Connection with Server lost: "+url+"\n");
        }
	}
	
	public void FilterByTCP(){		//FILTER BY TRANSPORT PROTOCOL TCP
		array_list.clear();
		if(endpoints!=null){
			endpoints = selectByProtocol(endpoints, "opc.tcp");
			testo1.append("Filtering endpoints, Transport Protocol = opc.tcp\n");
			testo1.append("Number endpoint: "+ endpoints.length +"\n");
			testo1.append("____________________________________\n");
			int nc;
			for(nc = 0; nc < endpoints.length; nc++){
				Endpoint e=new Endpoint(endpoints[nc].getEndpointUrl(),endpoints[nc].getTransportProfileUri(),endpoints[nc].getSecurityMode().toString(),endpoints[nc].getSecurityPolicyUri(),endpoints[nc].getSecurityLevel().toString());
				array_list.add(e);
			}
			Resources res =getResources();
	        adapter=new CustomAdapter( CustomListView, array_list,res );
	        list.setAdapter( adapter );
        }else{
        	testo1.append("Connection with Server lost: "+url+"\n");
        }
	}
	
	public void FilterBySmode(){		//FILTER BY SECURITY MODE NONE
		array_list.clear();
		if(endpoints!=null){
			endpoints = selectByMessageSecurityMode(endpoints,MessageSecurityMode.None);
			testo1.append("Filtering endpoints, Security Mode = None \n");
			testo1.append("Numbers endpoint: "+ endpoints.length +"\n");
			testo1.append("____________________________________\n");
			int nc;
			for(nc = 0; nc < endpoints.length; nc++){
				Endpoint e=new Endpoint(endpoints[nc].getEndpointUrl(),endpoints[nc].getTransportProfileUri(),endpoints[nc].getSecurityMode().toString(),endpoints[nc].getSecurityPolicyUri(),endpoints[nc].getSecurityLevel().toString());
				array_list.add(e);
			}
			Resources res =getResources();
	        adapter=new CustomAdapter( CustomListView, array_list,res );
	        list.setAdapter( adapter );
		}else{
			testo1.append("Connection with Server lost: "+url+"\n");
		}
	}
	
	//handle event: "select endpoint"
	public void onItemClick(int mPosition)
    {
        //Endpoint tempValues = ( Endpoint ) array_list.get(mPosition);
        Toast toast=Toast.makeText(this,"Endpoint selected:"+mPosition,Toast.LENGTH_SHORT);
        toast.show();
   	    EndpointDescription endpoint = endpoints[mPosition];
   	    currentendpoint = endpoint;
   	    progress.setMessage("Creating and Activating Session...");
   	    progress.show();
   	    //avvio la sessione
   	    new Sessione().execute();
    }
	
	//----------------------------------------------------------------------------------------------------------------------//	
	
	public void SessionActivity(){
		try{
			progress.dismiss();
			setContentView(R.layout.activity_session);
			menuType=1;
            testo_session = (TextView)findViewById(R.id.testo_session);

            ScrollView scrollConsole = (ScrollView) findViewById(R.id.Console);
			scrollConsole.fullScroll(ScrollView.FOCUS_DOWN);
	
			TextView t;
            t = (TextView)findViewById(R.id.name);
            t.setText("OPTIONS");
            
            Browsing();
   
        	BootstrapButton namespace,browsing,variables;
        	
        	input_ns = (BootstrapEditText)findViewById(R.id.input_ns);
        	input_i = (BootstrapEditText)findViewById(R.id.input_i);
        	
    		
        	namespace = (BootstrapButton)findViewById(R.id.namespace);			//*******NAMESPACE
    		namespace.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	  Toast.makeText(getApplicationContext(),Arrays.toString(namespaceArray) ,Toast.LENGTH_LONG).show();	  
                }
            });
    		
    		browsing = (BootstrapButton)findViewById(R.id.browsing);			//********BROWSING
    		browsing.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	try{
                	if(input_ns.getText().toString().equalsIgnoreCase("") | input_i.getText().toString().equalsIgnoreCase("")){
                    	  Toast.makeText(getApplicationContext(),"Error:Insert namespace and nodeID" ,Toast.LENGTH_LONG).show();	  
                	}
                	else{
                	browse.setNodeId(new NodeId(Integer.parseInt(input_ns.getText().toString()),Integer.parseInt(input_i.getText().toString())));
   		    	 
//   		    	 browse.setBrowseDirection( BrowseDirection.Both);
//   		    	 browse.setBrowseDirection( BrowseDirection.Inverse);
   		    	 browse.setBrowseDirection( BrowseDirection.Forward );

    		        String aux = "HasChild";
                    browse.setReferenceTypeId(fromString_ToReferenceTypeId(aux));
   		    	 browse.setIncludeSubtypes( true );
   		    	 //		         browse.setNodeClassMask( NodeClass.ALL );
   		    	 browse.setNodeClassMask( NodeClass.Object, NodeClass.Variable );	//vedere solo nodi object e variable
   		    	 browse.setResultMask( BrowseResultMask.All );
                    
   		    	 res = mySession.Browse( null, null, null, browse );
   		    	 
   	  		     testo_session.append("BROWSING User --> Total numbers of Results =  " + res.getResults().length+"\n");

   	  		     int j,x;
   		    	 for (j=0; j< res.getResults().length; j++){
   		      	     System.out.println("Numbers of Results =  " + j);
   				     for(x = 0; x < res.getResults()[j].getReferences().length; x++){
   				  	 testo_session.append("Name Space Index:  " + res.getResults()[j].getReferences()[x].getNodeId().getNamespaceIndex()+"\n");
			    	 testo_session.append("Name Space URI: " + res.getResults()[j].getReferences()[x].getNodeId().getNamespaceUri()+"\n");
			    	 testo_session.append("NodeID (namespace,id): " + res.getResults()[j].getReferences()[x].getNodeId()+"\n");
			    	 testo_session.append("BrowseName: "+ res.getResults()[j].getReferences()[x].getBrowseName()+"\n");
			    	 testo_session.append("DisplayName: " + res.getResults()[j].getReferences()[x].getDisplayName()+"\n");
	    			 testo_session.append("____________________________________\n");
   			         }
   		         }	
                }//fine else
                	}catch(Exception e){}
                }
            });

    		variables = (BootstrapButton)findViewById(R.id.variables);			//******VARIABILI
    		variables.setOnClickListener(new View.OnClickListener() {
    		    public void onClick(View v) {
                	try{
                	if(input_ns.getText().toString().equalsIgnoreCase("") | input_i.getText().toString().equalsIgnoreCase("")){
                    	  Toast.makeText(getApplicationContext(),"Error:Insert namespace and nodeID" ,Toast.LENGTH_LONG).show();	  
                	}
                	else{
                		ReadResponse res = mySession.Read(null, MaxAge, TimestampsToReturn.Source, new ReadValueId(new NodeId(Integer.parseInt(input_ns.getText().toString()),Integer.parseInt(input_i.getText().toString())), Attributes.Value, null, null ));		
                		testo_session.append("Value: "+res.getResults()[0].getValue()+"\n");
    	    			testo_session.append("____________________________________\n");
                }//fine else
                	}catch(Exception e){}
                }
            });
    		
    		subscriptionButton = (BootstrapButton)findViewById(R.id.buttonSub);			//******ADD SUBSCRIPTION
    		subscriptionButton.setOnClickListener(new View.OnClickListener() {
    		    public void onClick(View v) {
                	try{
                		Subscription();
                		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                	}catch(Exception e){}
                }
            });
    		list_sub = (ListView) findViewById(R.id.listview_subscription);
    		Resources res =getResources();
	        adapter_sub=new CustomAdapterSubscription( CustomListView, mapSubscription,res );
	        list_sub.setAdapter( adapter_sub );
	        
	        
    	}catch(Exception e){}
	}
//----------------------------------------------------------------------------------------------------------------------//	
	
	
	public void onItemSubClick(int mPosition)
    {
        //Endpoint tempValues = ( Endpoint ) array_list.get(mPosition);
        Toast toast=Toast.makeText(this,"Subscription ID selected:"+mapSubscription.get(mPosition).getSubId(),Toast.LENGTH_SHORT);
        toast.show();
   	   
        //**************************** ACTIVITY MONITOREDITEM
        setContentView(R.layout.activity_monitored);
        menuType=2;
		
		TextView t;
        t = (TextView)findViewById(R.id.name_m);
        t.setText("Add Monitored Item");
        final int pos =mPosition;
        
        list_m = (ListView) findViewById(R.id.listview_M);
		Resources ress =getResources();
        adapter_m=new CustomAdapterMonitoredItem( CustomListView, mapSubscription.get(mPosition).listMonitoredItem,ress );
        list_m.setAdapter( adapter_m );
        mapSubscription.get(mPosition).setadapterM(adapter_m);
        mapSubscription.get(mPosition).setList_m(list_m);
        
        BootstrapButton buttonMon_M = (BootstrapButton)findViewById(R.id.buttonMon_M);			//******ADD MONITORITEM
        buttonMon_M.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
            	try{
            		createItem(mapSubscription.get(pos).getSubId(),pos);
            		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            		Toast toast=Toast.makeText(getApplicationContext(),"Monitored Item created!",Toast.LENGTH_SHORT);
        	        toast.show();
            	}catch(Exception e){}
            }
        });
      
    }
	
	
	public void createItem(UnsignedInteger subId,int pos){
		
		BootstrapEditText input_namespace_M;
    	BootstrapEditText input_nodeid_M;
    	BootstrapEditText input_sampling_M;
    	BootstrapEditText input_queue_M;
    	BootstrapEditText input_discard_M;
    	BootstrapEditText input_deadband_M;
    	
    	progress.setMessage("Creating Monitored Item");
    	progress.show();
    	Log.w("SUB","\nIInserisci namespace  :");
    	
    	MonitoredItem temp = new MonitoredItem();
    	temp.setMySession(mySession);
    	
    			
		try{
			input_namespace_M =(BootstrapEditText)findViewById(R.id.input_namespace_M);
			temp.setNamespace(Integer.parseInt(input_namespace_M.getText().toString()));
		}catch(Exception e){
			progress.dismiss();
			Toast toast=Toast.makeText(this,"Insert NameSpace",Toast.LENGTH_SHORT);
	        toast.show();
	        return;
		}
		
		try{
			input_nodeid_M =(BootstrapEditText)findViewById(R.id.input_nodeid_M);
			temp.setNodeid(Integer.parseInt(input_nodeid_M.getText().toString()));
		}catch(Exception e){
			progress.dismiss();
			Toast toast=Toast.makeText(this,"Insert NodeId",Toast.LENGTH_SHORT);
	        toast.show();
	        return;
		}
		
		NodeId nodeId=new NodeId(temp.getNamespace(),temp.getNodeid());
		
		MonitoredItemCreateRequest mi = new MonitoredItemCreateRequest();
		mi.setItemToMonitor(new ReadValueId(nodeId, Attributes.Value, null, null ) );

    	//Inserisco i valori del parametro monitoringMode
		//Viene settato nello stato di Reporting_2 (vedi tabella 136)
		//Si ricorda che nella modalita' Reporting_2 si ha sempre sia il campionamento che l�inoltro delle Notifications
		
		mi.setMonitoringMode(MonitoringMode.Reporting);	

		
		//Procedura di Inserimento dei valori del parametro RequestedParameters		
		//Viene allocato un oggetto reqParams di tipo MonitoringParameters (vedi tabella 128)		

		MonitoringParameters reqParams = new MonitoringParameters();

		Log.w("MI","Sampling Interval [DEFAULT: 1000]:");
		try{
			input_sampling_M =(BootstrapEditText)findViewById(R.id.input_sampling_M);
			temp.setInput_sampling(Double.parseDouble(input_sampling_M.getText().toString()));
		}catch(Exception e){
			temp.setInput_sampling(MonitoredItem.Default_SamplingInterval);
		}
	
		Log.w("MI","Queue Size: [DEFAULT: 4]");
		try{
			input_queue_M =(BootstrapEditText)findViewById(R.id.input_queue_M);
			temp.setInput_queue(new UnsignedInteger(Integer.parseInt(input_queue_M.getText().toString())));
			
		}catch(Exception e){
			temp.setInput_queue(MonitoredItem.Default_QueueSize);
		}

		Log.w("MI","Discard Oldest (true or false) [DEFAULT: false]:");
		try{input_discard_M =(BootstrapEditText)findViewById(R.id.input_discard_M);
		temp.setInput_discard(Boolean.parseBoolean(input_discard_M.getText().toString()));
		
		}catch(Exception e){
			temp.setInput_discard(MonitoredItem.Default_DiscardOldest);
		}
	
        //Il ClientHandle viene settato pari a nc+1, solo per semplificare il codice
        UnsignedInteger chandle=new UnsignedInteger(id_mitem);
		reqParams.setClientHandle(chandle);	//identificativo per il riconoscimento del monitorited items
        reqParams.setSamplingInterval(temp.getInput_sampling()); //intervallo di campionamento
        reqParams.setQueueSize(temp.getInput_queue());	//grandezza della coda per le notifiche
        reqParams.setDiscardOldest(temp.getInput_discard());	//politica di gestione di buffer overflow

        //manca ancora il riempimento del parametro filter, in accordo al DataChangeFilter (deadband, trigger)
        Log.w("MI","Deadband: (1.00 -> Absolute / 0.5% -> Percent ) [DEFAULT: 0.01]");
		try{
			input_deadband_M =(BootstrapEditText)findViewById(R.id.input_deadband_M);
			String a = input_deadband_M.getText().toString();
			if(a.contains("%")){
				temp.setPercentDeadBand(Double.parseDouble(a.split("%")[0]));
				temp.setAbsoluteDeadBand(null);
			}else{
				temp.setAbsoluteDeadBand(Double.parseDouble(a));
				temp.setPercentDeadBand(null);
			}
		}catch(Exception e){
			temp.setAbsoluteDeadBand(MonitoredItem.Default_AbsoluteDeadBand);
					temp.setPercentDeadBand(null);
		}

		DataChangeFilter filter = new DataChangeFilter(); 
		if(temp.getAbsoluteDeadBand() != null){
			filter.setDeadbandValue(temp.getAbsoluteDeadBand());
			filter.setTrigger(DataChangeTrigger.StatusValue);
			filter.setDeadbandType(UnsignedInteger.valueOf(DeadbandType.Absolute.getValue()));
		}else{
			filter.setDeadbandValue(temp.getPercentDeadBand());
			filter.setTrigger(DataChangeTrigger.StatusValue);
			filter.setDeadbandType(UnsignedInteger.valueOf(DeadbandType.Percent.getValue()));
		}
		
		ExtensionObject fil;
		try {
			fil = ExtensionObject.binaryEncode(filter);
			reqParams.setFilter(fil);
			
		} catch (EncodingException e1) {
			return;
		}
		

	 	//Inserisco i valori del parametro RequestedParameters
		mi.setRequestedParameters(reqParams);
        
		//adesso posso invocare il servizio CreateMonitoredItems
		//alloco l'oggetto mi per contenere tutti i parametri della request: subscriptionId, timestampsToReturn, itemsToCreate[]
	
		CreateMonitoredItemsRequest mir = new CreateMonitoredItemsRequest();
		mir.setSubscriptionId(subId);
		// viene richiesto il timestamp del server
		// in alternativa si poteva chiedere il timestamp della sorgente (source) o entrambi e nulla
		mir.setTimestampsToReturn(TimestampsToReturn.Server); 
		MonitoredItemCreateRequest[] s =new MonitoredItemCreateRequest[1];
		s[0]=mi;
		mir.setItemsToCreate(s);

	    temp.setMir(mir);
	    temp.setSubId(subId);
	    temp.setId_M(""+id_mitem);
	    temp.setTime_M("");
	    temp.setValue_M("");
	    temp.setStatus_M("");
	    mapSubscription.get(pos).listMonitoredItem.add(temp);
		    
	    listMonitoredItem.add(temp);
	    adapter_m.notifyDataSetChanged();
	    id_mitem++;
	    progress.dismiss();
	    try {
	    	//CreateMonitoredItemsResponse w = 
	    	mySession.CreateMonitoredItems(mir);
	    } catch (ServiceFaultException e) {
	    	e.printStackTrace();
		} catch (ServiceResultException e) {
			e.printStackTrace();
		}
	    //temp.active();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      
    	switch (item.getItemId()) {
	    	case R.id.back://RETURN
	    		if (item.isChecked() == true) {
	    			item.setChecked(false);
	    		} else {
	    			item.setChecked(true);
	    			try{
	    				SessionActivity();	
	    			}catch(Exception e){}
	    		}
	    		return true;
	    		
	    	case R.id.item1://EXIT
	    		if (item.isChecked() == true) {
	    			item.setChecked(false);
	    		} else {
	    			item.setChecked(true);
	    			try{
	    				Toast.makeText(getApplicationContext(),"Session Destroyed" ,Toast.LENGTH_LONG).show();
	    				mySession.closeAsync();
	    			}catch(Exception e){}
	    			finish();	
	    		}
	    		return true;
	    
	    	case R.id.uno://ALL
		    	if (item.isChecked() == true) {
		            item.setChecked(false);
		        } else {
		            item.setChecked(true);
		            try{
		            	progress.setMessage("Loading...");
		            	progress.show();
		            	new RetriveEndPoint().execute("ALL");				
		            }catch(Exception e){}
		        }
		        return true;
		        
		    case R.id.due://TCP
		        if (item.isChecked() == true) {
		            item.setChecked(false);
		        } else {
		            item.setChecked(true);
		            try{
		            	progress.setMessage("Loading...");
		            	progress.show();
		            	new RetriveEndPoint().execute("TCP");			
			            }catch(Exception e){}
		        }
		        return true;
		        
		    case R.id.tre://SMODE NONE
		        if (item.isChecked() == true) {
		            item.setChecked(false);
		        } else {
		            item.setChecked(true);
		            try{
		            	progress.setMessage("Loading...");
		            	progress.show();
		            	new RetriveEndPoint().execute("SMODE");				
			            }catch(Exception e){}
		        }
		        return true;
		    }
	    return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
    	Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent);
    }
    
    private class Connessione extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... args) {
        	try {
				start(certFile,privKeyFile);
				return "successo";
			} catch (Exception e) {
				return "error";
			}
            
        }
        @Override
        protected void onPostExecute(String json) {
            progress.dismiss();
            testo1 = (TextView)findViewById(R.id.testo);
        	if(json.equals("successo")){
	            testo1.append("Connection with Server: "+url+"\n");
				testo1.append("Number endpoint: "+ endpoints.length +"\n");
				endpoints = sortBySecurityLevel(endpoints);
				testo1.append("____________________________________\n");
				list = (ListView) findViewById(R.id.listview);
		   	    for(int nc = 0; nc < endpoints.length; nc++){
					Endpoint e=new Endpoint(endpoints[nc].getEndpointUrl(),endpoints[nc].getTransportProfileUri(),endpoints[nc].getSecurityMode().toString(),endpoints[nc].getSecurityPolicyUri(),endpoints[nc].getSecurityLevel().toString());
					array_list.add(e);
		   	    }
		   	    Resources res =getResources();
		   	    adapter=new CustomAdapter( CustomListView, array_list,res );
		   	    list.setAdapter( adapter );
            }else{
            	testo1.append("Impossible connect with Server: "+url+"\n");
				testo1.append("____________________________________\n");
				menuType=1;
            }
        }
    }
    
    private class RetriveEndPoint extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... args) {
        	try {
        		Log.w("MZ","ricarico tutti gli endpoint");
        		endpoints = myClient.discoverEndpoints(url);
				return args[0];
			} catch (Exception e) {
				return "ERROR";
			}
        }
        @Override
        protected void onPostExecute(String value) {
        	progress.dismiss();
        	switch (value) {
				case "TCP":
					FilterByTCP();
					break;
				case "SMODE":
					FilterBySmode();		
					break;
				case "ALL":
					FilterByALL();
					break;
				case "ERROR":
					Log.w("MZ","errore nel caricamento di tutti gli endpoint");
					break;
				default:
					break;
			}
        }
    }
    
	
    private class Sessione extends AsyncTask<String,String,String> {
   		@Override
        protected String doInBackground(String... args) {
   			testo1 = (TextView)findViewById(R.id.testo);
        	Log.d("MZ","Eseguo la sessione");
        	try {
        		mySession = myClient.createSessionChannel(url,currentendpoint);
				mySession.activate();
				resp = mySession.Read(	//leggo il nodo del NamespaceArray
						null, 
						null, 
						TimestampsToReturn.Neither, 				
						new ReadValueId(Identifiers.Server_NamespaceArray, Attributes.Value, null, null ) 
				);
				subscriptionManager = SubscriptionManager.getInstance(mySession);
				return "successo";

				} catch (ServiceResultException e) { 
					Log.w("MZ","errore");
					return "error";
				}
        }

        @Override
        protected void onPostExecute(String json) {
            progress.dismiss();
        	if(json.equals("successo")){
        		testo1.append("Session Created and Activated\n");
	   	    	testo1.append("____________________________________\n");
	   	        namespaceArray = (String[]) resp.getResults()[0].getValue().getValue();
	   	        //			System.out.println(Arrays.toString(namespaceArray));
				testo1.append("NameSpaceArray Endpoint\n");
				testo1.append(Arrays.toString(namespaceArray)+"\n");
				testo1.append("____________________________________\n");
			   //*******ACTIVITY SESSION 
    			progress.show();
    			SessionActivity();
        	}else{
            	testo1.append("Impossible create session");
				testo1.append("____________________________________\n");
            }
        }
    }
      
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

    	if(menuType==1) { //SESSION
    		menu.findItem(R.id.uno).setVisible(false);
    		menu.findItem(R.id.due).setVisible(false);
    		menu.findItem(R.id.tre).setVisible(false);
    		menu.findItem(R.id.item1).setVisible(true);
    		menu.findItem(R.id.back).setVisible(false);
    		}
    	if(menuType==0){	//HOME
    		menu.findItem(R.id.item1).setVisible(false);
    		menu.findItem(R.id.back).setVisible(false);
    	}
    	if(menuType==2){	//MONITORED
    		menu.findItem(R.id.uno).setVisible(false);
    		menu.findItem(R.id.due).setVisible(false);
    		menu.findItem(R.id.tre).setVisible(false);
    		menu.findItem(R.id.item1).setVisible(false);
    		menu.findItem(R.id.back).setVisible(true);

    	}
    		return super.onPrepareOptionsMenu(menu);
    }
   
    
    public void Subscription(){
    	
    	BootstrapEditText input_interval;
    	BootstrapEditText input_maxkeep;
    	BootstrapEditText input_lifetime;
    	BootstrapEditText input_numnotify;
    	
    	progress.setMessage("Creating Subscription");
    	progress.show();
    	Subscription temp =  new Subscription();
    	
    	Log.d("SUB","\nInserisci i Parametri di Sottoscrizione :");
    	Log.d("SUB","Requested Publishing Interval [DEFAULT: " +
					Subscription.Default_RequestedPublishingInterval + "]:");
		try{
			input_interval =(BootstrapEditText)findViewById(R.id.input_interval);
			temp.setRequestedPublishingInterval(Double.parseDouble(input_interval.getText().toString()));
		}catch(Exception e){
			temp.setRequestedPublishingInterval(Subscription.Default_RequestedPublishingInterval);
		}
		
		Log.d("SUB","Requested Max Keep Alive Count [DEFAULT: " +
				Subscription.Default_RequestedMaxKeepAliveCount + "]:");
		try{
			input_maxkeep =(BootstrapEditText)findViewById(R.id.input_maxkeep);
			temp.setRequestedMaxKeepAliveCount(UnsignedInteger.parseUnsignedInteger(input_maxkeep.getText().toString()));
		}catch(Exception e){
			temp.setRequestedMaxKeepAliveCount(Subscription.Default_RequestedMaxKeepAliveCount);
		}
		
		// RequestedLifetimeCount > 3 * RequestedMaxKeepAliveCount
		
		Log.d("SUB","Requested Lifetime Count [DEFAULT: " +
				Subscription.Default_RequestedLifetimeCount + "]:");
		Log.d("SUB","Nota: deve essere superiore a 3 volte il RequestedMaxKeepAliveCount ");
		try{
			input_lifetime = (BootstrapEditText)findViewById(R.id.input_lifetime);
			temp.setRequestedLifetimeCount(UnsignedInteger.parseUnsignedInteger(input_lifetime.getText().toString()));
		}catch(Exception e){
			temp.setRequestedLifetimeCount(Subscription.Default_RequestedLifetimeCount);
		}
		if (temp.getRequestedLifetimeCount().compareTo(temp.getRequestedMaxKeepAliveCount().intValue()*3)<=0){
			Log.e("SUB","Errore deve essere superiore a 3 volte requestedmaxkeepalivecount");
			return;
		}

		Log.d("SUB","Maximum Number of Notifications in a Single Publish response [DEFAULT: " +
				Subscription.Default_MaxNotificationsPerPublish + "]:");
		try{
			
			input_numnotify =(BootstrapEditText)findViewById(R.id.input_numnotify);
			temp.setMaxNotificationsPerPublish(UnsignedInteger.parseUnsignedInteger(input_numnotify.getText().toString()));
		}catch(Exception e){
			temp.setMaxNotificationsPerPublish(Subscription.Default_MaxNotificationsPerPublish);
		}

		Log.d("SUB","Il parametro publishing Enabled viene fissato a True ");
		Log.d("SUB","La Priorita' assegnata alla Subscription e' : " +
				Subscription.Default_Priority);
		
		CreateSubscriptionRequest subscription = new CreateSubscriptionRequest(null, 
	  				temp.getRequestedPublishingInterval(),temp.getRequestedLifetimeCount(), 
	  				temp.getRequestedMaxKeepAliveCount(), temp.getMaxNotificationsPerPublish(), 
	  				Subscription.Default_PublishingEnabled, Subscription.Default_Priority);
  		
		CreateSubscriptionResponse response;
		try {
			response = mySession.CreateSubscription(subscription);
	  		UnsignedInteger subId = response.getSubscriptionId();
	  		Log.d("SUB","Subscription Creata, il suo Id (SubscriptionId) e' :" + 
		  		  		subId);
	  		Log.d("SUB","    Il Revised Requested Publishing Interval:" + 
		  		response.getRevisedPublishingInterval());
	  		subscriptionManager.setPRRate(response.getRevisedPublishingInterval());
	  		Log.d("SUB","    Il Revised Lifetime Count :" + 
		  		  		response.getRevisedLifetimeCount());
	  		Log.d("SUB","    Il Revised Max Keep Alive Count:" + 
		  		  		response.getRevisedMaxKeepAliveCount());
	  		temp.setRequest(subscription);
	  		temp.setResponse(response);
	  		temp.setSubId(subId);
	  		mapSubscription.add(temp);
	  		subscriptionManager.setMapSubscription(mapSubscription);
	  		//temp.active(mySession);
	  		//inizializzo il contatore degli ack
	  		SubscriptionAcknowledgement s = new SubscriptionAcknowledgement();
	  		s.setSequenceNumber(null);
	  		s.setSubscriptionId(subId);
	  		subscriptionManager.sac.add(s);
	  		
	  		progress.dismiss();
	  		
	  		adapter_sub.notifyDataSetChanged();
	  		Toast.makeText(getApplicationContext(),"Subscription Creata, ID: "+subId,Toast.LENGTH_LONG).show();
		} catch (ServiceResultException e) {
			Log.e("SUB","Errore nella subscription"); 
		}
	  	
    }
    
    
    public void Browsing(){		//WHEN START ACTIVITY SESSION
    	try{
             browse = new BrowseDescription();
  		     //ObjectsFolder va alla cartella degli Oggetti, dove sono messi i Nodi
  		     browse.setNodeId( Identifiers.ObjectsFolder);   
//			           		     browse.setNodeId( Identifiers.RootFolder );
  		     browse.setBrowseDirection( BrowseDirection.Forward );
  		     browse.setIncludeSubtypes( true );
  		     browse.setNodeClassMask( NodeClass.Object );	//solo i nodi Object
//			           		     browse.setNodeClassMask( NodeClass.ALL );
  		     browse.setResultMask( BrowseResultMask.All );	//quali attributi vedere
  		     res = mySession.Browse( null, null, null, browse );	
  		     
  		     testo_session.append("BROWSING --> Numbers total of Results =  " + res.getResults().length+"\n");
       	  int j,x;
       	  for (j=0; j< res.getResults().length; j++){
	      	     System.out.println("Number di Results =  " + j);
			     for(x = 0; x < res.getResults()[j].getReferences().length; x++){
			    	 testo_session.append("Name Space Index:  " + res.getResults()[j].getReferences()[x].getNodeId().getNamespaceIndex()+"\n");
			    	 testo_session.append("Name Space URI: " + res.getResults()[j].getReferences()[x].getNodeId().getNamespaceUri()+"\n");
			    	 testo_session.append("NodeID (namespace,id): " + res.getResults()[j].getReferences()[x].getNodeId()+"\n");
			    	 testo_session.append("BrowseName: "+ res.getResults()[j].getReferences()[x].getBrowseName()+"\n");
			    	 testo_session.append("DisplayName: " + res.getResults()[j].getReferences()[x].getDisplayName()+"\n");
	    			 testo_session.append("____________________________________\n");
		        }
       	  }
       	  
       	}catch(Exception e){}
    }
    
    
    public static NodeId fromString_ToReferenceTypeId (String rT){
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

	public void onItemMonClick(int mPosition) {
		adapter_m.notifyDataSetChanged();
	}
}

