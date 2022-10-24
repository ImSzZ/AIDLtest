package com.example.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.Date;

public class MyService extends Service {
    private static final String TAG = "---MyService---";
    private static final String TIME = "---TIME---";
    Date d = new Date();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return new Mybinder();
    }

    class Mybinder extends Mytest.Stub {

        @Override
        //public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        //return "777";
        public String getStringFromService() throws RemoteException {
            Log.d(TAG, "service 端的方法被client调用了");
            Log.d(TIME,d.toString());
            return "777";
        }
    }
}
