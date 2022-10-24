package com.example.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
//import android.os.ServiceConnection;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.TextView;
import android.util.Log;

import com.example.aidlservice.Mytest;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "---MyClient---";
    private Mytest mytestaidl = null;
    private TextView txt = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mytestaidl = Mytest.Stub.asInterface(iBinder);
                try {
                    String s = mytestaidl.getStringFromService();
                    txt.setText(s);
                }catch (RemoteException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG, "onServiceDisconnected");
            }
        };
        Intent intent = new Intent();
        intent.setAction("com.example.service.action");
        intent.setPackage("com.example.aidlservice");
        bindService(intent,connection,BIND_AUTO_CREATE);    }
}