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
public class CustomAdapterMonitoredItem extends BaseAdapter implements OnClickListener {
          
         /*********** Declare Used Variables *********/
         private Activity activity;
         private ArrayList data;
         private static LayoutInflater inflater=null;
         public Resources res;
         MonitoredItem tempValues=null;
         int i=0;
          
         /*************  CustomAdapter Constructor *****************/
         public CustomAdapterMonitoredItem(Activity a, ArrayList d,Resources resLocal) {
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
             public TextView id_M_value;
             public TextView value_M_value;
             public TextView status_M_value;
             public TextView time_M_value;
             
         }
      
         /****** Depends upon data size called for each row , Create each ListView row *****/
         public View getView(int position, View convertView, ViewGroup parent) {
              
             View vi = convertView;
             ViewHolder holder;
              
             if(convertView==null){
                  
                 /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                 vi = inflater.inflate(R.layout.listview_monitor_layout, null);
                  
                 /****** View Holder Object to contain listview_layout.xml file elements ******/
 
                 holder = new ViewHolder();
                 holder.id_M_value = (TextView) vi.findViewById(R.id.id_M_value);
                 holder.value_M_value=(TextView)vi.findViewById(R.id.value_M_value);
                 holder.status_M_value=(TextView)vi.findViewById(R.id.status_M_value);
                 holder.time_M_value=(TextView)vi.findViewById(R.id.time_M_value);
                 
                  
                /************  Set holder with LayoutInflater ************/
                 vi.setTag( holder );
             }
             else 
                 holder=(ViewHolder)vi.getTag();
              
             if(data.size()<=0)
             {
                 holder.id_M_value.setText("No Data");
                  
             }
             else
             {
                 /***** Get each Model object from Arraylist ********/
                 tempValues=null;
                 tempValues = ( MonitoredItem ) data.get( position );
                  
                 /************  Set Model values in Holder elements ***********/
 
                  holder.id_M_value.setText(tempValues.getId_M().toString());
                  holder.status_M_value.setText(tempValues.getStatus_M().toString() );
                  holder.time_M_value.setText( tempValues.getTime_M().toString() );
                  holder.value_M_value.setText( tempValues.getValue_M().toString() );
                  
                  tempValues.setViewholder(holder);
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
               
               sct.onItemMonClick(mPosition);
              /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
                 //sct.onItemSubClick(mPosition);
             }               
         }   
         
         public void update(){
        	 activity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					notifyDataSetChanged();
					notifyDataSetInvalidated();
					
				}
			});
         }
         
     }