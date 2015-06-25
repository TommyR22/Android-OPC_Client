package com.project.opc;

import static org.opcfoundation.ua.utils.EndpointUtil.sortBySecurityLevel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.opcfoundation.ua.application.Client;
import org.opcfoundation.ua.core.EndpointDescription;
import org.opcfoundation.ua.transport.security.Cert;
import org.opcfoundation.ua.transport.security.KeyPair;
import org.opcfoundation.ua.transport.security.PrivKey;

import com.beardedhen.androidbootstrap.BootstrapButton;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	private static final String PRIVKEY_PASSWORD = "Opc.Ua";
	BootstrapButton connect;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getActionBar().hide();
	
        
		connect = (BootstrapButton)findViewById(R.id.connect);
		connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	  Intent intent = new Intent(v.getContext(), SecondActivity.class);
            	  startActivity(intent);
            	  
            }
        });
		
	}
}
