package chinna.sandyz.com.assignment121;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView)findViewById(R.id.txtview);

        getPercentage();
    }

    private void getPercentage(){

        //create object of broadcast receiver
        BroadcastReceiver myReciever=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                context.unregisterReceiver(this);
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;

                if (currentLevel >= 0 && scale > 0) {
                    level = (currentLevel * 100) / scale;
                }
                textView.setText("Battery Level Remaining: " + level + "%");

            }
        };
            //intent filter to filter the broadcast message we want
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        // register the receiver
        registerReceiver(myReciever, batteryLevelFilter);

    }

}
