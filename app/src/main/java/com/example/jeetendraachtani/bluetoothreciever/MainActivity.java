package com.example.jeetendraachtani.bluetoothreciever;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    @BindView(R.id.tv_bluetooth4)
    Button tv_bluetooth4;

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

        tv_bluetooth4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick();
            }
        });

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

        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                Log.d(this.getClass().getName(), "KEYCODE_ENTER");
             //   Toast.makeText(this, "KeyCOde ENter Pressed", Toast.LENGTH_SHORT).show();
                buttonClick();
                return true;

            default:
                return super.onKeyUp(keyCode, event);

        }
    }

    public void buttonClick()
    {
        Toast.makeText(getApplicationContext(), "Bluetooth Event Clicked", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver3);
    }
}
