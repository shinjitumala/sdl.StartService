package jp.ac.titech.itpro.sdl.startservice;

import android.bluetooth.le.ScanFilter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    private Looper looper;
    private Handler handler;

    private final static String ACTION_ANSWER = "黄色くて温かい飲み物";
    private final static String EXTRA_ANSWER = "69neinnein";

    private BroadcastReceiver receiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate in " + Thread.currentThread());
        setContentView(R.layout.activity_main);

        this.receiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent){
                Log.d(TAG, "onReceive");
                String action = intent.getAction();
                if(action == null) return;
                switch (action){
                    case ACTION_ANSWER:
                        onHandleIntent(intent);
                        break;
                }
            }
        };

        this.filter = new IntentFilter();
        filter.addAction(ACTION_ANSWER);

        this.registerReceiver(receiver, filter);
    }

    public void onClickTest1(View v) {
        Log.d(TAG, "onClickTest1 in " + Thread.currentThread());
        Intent intent = new Intent(this, Service1.class);
        intent.putExtra(Service1.EXTRA_MYARG, "Hello, Service1");
        startService(intent);
    }

    public void onClickTest2(View v) {
        Log.d(TAG, "onClickTest2 in " + Thread.currentThread());
        Intent intent = new Intent(this, Service2.class);
        intent.putExtra(Service2.EXTRA_MYARG, "Hello, Service2");
        startService(intent);
    }

    public void onClickTest3(View v) {
        Log.d(TAG, "onClickTest3 in" + Thread.currentThread());
        Intent intent = new Intent(this, Service3.class);
        intent.putExtra(Service3.EXTRA_MYARG, "Hello, Service3");
        startService(intent);
    }

    protected void onHandleIntent(Intent intent){
        Log.d(TAG, "onHandleIntent in " + Thread.currentThread());
        Log.d(TAG, "mvarg = " + intent.getStringExtra(EXTRA_ANSWER));
        Toast.makeText(this, "ビヨーン", Toast.LENGTH_SHORT).show();
    }
}
