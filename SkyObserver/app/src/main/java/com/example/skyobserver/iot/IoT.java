package com.example.skyobserver.iot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.skyobserver.R;

public class IoT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot_state);

        ImageButton bt1 = findViewById(R.id.iotbutton1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IoT.this, "IotBt1 click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void iotState(View view) {
        if(view.getId() == R.id.iotbutton1){
            Toast.makeText(this, "IoT button 1", Toast.LENGTH_SHORT).show();
        } else if(view.getId() == R.id.iotbutton2){
            Toast.makeText(this, "IoT button 2", Toast.LENGTH_SHORT).show();
        } else if(view.getId() == R.id.iotbutton3){
            Toast.makeText(this, "IoT button 3", Toast.LENGTH_SHORT).show();
        } else if(view.getId() == R.id.iotbutton4){
            Toast.makeText(this, "IoT button 4", Toast.LENGTH_SHORT).show();
        }  else if(view.getId() == R.id.iotbutton5){
            Toast.makeText(this, "IoT button 5", Toast.LENGTH_SHORT).show();
        }  else if(view.getId() == R.id.iotbutton6){
            // picam
            Toast.makeText(this, "IoT button 6", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IoT.this, PIcam.class);
            startActivity(intent);
        }
    }
}
