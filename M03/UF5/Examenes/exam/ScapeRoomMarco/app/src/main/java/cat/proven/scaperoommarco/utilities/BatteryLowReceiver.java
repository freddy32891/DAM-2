package cat.proven.scaperoommarco.utilities;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cat.proven.scaperoommarco.Game_activity;


public class BatteryLowReceiver extends BroadcastReceiver {
    Game_activity activity;

    public BatteryLowReceiver() {}

    public BatteryLowReceiver(Game_activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Batery Low");
        builder.setMessage("Connect the phone to charge");
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
