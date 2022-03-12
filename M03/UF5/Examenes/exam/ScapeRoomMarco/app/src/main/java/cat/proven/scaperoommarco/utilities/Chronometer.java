package cat.proven.scaperoommarco.utilities;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.Timer;
import java.util.TimerTask;

import cat.proven.scaperoommarco.Game_activity;

public class Chronometer extends Service {

    //attributes that we need
    private Timer timer;
    private static final long INVERVAL_UPGRADE = 10;
    public static Game_activity gameActivityListener;
    private double valueChrono = 0;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                // retrieve a method from gameactivity
                if(gameActivityListener != null) {
                    gameActivityListener.startChronometer(valueChrono);
                }
            }
        };
    }

    /**
     * Instance activity to request data of service
     * @param ga
     */
    public static void setUpdateListener(Game_activity ga){gameActivityListener = ga;}

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) timer.cancel();
    }

    private void incrementChrono() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                valueChrono += 0.01;//suma
                handler.sendEmptyMessage(0);//Desde el hilo enviamos una señal al handler cuando
                // ha finalizado la tarea (incrementar un valor) para que actualize la activity
            }
        }, 0, INVERVAL_UPGRADE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        incrementChrono();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
