package com.project.opc;

import java.util.ArrayList;
import java.util.HashMap;

import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapterSubscription extends BaseAdapter implements OnClickListener {
          
         /*********** Declare Used Variables *********/
         private Activity activity;
         private ArrayList data;
         private static LayoutInflater inflater=null;
         public Resources res;
         Subscription tempValues=null;
         int i=0;
          
         /*************  CustomAdapter Constructor *****************/
         public CustomAdapterSubscription(Activity a, ArrayList d,Resources resLocal) {
                 activity = a;
                 data=d;
                 res = resLocal;
                 inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              
         }
      
         /******** What is the size of Passed Arraylist Size ************/
         public int getCount() {
             if(data.size()<=0)
                 return 1;
             return data.size();
         }
      
         public Object getItem(int position) {
             return position;
         }
      
         public long getItemId(int position) {
             return position;
         }
          
         /********* Create a holder Class to contain inflated xml file elements *********/
         public static class ViewHolder{
             public TextView sub_id;
             public TextView sub_requestedPublishingInterval;
             public TextView sub_requestedLifetimeCount;
             public TextView sub_requestedMaxKeepAliveCount;
             public TextView sub_maxNotificationsPerPublish;
         }
      
         /****** Depends upon data size called for each row , Create each ListView row *****/
         public View getView(int position, View convertView, ViewGroup parent) {
              
             View vi = convertView;
             ViewHolder holder;
              
             if(convertView==null){
                  
                 /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                 vi = inflater.inflate(R.layout.listview_subscription_layout, null);
                  
                 /****** View Holder Object to contain listview_layout.xml file elements ******/
 
                 holder = new ViewHolder();
                 holder.sub_id = (TextView) vi.findViewById(R.id.sub_id_value);
                 holder.sub_requestedPublishingInterval=(TextView)vi.findViewById(R.id.sub_requestedPublishingInterval_value);
                 holder.sub_requestedLifetimeCount=(TextView)vi.findViewById(R.id.sub_requestedLifetimeCount_value);
                 holder.sub_requestedMaxKeepAliveCount=(TextView)vi.findViewById(R.id.sub_requestedMaxKeepAliveCount_value);
                 holder.sub_maxNotificationsPerPublish=(TextView)vi.findViewById(R.id.sub_maxNotificationsPerPublish_value);
                  
                /************  Set holder with LayoutInflater ************/
                 vi.setTag( holder );
             }
             else 
                 holder=(ViewHolder)vi.getTag();
              
             if(data.size()<=0)
             {
                 holder.sub_id.setText("No Data");
                  
             }
             else
             {
                 /***** Get each Model object from Arraylist ********/
                 tempValues=null;
                 tempValues = ( Subscription ) data.get( position );
                  
                 /************  Set Model values in Holder elements ***********/
 
                  holder.sub_id.setText(tempValues.getSubId().toString());
                  holder.sub_requestedPublishingInterval.setText(tempValues.getRequestedPublishingInterval().toString() );
                  holder.sub_requestedLifetimeCount.setText( tempValues.getRequestedLifetimeCount().toString() );
                  holder.sub_requestedMaxKeepAliveCount.setText( tempValues.getRequestedMaxKeepAliveCount().toString() );
                  holder.sub_maxNotificationsPerPublish.setText( tempValues.getMaxNotificationsPerPublish().toString() );

                  /******** Set Item Click Listner for LayoutInflater for each row *******/
 
                  vi.setOnClickListener(new OnItemClickListener( position ));
             }
             return vi;
         }
          
         @Override
         public void onClick(View v) {
                 Log.v("CustomAdapter", "=====Row button clicked=====");
         }
          
         /********* Called when Item click in ListView ************/
         private class OnItemClickListener  implements OnClickListener{           
             private int mPosition;
              
             OnItemClickListener(int position){
                  mPosition = position;
             }
              
             @Override
             public void onClick(View arg0) {

               SecondActivity sct = (SecondActivity)activity;
 
              /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
                 sct.onItemSubClick(mPosition);
             }               
         }   
     }