package com.example.skyobserver;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.skyobserver.msmap.MData;
import com.example.skyobserver.msmap.MStation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener {

    public static final int REQUEST_CODE_PERMISSIONS = 1000;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    Marker marker;
    Marker resultMarker=null;
    private FusedLocationProviderClient mFusedLocationClient;
    private Geocoder geocoder;
    ArrayList<MStation> mStations;
    MData mdata = new MData();
    private EditText seditText;
    private Button sbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        seditText=findViewById(R.id.mapsearchedit);
        sbutton=findViewById(R.id.mapsearchbtn);


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



    }


    @Override
    protected void onStart() {
        mGoogleApiClient.connect();

        super.onStart();
    }


    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        geocoder=new Geocoder(this);

        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=seditText.getText().toString();
                List<Address> addressList = null;

                if(resultMarker!=null){
                    resultMarker.remove();
                }
                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            str, // 주소
                            10); // 최대 검색 결과 개수
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(addressList.get(0).toString());
                // 콤마를 기준으로 split
                String []splitStr = addressList.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
                System.out.println(address);

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                System.out.println(latitude);
                System.out.println(longitude);


                // 좌표(위도, 경도) 생성
                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                // 마커 생성
                MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title("search result");
                mOptions2.snippet(address);
                mOptions2.position(point);
                // 마커 추가

                resultMarker=mMap.addMarker(mOptions2);
                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));
            }
        });



      /*         // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-35.153469, 126.887775);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
*/
/*
        LatLng hanul = new LatLng(35.153469, 126.887775);
        mMap.addMarker(new MarkerOptions().position(hanul).title("한울")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hanul));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0623627797"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });*/

/*

        LatLng hanul = new LatLng(35.153469, 126.887775);
        mMap.addMarker(new MarkerOptions().position(hanul).title("한울")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hanul));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));*/

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions
                    (this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);
            return;
        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng myLocation =new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));

                }
            }
        });

        mStations = MainActivity.mStion;
        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MapsActivity.this);
        mMap.setInfoWindowAdapter(adapter);


        for (int i = 0; i < mStations.size(); i++) {


            if (mStations.get(i).getDmX().length() > 0) {
                LatLng sLocation = new LatLng(Double.parseDouble(mStations.get(i).getDmX()), Double.parseDouble(mStations.get(i).getDmY()));


                if(mStations.get(i).getPm10value().equals("-")){
                    mMap.addMarker(new MarkerOptions().position(sLocation).title(mStations.get(i).getStationName()).snippet(String.valueOf(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark5)));
                }
                else if(0 <= Integer.parseInt(mStations.get(i).getPm10value()) && 30 >= Integer.parseInt(mStations.get(i).getPm10value())) {
                    mMap.addMarker(new MarkerOptions().position(sLocation).snippet(String.valueOf(i)).title(mStations.get(i).getStationName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark1)));

                } else if (31 <= Integer.parseInt(mStations.get(i).getPm10value()) && 80 >= Integer.parseInt(mStations.get(i).getPm10value())){
                    mMap.addMarker(new MarkerOptions().position(sLocation).snippet(String.valueOf(i)).title(mStations.get(i).getStationName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark2)));

                } else if (81 <= Integer.parseInt(mStations.get(i).getPm10value()) && 150 >= Integer.parseInt(mStations.get(i).getPm10value())) {
                    mMap.addMarker(new MarkerOptions().position(sLocation).snippet(String.valueOf(i)).title(mStations.get(i).getStationName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark3)));

                }else {
                    mMap.addMarker(new MarkerOptions().position(sLocation).snippet(String.valueOf(i)).title(mStations.get(i).getStationName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark4)));

                }
/*
            if (mStations.get(i).getDmX().length() > 0) {
                LatLng sLocation = new LatLng(Double.parseDouble(mStations.get(i).getDmX()), Double.parseDouble(mStations.get(i).getDmY()));

               // mMap.addMarker(new MarkerOptions().position(sLocation).title(mStations.get(i).getStationName())).showInfoWindow();

                if(mStations.get(i).getPm10value().equals("-")){
                    mMap.addMarker(new MarkerOptions().position(sLocation).title(mStations.get(i).getStationName()).snippet("점검중").icon(BitmapDescriptorFactory.fromResource(R.drawable.mark5)));
                }
                else if(0 <= Integer.parseInt(mStations.get(i).getPm10value()) && 30 >= Integer.parseInt(mStations.get(i).getPm10value())) {
                    mMap.addMarker(new MarkerOptions().position(sLocation).title(mStations.get(i).getStationName()).snippet("미세먼지 수치 : "+mStations.get(i).getPm10value()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark1)));
                } else if (31 <= Integer.parseInt(mStations.get(i).getPm10value()) && 80 >= Integer.parseInt(mStations.get(i).getPm10value())){
                    mMap.addMarker(new MarkerOptions().position(sLocation).title(mStations.get(i).getStationName()).snippet("미세먼지 수치 : "+mStations.get(i).getPm10value()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark2)));
                } else if (81 <= Integer.parseInt(mStations.get(i).getPm10value()) && 150 >= Integer.parseInt(mStations.get(i).getPm10value())) {
                    mMap.addMarker(new MarkerOptions().position(sLocation).title(mStations.get(i).getStationName()).snippet("미세먼지 수치 : "+mStations.get(i).getPm10value()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark3)));
                }else {
                    mMap.addMarker(new MarkerOptions().position(sLocation).title(mStations.get(i).getStationName()).snippet("미세먼지 수치 : "+mStations.get(i).getPm10value()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark4)));
                }
*/
                            //.snippet("미세먼지 수치 : "+mdata.getPm10Value())
