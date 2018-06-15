package demo.rusan.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class RemoteService extends Service {

    private int mId = 0;
    private IRemoteServiceCallback mCallback;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public int getPid() throws RemoteException {
            return mId;
        }

        @Override
        public void setPid(int id) throws RemoteException {
            mId = id;

            if ( mCallback != null ) {
                mCallback.valueChanged(id);
            }

        }

        @Override
        public void registerCallback(IRemoteServiceCallback callback) throws RemoteException {
            mCallback = callback;
        }

        @Override
        public void unRegisterCallback(IRemoteServiceCallback callback) throws RemoteException {
            mCallback = null;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

}
