package com.example.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private Button btnRevoke, btnLogout;
    private FirebaseAuth mAuth ;
    private ChildEventListener mChildEventListener;
    private String latitude;
    private String longitude;
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
        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnRevoke = (Button) findViewById(R.id.btn_revoke);
        btnLogout.setOnClickListener(this);
        btnRevoke.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getInstance().getReference().child("Gps/gps");

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
        mMap = googleMap;

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevchildkey) {

                String lati = dataSnapshot.child("latitude").getValue(String.class);
                String lngi = dataSnapshot.child("longitude").getValue(String.class);
                double lat = Double.parseDouble(lati);
                double lng = Double.parseDouble(lngi);

                LatLng newLocation = new LatLng(lat, lng);

                mMap.addMarker(new MarkerOptions().position(newLocation).title("plz"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation,18));
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

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void revokeAccess() {
        mAuth.getCurrentUser().delete();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu) ;

        return true ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                signOut();
                finishAffinity();
                break;
            case R.id.btn_revoke:
                revokeAccess();
                finishAffinity();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //or switch문을 이용하면 될듯 하다.

        if (id == R.id.menu_대여) {
            Intent settingIntent = new Intent(this, MainActivity3.class);
            startActivity(settingIntent);
        }

        if (id == R.id.menu_반납) {
            Intent settingIntent = new Intent(this, MainActivity2.class);
            startActivity(settingIntent);
        }

        if (id == R.id.menu_로그아웃) {
            signOut();
            finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }

}