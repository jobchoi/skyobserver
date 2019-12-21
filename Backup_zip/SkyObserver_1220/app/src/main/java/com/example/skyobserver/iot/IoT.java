package com.example.skyobserver.iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.skyobserver.R;

public class IoT extends AppCompatActivity {

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot);

        imageButton = findViewById(R.id.cctv);

        ImageButton bt1 = findViewById(R.id.cctv);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IoT.this, "IotBt1 click", Toast.LENGTH_SHORT).show();
                imageButton.setImageDrawable(ContextCompat.getDrawable(imageButton.getContext(), R.drawable.fireoff));
            }
        });
/*        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IoT.this, "cctv", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
