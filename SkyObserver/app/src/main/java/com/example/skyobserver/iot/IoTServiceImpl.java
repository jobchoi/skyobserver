package com.example.skyobserver.iot;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class IoTServiceImpl implements IoTSevice{

    private Context context;

    public IoTServiceImpl(Context context){
        this.context =  context;
    }

    @Override
    public void myToast(String what, Context context) {
        Toast.makeText(context, what, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void homecam() {
        Intent intent = new Intent(this.context, PIcam.class);
        context.startActivity(intent);
    }


    @Override
    public void sendDataAsync() {

    }

    protected class ToServer extends AsyncTask<Void, Void, Void>{

    }
}
