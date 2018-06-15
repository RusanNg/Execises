// IRemoteService.aidl
package demo.rusan.servicedemo;

// Declare any non-default types here with import statements
import demo.rusan.servicedemo.IRemoteServiceCallback;

interface IRemoteService {

        int getPid();

        void setPid(int id);

        void registerCallback(IRemoteServiceCallback callback);

        void unRegisterCallback(IRemoteServiceCallback callback);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
