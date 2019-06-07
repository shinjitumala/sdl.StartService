package jp.ac.titech.itpro.sdl.startservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class Service3 extends Service {
    private final static String TAG = Service3.class.getSimpleName();
    public final static String EXTRA_MYARG = "うんこ";
    private final static int MSG_TAG = 69;

    private final static String ACTION_ANSWER = "黄色くて温かい飲み物";
    private final static String EXTRA_ANSWER = "69neinnein";

    private Looper looper;
    private Handler handler;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate in " + Thread.currentThread());
        HandlerThread th = new HandlerThread("service3 thread");
        th.start();
        looper = th.getLooper();
        handler = new Handler(looper){
            public void handleMessage(Message message){
                Log.d(TAG, "handleMessage in " + Thread.currentThread());
                onHandleIntent((Intent) message.obj);
                stopSelf(message.arg1);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int start_id){
        Log.d(TAG, "onStartCommand in " + Thread.currentThread());
        Message message = handler.obtainMessage(MSG_TAG, start_id, 0, intent);
        handler.sendMessage(message);
        return super.onStartCommand(intent, flags, start_id);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy in " + Thread.currentThread());
        looper.quit();
        super.onDestroy();
    }

    protected void onHandleIntent(Intent intent){
        Log.d(TAG, "onHandleIntent in " + Thread.currentThread());
        Log.d(TAG, "mvarg = " + intent.getStringExtra(EXTRA_MYARG));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException chinchin){
            chinchin.printStackTrace();
        }
        Intent new_intent = new Intent();
        new_intent.setAction(ACTION_ANSWER);
        new_intent.putExtra(EXTRA_ANSWER, "BURIBURIBURI");
        sendBroadcast(new_intent);
        Log.d(TAG, "onHandleIntent in " + Thread.currentThread());
        Log.d(TAG, "sent broadcast after delay");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
