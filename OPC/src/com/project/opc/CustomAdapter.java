package com.project.opc;

import java.util.ArrayList;

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
public class CustomAdapter extends BaseAdapter implements OnClickListener {
          
         /*********** Declare Used Variables *********/
         private Activity activity;
         private ArrayList data;
         private static LayoutInflater inflater=null;
         public Resources res;
         Endpoint tempValues=null;
         int i=0;
          
         /*************  CustomAdapter Constructor *****************/
         public CustomAdapter(Activity a, ArrayList d,Resources resLocal) {
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
             public TextView url;
             public TextView level;
             public TextView mode;
             public TextView protocol;
             public TextView policy;
			
         }
      
         /****** Depends upon data size called for each row , Create each ListView row *****/
         public View getView(int position, View convertView, ViewGroup parent) {
              
             View vi = convertView;
             ViewHolder holder;
              
             if(convertView==null){
                  
                 /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                 vi = inflater.inflate(R.layout.listview_layout, null);
                  
                 /****** View Holder Object to contain listview_layout.xml file elements ******/
 
                 holder = new ViewHolder();
                 holder.url = (TextView) vi.findViewById(R.id.url);
                 holder.level=(TextView)vi.findViewById(R.id.level);
                 holder.mode=(TextView)vi.findViewById(R.id.mode);
                 holder.protocol=(TextView)vi.findViewById(R.id.protocol);
                 holder.policy=(TextView)vi.findViewById(R.id.policy);
                  
                /************  Set holder with LayoutInflater ************/
                 vi.setTag( holder );
             }
             else 
                 holder=(ViewHolder)vi.getTag();
              
             if(data.size()<=0)
             {
                 holder.url.setText("No Data");
                  
             }
             else
             {
                 /***** Get each Model object from Arraylist ********/
                 tempValues=null;
                 tempValues = ( Endpoint ) data.get( position );
                  
                 /************  Set Model values in Holder elements ***********/
 
                  holder.url.setText(tempValues.getUrl() );
                  holder.level.setText(tempValues.getSlevel() );
                  holder.mode.setText( tempValues.getSmode() );
                  holder.protocol.setText( tempValues.getProtocol() );
                  holder.policy.setText( tempValues.getSpolicy() );

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
                 sct.onItemClick(mPosition);
             }               
         }   
     }