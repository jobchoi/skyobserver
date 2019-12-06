package com.example.skyobserver.iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.R;

public class IoT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot_state);

    }

    public void iotState(View view) {
        IoTServiceImpl ism = new IoTServiceImpl(this);

        if(view.getId() == R.id.iotbutton1){
            ism.myToast("led 1", this);
        } else if(view.getId() == R.id.iotbutton2){
            ism.myToast("led 2", this);
        } else if(view.getId() == R.id.iotbutton3){
            ism.myToast("led 3", this);
        } else if(view.getId() == R.id.iotbutton4){
            ism.myToast("led 4",this);
        }  else if(view.getId() == R.id.iotbutton5){
            ism.myToast("led 5",this);
        }  else if(view.getId() == R.id.iotbutton6){
            // picam
            ism.myToast("picam", this);
            ism.homecam();

        }
    }
}
