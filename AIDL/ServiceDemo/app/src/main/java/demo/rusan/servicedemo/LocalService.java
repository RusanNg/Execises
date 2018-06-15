package demo.rusan.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.security.PublicKey;
import java.util.Random;

public class LocalService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.i("rusan", "server onBind: ");
        return mBinder;
    }

    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    public int getRandomNumber() {

        return mGenerator.nextInt(100);

    }

}
