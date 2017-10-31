package com.atguigu.contexttest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyApplication application = (MyApplication) this.getApplication();
        String data = application.getData();
        Log.e("TAG", "data = " + data);
        return super.onStartCommand(intent, flags, startId);
    }
}
