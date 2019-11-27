package com.example.skyobserver.iot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.skyobserver.R;

public class PIcam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picam);
        Toast.makeText(this, "IoT : pi카메라 확인", Toast.LENGTH_SHORT).show();
    }
}
