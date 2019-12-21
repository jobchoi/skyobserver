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
        setContentView(R.layout.activity_iot);

        ImageButton bt1 = findViewById(R.id.iotbutton1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IoT.this, "cctv click", Toast.LENGTH_SHORT).show();
                Intent cctv = new Intent(getApplicationContext(), cctv.class);
                startActivity(cctv);
            }
        });
    }
}
