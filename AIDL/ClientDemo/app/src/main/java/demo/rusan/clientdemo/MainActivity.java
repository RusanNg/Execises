package demo.rusan.clientdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean mBound;

    private Messenger messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent();
        intent.setAction("demo.rusan.servicedemo.messengerService");
        intent.setPackage("demo.rusan.servicedemo");


//        Log.i("rusan", "onStart: " + bindService(intent, connection, BIND_AUTO_CREATE) );



    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBound = true;
            Log.i("rusan", "onServiceConnected: ");

            messenger = new Messenger(service);

            try {
                sayHello();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.i("rusan", "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onStop() {
        super.onStop();

//        if (mBound) {
//            unbindService(connection);
//        }

    }

    public void sayHello() throws RemoteException {
        if (!mBound) {
            return;
        }

        Message msg = Message.obtain(null, 1, 0, 0);

        messenger.send(msg);
    }


}
