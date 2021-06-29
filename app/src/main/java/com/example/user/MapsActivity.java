package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Timer;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap1;
    private FirebaseAuth mAuth ;
    private PolylineOptions polylineOptions;
    private ArrayList<LatLng> arrayPoints;
    private LatLng startLatLng = new LatLng(0,0);
    private LatLng endLatLng = new LatLng(0,0);
    DatabaseReference databaseReference;
    FirebaseDatabase mDatabase;

    private void drawPath(){
        Polyline polyline1 = mMap1.addPolyline(new PolylineOptions()
        .clickable(true)
        .add(
                startLatLng,
                endLatLng)
        .color(Color.RED)
        .width(20)
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        String na = intent.getStringExtra("name");

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getInstance().getReference().child(na+"/gps"); //"Gps0/gps"
        databaseReference.removeValue();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap1 = googleMap;

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevchildkey) {

                Intent settingIntent = getIntent();
                String na = settingIntent.getStringExtra("name");

                String lati = dataSnapshot.child("latitude").getValue(String.class);
                String lngi = dataSnapshot.child("longitude").getValue(String.class);
                double lat1 = Double.parseDouble(lati);
                double lng1 = Double.parseDouble(lngi);

                LatLng newLocation1 = new LatLng(lat1, lng1);
                mMap1.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation1, 18));
                if(startLatLng.latitude==0&&startLatLng.longitude==0){
                    startLatLng=newLocation1;
                }
                endLatLng = newLocation1;
                drawPath();
                startLatLng = newLocation1;
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

            Intent intent = getIntent();
            String na = intent.getStringExtra("name");

            databaseReference = mDatabase.getInstance().getReference().child(na);
            DatabaseReference state = mDatabase.getReference(na+"/state");

            state.setValue("0");

        }

        if (id == R.id.menu_로그아웃) {
            finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }

}

