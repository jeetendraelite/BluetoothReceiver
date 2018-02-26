package com.example.jeetendraachtani.bluetoothreciever;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_bluetooth1)
    Button tv_bluetooth1;

    @BindView(R.id.tv_bluetooth2)
    Button tv_bluetooth2;

    @BindView(R.id.tv_bluetooth3)
    Button tv_bluetooth3;

    AudioManager manager;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        takeKeyEvents(true);

        IntentFilter filter3 = new IntentFilter();
        filter3.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter3.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        registerReceiver(mBroadcastReceiver3, filter3);

        tv_bluetooth1.setClickable(false);
        tv_bluetooth2.setClickable(false);
        tv_bluetooth3.setClickable(false);

        tv_bluetooth1.setBackgroundColor(getResources().getColor(R.color.grey));
        tv_bluetooth2.setBackgroundColor(getResources().getColor(R.color.grey));
        tv_bluetooth3.setBackgroundColor(getResources().getColor(R.color.grey));


       /* tv_bluetooth3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick();
            }
        });
*/

    }

    private final BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    Toast.makeText(context, "BlueTooth Connected", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    Toast.makeText(context, "BlueTooth DisConnected", Toast.LENGTH_SHORT).show();
                    break;


            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
           /* if(keyCode==KeyEvent.KEYCODE_ENTER) {
                Log.d(this.getClass().getName(), "KEYCODE_ENTER");
                tv_bluetooth3.setFocusable(true);
                buttonClick();
            }
            else {
                tv_bluetooth3.setFocusable(false);
            }*/


        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            i++;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (i == 1) {
                        Toast.makeText(MainActivity.this, "Single Click", Toast.LENGTH_SHORT).show();

                        if(tv_bluetooth1.isClickable()){
                            tv_bluetooth1.setClickable(false);
                            tv_bluetooth1.setBackgroundColor(getResources().getColor(R.color.grey));
                        }
                        else {
                            tv_bluetooth1.setClickable(true);
                            tv_bluetooth1.setBackgroundColor(getResources().getColor(R.color.greyDark));
                        }

                        tv_bluetooth2.setClickable(false);
                        tv_bluetooth3.setClickable(false);

                        tv_bluetooth2.setBackgroundColor(getResources().getColor(R.color.grey));
                        tv_bluetooth3.setBackgroundColor(getResources().getColor(R.color.grey));
                        buttonClick();
                    } else if (i == 2) {
                        Toast.makeText(MainActivity.this, "Double Click", Toast.LENGTH_SHORT).show();

                        if(tv_bluetooth2.isClickable()){
                            tv_bluetooth2.setClickable(false);
                            tv_bluetooth2.setBackgroundColor(getResources().getColor(R.color.grey));
                        }
                        else {
                            tv_bluetooth2.setClickable(true);
                            tv_bluetooth2.setBackgroundColor(getResources().getColor(R.color.greyDark));
                        }

                        tv_bluetooth1.setClickable(false);
                        tv_bluetooth3.setClickable(false);

                        tv_bluetooth1.setBackgroundColor(getResources().getColor(R.color.grey));
                        tv_bluetooth3.setBackgroundColor(getResources().getColor(R.color.grey));
                        buttonClick();
                    } else if (i == 3) {
                        Toast.makeText(MainActivity.this, "Triple Click", Toast.LENGTH_SHORT).show();

                        if(tv_bluetooth3.isClickable()){
                            tv_bluetooth3.setClickable(false);
                            tv_bluetooth3.setBackgroundColor(getResources().getColor(R.color.grey));
                        }
                        else {
                            tv_bluetooth3.setClickable(false);
                            tv_bluetooth3.setBackgroundColor(getResources().getColor(R.color.greyDark));
                        }

                        tv_bluetooth1.setClickable(false);
                        tv_bluetooth2.setClickable(false);

                        tv_bluetooth1.setBackgroundColor(getResources().getColor(R.color.grey));
                        tv_bluetooth2.setBackgroundColor(getResources().getColor(R.color.grey));
                        buttonClick();
                    }
                 /*   tv_bluetooth1.setFocusable(false);
                    tv_bluetooth2.setFocusable(false);
                    tv_bluetooth3.setFocusable(false);*/
                    i = 0;
                }
            }, 500);
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.d(this.getClass().getName(), "KEYCODE_VOLUME_UP");
                manager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE,
                        AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.d(this.getClass().getName(), "KEYCODE_VOLUME_DOWN");
                manager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER,
                        AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                return true;

            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    public void buttonClick() {
        Toast.makeText(getApplicationContext(), "Bluetooth Event Clicked", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver3);
    }
}