//
//                Log.d("process", "좌표 " + i + "  " + mStations.get(i).getDmX() + "  " + Double.parseDouble(mStations.get(i).getDmY()));
//                Log.d("process", "이름 " + i + "  " + mStations.get(i).getStationName());
            } else {
                continue;
            }

            mMap.setOnMarkerClickListener(this);





        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                   // Toast.makeText(this, "권한 체크 거부", Toast.LENGTH_SHORT).show();
                }
        }

    }

    public void onLastLocationButtonClicked(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions
                    (this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);
            return;
        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
               LatLng myLocation =new LatLng(location.getLatitude(),location.getLongitude());
                  //  mMap.addMarker(new MarkerOptions().position(myLocation).title("현재 위치")).showInfoWindow();
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));

//
//                    LatLng hanul = new LatLng(35.153469, 126.887775);
//                    mMap.moveCamera(CameraUpdateFactory.newLatLng(hanul));
//                    mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));

                }


            }


        });


    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }





    @Override
    public boolean onMarkerClick(Marker marker) {
        this.marker=marker;
       // marker.remove();



        marker.showInfoWindow();
        //mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()).snippet("미세먼지 수치 : "+mdata.getPm10Value())).showInfoWindow();
      //  mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()).snippet("미세먼지 수치 : "+mdata.getPm10Value()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark1))).showInfoWindow();


        //
//        MeasuringData md = new MeasuringData(MapsActivity.this);
//        try {
//
//
//            md.execute(marker.getTitle());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }


        return true;
    }

    public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private Activity context;

        public CustomInfoWindowAdapter(Activity context){
            this.context = context;

        }

        @Override
        public View getInfoWindow(Marker marker) {
            View view = context.getLayoutInflater().inflate(R.layout.markerinfo, null);

            TextView tvTitle = view.findViewById(R.id.markname);
            LinearLayout tv10title= view.findViewById(R.id.mark10vtitel);
            LinearLayout tv25title= view.findViewById(R.id.mark25vtitel);
            if(marker.getTitle().equals("search result")){
               tvTitle.setText(marker.getSnippet());
               tv10title.setVisibility(View.GONE);
               tv25title.setVisibility(View.GONE);

            }else {

                int position = Integer.parseInt(marker.getSnippet());

                TextView tvSubTitle = view.findViewById(R.id.mark10v);
                TextView tvSubTitle2 = view.findViewById(R.id.mark25v);
                tv10title.setVisibility(View.VISIBLE);
                tv25title.setVisibility(View.VISIBLE);
                tvTitle.setText(marker.getTitle());
                if (mStations.get(position).getPm10value().equals("-")) {
                    tvSubTitle.setText("점검중");
                } else {
                    tvSubTitle.setText(mStations.get(position).getPm10value());
                }
                if (mStations.get(position).getPm25value().equals("-")) {
                    tvSubTitle2.setText("점검중");
                } else {
                    tvSubTitle2.setText(mStations.get(position).getPm25value());
                }
            }
            return view;
        }

        @Override
        public View getInfoContents(Marker marker) {

            return null;
        }
    }





    public void secondLoading(final MData data) {
        this.mdata = data;
        if (mdata.getPm10Grade1h().equals("1")) {
            mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()).snippet("미세먼지 수치 : "+mdata.getPm10Value()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark1))).showInfoWindow();

        } else if (mdata.getPm10Grade1h().equals("2")) {
            mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()).snippet("미세먼지 수치 : "+mdata.getPm10Value()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark2))).showInfoWindow();
        } else if (mdata.getPm10Grade1h().equals("3")) {
            mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()).snippet("미세먼지 수치 : "+mdata.getPm10Value()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark3))).showInfoWindow();

        } else if (mdata.getPm10Grade1h().equals("4")) {
            mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()).snippet("미세먼지 수치 : "+mdata.getPm10Value()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mark4))).showInfoWindow();
        } else {
            mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()).snippet("미세먼지 수치 : 측정중").icon(BitmapDescriptorFactory.fromResource(R.drawable.mark1))).showInfoWindow();

        }
        Log.d("process","마커 "+mdata.getPm10Grade1h());
    }
}
