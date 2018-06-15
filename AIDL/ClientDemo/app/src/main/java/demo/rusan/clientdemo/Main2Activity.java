package demo.rusan.clientdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import demo.rusan.servicedemo.IRemoteService;
import demo.rusan.servicedemo.IRemoteServiceCallback;

public class Main2Activity extends AppCompatActivity {

    IRemoteService mService = null;

    private boolean binding = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                if (mService != null) {
                    try {
                        mService.setPid(5566);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent();

        intent.setAction("demo.rusan.servicedemo.remoteService");
        intent.setPackage("demo.rusan.servicedemo");

        Log.i("rusan client", "onStart: " + bindService(intent, mConnection, BIND_AUTO_CREATE) );
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.i("rusan client", "remote service onServiceConnected");

            binding = true;

            mService = IRemoteService.Stub.asInterface(service);

            try {
                mService.registerCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            binding = false;

        }
    };

    private IRemoteServiceCallback.Stub callback = new IRemoteServiceCallback.Stub() {
        @Override
        public void valueChanged(int value) throws RemoteException {

            Toast.makeText(getApplicationContext(), "rusan @ client valueChanged: " + value, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if ( binding ) {
            try {
                mService.unRegisterCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            unbindService(mConnection);
        }
    }
}



