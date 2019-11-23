package com.example.skyobserver;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.skyobserver.board.BoardActivity;
import com.example.skyobserver.iot.IoT;
import com.example.skyobserver.member.Login;
import com.example.skyobserver.member.Mypage;
import com.example.skyobserver.msmap.MStation;
import com.example.skyobserver.msmap.MeasuringStation;
import com.example.skyobserver.nearstation.NearSt_Fragment;
import com.example.skyobserver.statistics.Statistics;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager pager;
    public String buf ;
    Fragment main_Fragment;
    Fragment nearSt_Fragment;

    public static GeoPoint tmset;
    public static final int REQUEST_CODE_MENU = 101;
    public static final int REQUEST_CODE_BOARD = 102;
    private boolean signupActivityLock = false;
    public static final int REQUEST_CODE_PERMISSIONS = 1009;


    public static ArrayList<MStation> mStion = new ArrayList<>();

    ImageView profile;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
        buf = userPref.getString("emails","");


        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
       /* DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.container);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/

        DrawerLayout drawer;
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview=navigationView.getHeaderView(0);

         this.imageView=headerview.findViewById(R.id.headerimageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  로그인 상태에 따라 Login Activity
                signCaller(v, MainActivity.this);
            }
        });


        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        final TabLayout tabs = findViewById(R.id.tabs);


        main_Fragment = new Main_Fragment();
        nearSt_Fragment = new NearSt_Fragment();

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), tabs.getTabCount());

        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                int position = tab.getPosition();


                if (position == 0) {
                    toolbar.setTitle("Main");

                } else if (position == 1) {

                    toolbar.setTitle("Near Station");
                }
//
            }




            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        Intent intent2 = new Intent(getApplicationContext(), BoardActivity.class);
                       startActivity(intent2);


                        return true;
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "IoT", Toast.LENGTH_LONG).show();
                        if(signupActivityLock){
                            Intent intent_iot = new Intent(getApplicationContext(), IoT.class);
                            startActivity(intent_iot);
                        }
                        return true;
                    case R.id.tab3:
                        MeasuringStation ms = new MeasuringStation(MainActivity.this);
                        try {
                            ms.execute().get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_MENU);

                        return true;
                }

                return false;
            }
        });





    }



    @Override
    protected void onPostResume() {
        super.onPostResume();

        // =========================================
        // 로그인이 되어 있지 않은 상태 -> 로그인 -> 메인액티비티 실행 -> setContentView() 실행이 제대로 이루어지지 않음
        // onCreate(); -> 메인 액티비티가 실행전에 다른 액티비티가 실행되서 그런것으로 확인됨
        // onPostResume()을 이용하여 다른 액티비티가 먼저 실행되더라도 실행되도록 보완.
       // setContentView(R.layout.activity_main);
       /* Button signOut = findViewById(R.id.nav_send);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clear실행", Toast.LENGTH_SHORT).show();
                clearState();
*/
            /*    // DEBUG용 - SharedPreferences  값 확인
                SharedPreferences userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
                Log.d("restoreState값확인 : ",userPref.getAll().values().toString());

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);*/
////            }
//        });

        //Toast.makeText(this, "onPostResume실행", Toast.LENGTH_SHORT).show();
        restoreState();
    }

    protected void restoreState(){
        SharedPreferences userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview=navigationView.getHeaderView(0);

        TextView idtextView =headerview.findViewById(R.id.getidtextView);
        TextView emailtextView =headerview.findViewById(R.id.getemailtextView);



        if (userPref.getAll().values().toString().length()>2 ) {
            // 필요한 형식을 가져오가너 getAll로 모든 값을 사용.
//            getData = userPref.getString("name", "");
//            getData = userPref.getString("id","");
            Log.d("restoreState값확인 : ",userPref.getAll().values().toString());


            idtextView.setText(userPref.getString("email", ""));
            String pImge = userPref.getString("filename","");

            Log.d("restoreState값확인 : ",pImge);


                          RequestOptions requestOptions = new RequestOptions();
               requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
               requestOptions.skipMemoryCache(true);
               requestOptions.signature(new ObjectKey(System.currentTimeMillis()));
               requestOptions.transform(new CenterCrop(), new RoundedCorners(20));


                Glide.with(this)
                    .load(pImge)
                    .apply(requestOptions)
                    .into(imageView);

            Log.d("ProfileMypage :",pImge);


            signupActivityLock = true;
        }
    }

    public void signCaller(View view, MainActivity mainActivity) {
        if(!signupActivityLock){
            Log.d("signupActivityLock_확인",Boolean.toString(signupActivityLock));

            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }

    // 모든 Preference 데이터 초기화
    protected void clearState() {
        SharedPreferences userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();

      //  Toast.makeText(this, "clearState실행", Toast.LENGTH_SHORT).show();
        //TextView idtextView =findViewById(R.id.getidtextView);
        //idtextView.clearComposingText();
        signupActivityLock = false;
        editor.clear();
        editor.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions
                    (this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);
            return;
        }



        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
           Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            GeoPoint lastLoc = new GeoPoint(location.getLongitude(), location.getLatitude());

            //LatLng hanul = new LatLng(35.153469, 126.887775);

           // GeoPoint lastLoc = new GeoPoint(hanul.longitude,hanul.latitude);

            GeoPoint tmset = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, lastLoc);
            this.tmset=tmset;
           // Log.d("process",tmset.getX()+"      "+tmset.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }


        super.onStart();
    }

    public void updateProducts(final ArrayList<MStation> mStations) {
        this.mStion = mStations;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
/*

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.container);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this, "마이페이지 확인", Toast.LENGTH_SHORT).show();

            // 로그인 한 상태이면 Intent 활성화
            // SharedPreferences를 이용하여 Activity 활성화 결정,
            // signupActivityLock값을 flag로 하여 로그인 상태를 파악.
            if (signupActivityLock) {
//                Toast.makeText(this, "로그인:OK, Intent 동작 ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Mypage.class);
                startActivity(intent);
            } else {
                // 로그인 하지 않은 상태면 비활성화
//                Toast.makeText(this, "로그인:NG, Intent 비동작 ", Toast.LENGTH_SHORT).show();
            }


        } else if (id == R.id.nav_statistics) {
            Intent intent = new Intent(MainActivity.this, Statistics.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            clearState();
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            View headerview=navigationView.getHeaderView(0);

            // 프로필 이미지 초기화

            imageView.setImageResource(R.drawable.logo);

//            android.R.drawable.picture_frame

            TextView idtextView =headerview.findViewById(R.id.getidtextView);
            idtextView.setText("로그인을 해주세요");
            signupActivityLock=false;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    class MyPagerAdapter extends FragmentStatePagerAdapter {


        private int tabCount;

        public MyPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }


        @Override
        public Fragment getItem(int position) {
            Fragment selected = null;
            if (position == 0) {
                selected = main_Fragment;

            } else if (position == 1) {
                selected = nearSt_Fragment;
            }
            return selected;
        }

        @Override
        public int getCount() {
            return tabCount;
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearState();
    }
}
