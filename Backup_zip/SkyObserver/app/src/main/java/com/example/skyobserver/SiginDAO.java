package com.example.skyobserver;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

public class SiginDAO {
    public void sendSigninData(){
        String url = Common.SERVER_URL + "/TheWM/signin.so";

        //NetworkTask networkTask = new NetworkTask(url,null);
      //  networkTask.execute();
        Log.d("쓰레드(doInBackground)","0");
    }

    public class NetworkTask extends AsyncTask<Void, Void, String>{
        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values){
            this.url = url;
            this.values = values;
            Log.d("쓰레드(doInBackground)","1");
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();

            result = requestHttpURLConnection.request(url,values);
            Log.d("쓰레드(doInBackground)",result.toString());

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("리턴값 확인 ",s);
        }
    }
}
