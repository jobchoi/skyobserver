package com.example.skyobserver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.skyobserver.msmap.MData;
import com.example.skyobserver.msmap.MStation;
import com.example.skyobserver.msmap.MeasuringData;
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

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener {

    public static final int REQUEST_CODE_PERMISSIONS = 1000;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    Marker marker;
    private FusedLocationProviderClient mFusedLocationClient;

    ArrayList<MStation> mStations;
    MData mdata = new MData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


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
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));

                }
            }
        });



        mStations = MainActivity.mStion;
        for (int i = 0; i < mStations.size(); i++) {

            if (mStations.get(i).getDmX().length() > 0) {
                LatLng sLocation = new LatLng(Double.parseDouble(mStations.get(i).getDmX()), Double.parseDouble(mStations.get(i).getDmY()));

                mMap.addMarker(new MarkerOptions().position(sLocation).title(mStations.get(i).getStationName())).showInfoWindow();

                Log.d("process", "좌표 " + i + "  " + mStations.get(i).getDmX() + "  " + Double.parseDouble(mStations.get(i).getDmY()));
                Log.d("process", "이름 " + i + "  " + mStations.get(i).getStationName());
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
                    Toast.makeText(this, "권한 체크 거부", Toast.LENGTH_SHORT).show();
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
               /*  LatLng myLocation =new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(myLocation).title("현재 위치")).showInfoWindow();
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));*/


                    LatLng hanul = new LatLng(35.153469, 126.887775);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(hanul));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));

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
        marker.remove();
        mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()).snippet("미세먼지 수치 : 측정중0").icon(BitmapDescriptorFactory.fromResource(R.drawable.mark5))).showInfoWindow();

        MeasuringData md = new MeasuringData(MapsActivity.this);
        try {


            md.execute(marker.getTitle());

        } catch (Exception e) {
            e.printStackTrace();

        }


        return true;
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
