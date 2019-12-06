package com.example.skyobserver.iot;

import android.content.Context;

interface IoTSevice {
    void sendDataAsync();
    void myToast(String what, Context context);
    void homecam();
}
