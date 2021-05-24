package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Timer;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap1;
    private Button btnRevoke, btnLogout;
    private FirebaseAuth mAuth ;
    private ChildEventListener mChildEventListener;
    private String latitude;
    private String longitude;
    private int co;
    private int lim;
    private Timer timer = new Timer();
    private int countdown = 1;
    private int count = 1;

    DatabaseReference databaseReference;
    FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //bicycleId name = new bicycleId();
        //String na = name.getbicyclename();
        Intent intent = getIntent();
        String na = intent.getStringExtra("name");

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getInstance().getReference().child(na+"/gps"); //"Gps0/gps"
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap1 = googleMap;

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevchildkey) {

                //bicycleId name = new bicycleId();
                //String na = name.getbicyclename();
                Intent settingIntent = getIntent();
                String na = settingIntent.getStringExtra("name");

                String lati = dataSnapshot.child("latitude").getValue(String.class);
                String lngi = dataSnapshot.child("longitude").getValue(String.class);
                double lat1 = Double.parseDouble(lati);
                double lng1 = Double.parseDouble(lngi);

                LatLng newLocation1 = new LatLng(lat1, lng1);

                mMap1.addMarker(new MarkerOptions().position(newLocation1).title(na));
                mMap1.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation1, 18));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        // Add a marker in Sydney and move the camera

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu) ;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_반납) {
            Intent settingIntent = new Intent(this, ReturnActivity.class);
            startActivity(settingIntent);

            //bicycleId name = new bicycleId();
            //String na = name.getbicyclename();
            Intent intent = getIntent();
            String na = intent.getStringExtra("name");

            databaseReference = mDatabase.getInstance().getReference().child(na);
            DatabaseReference state = mDatabase.getReference(na+"/state");

            state.setValue("0");
            //현석이가 만든 코드
        }

        if (id == R.id.menu_로그아웃) {
            finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }

}

