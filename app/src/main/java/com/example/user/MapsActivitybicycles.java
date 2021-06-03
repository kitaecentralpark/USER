package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MapsActivitybicycles extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Marker bike0;
    private ChildEventListener mChildEventListener0;
    private DatabaseReference databaseReference0;

    private Marker bike1;
    private ChildEventListener mChildEventListener1;
    private DatabaseReference databaseReference1;

    private Marker bike2;
    private ChildEventListener mChildEventListener2;
    private DatabaseReference databaseReference2;

    private Marker bike3;
    private ChildEventListener mChildEventListener3;
    private DatabaseReference databaseReference3;

    private Marker bike4;
    private ChildEventListener mChildEventListener4;
    private DatabaseReference databaseReference4;

    private Marker bike5;
    private ChildEventListener mChildEventListener5;
    private DatabaseReference databaseReference5;

    private GoogleMap.OnMarkerClickListener markerClickListener;

    private DatabaseReference state0;
    private FirebaseDatabase mDatabase;
    private LatLng newLocation;
    private LatLng firstLocation;
    private bicycleId GpsN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activitybicycles);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

        firstLocation = new LatLng(0.0,0.0);
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference0 = mDatabase.getReference().child("Gps0/gps");
        databaseReference1 = mDatabase.getReference().child("Gps1/gps");
        databaseReference2 = mDatabase.getReference().child("Gps2/gps");
        databaseReference3 = mDatabase.getReference().child("Gps3/gps");
        databaseReference4 = mDatabase.getReference().child("Gps4/gps");
        databaseReference5 = mDatabase.getReference().child("Gps5/gps");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        bike0 = mMap.addMarker(new MarkerOptions().position(firstLocation).title("Gps0"));
        bike1 = mMap.addMarker(new MarkerOptions().position(firstLocation).title("Gps1"));
        bike2 = mMap.addMarker(new MarkerOptions().position(firstLocation).title("Gps2"));
        bike3 = mMap.addMarker(new MarkerOptions().position(firstLocation).title("Gps3"));
        bike4 = mMap.addMarker(new MarkerOptions().position(firstLocation).title("Gps4"));
        bike5 = mMap.addMarker(new MarkerOptions().position(firstLocation).title("Gps5"));

        mChildEventListener0 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String lat = dataSnapshot.child("latitude").getValue(String.class);
                String lng = dataSnapshot.child("longitude").getValue(String.class);
                double Lat = Double.parseDouble(lat);
                double Lng = Double.parseDouble(lng);

                newLocation = new LatLng(Lat, Lng);

                bike0.setPosition(newLocation);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 14));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String key) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mChildEventListener1 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String lat = dataSnapshot.child("latitude").getValue(String.class);
                String lng = dataSnapshot.child("longitude").getValue(String.class);
                double Lat = Double.parseDouble(lat);
                double Lng = Double.parseDouble(lng);

                newLocation = new LatLng(Lat, Lng);
                bike1.setPosition(newLocation);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 7));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String key) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mChildEventListener2 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String lat = dataSnapshot.child("latitude").getValue(String.class);
                String lng = dataSnapshot.child("longitude").getValue(String.class);
                double Lat = Double.parseDouble(lat);
                double Lng = Double.parseDouble(lng);

                newLocation = new LatLng(Lat, Lng);
                bike2.setPosition(newLocation);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 7));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String key) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mChildEventListener3 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String lat = dataSnapshot.child("latitude").getValue(String.class);
                String lng = dataSnapshot.child("longitude").getValue(String.class);
                double Lat = Double.parseDouble(lat);
                double Lng = Double.parseDouble(lng);

                newLocation = new LatLng(Lat, Lng);
                bike3.setPosition(newLocation);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 7));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String key) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mChildEventListener4 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String lat = dataSnapshot.child("latitude").getValue(String.class);
                String lng = dataSnapshot.child("longitude").getValue(String.class);
                double Lat = Double.parseDouble(lat);
                double Lng = Double.parseDouble(lng);

                newLocation = new LatLng(Lat, Lng);
                bike4.setPosition(newLocation);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 7));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String key) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mChildEventListener5 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String lat = dataSnapshot.child("latitude").getValue(String.class);
                String lng = dataSnapshot.child("longitude").getValue(String.class);
                double Lat = Double.parseDouble(lat);
                double Lng = Double.parseDouble(lng);

                newLocation = new LatLng(Lat, Lng);
                bike5.setPosition(newLocation);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 7));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String key) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        markerClickListener = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markerId = marker.getTitle();
                GpsN = new bicycleId();
                GpsN.setBicyclename(markerId);
                return false;
            }
        };

        databaseReference0.addChildEventListener(mChildEventListener0);
        databaseReference1.addChildEventListener(mChildEventListener1);
        databaseReference2.addChildEventListener(mChildEventListener2);
        databaseReference3.addChildEventListener(mChildEventListener3);
        databaseReference4.addChildEventListener(mChildEventListener4);
        databaseReference5.addChildEventListener(mChildEventListener5);

        mMap.setOnMarkerClickListener(markerClickListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions2, menu) ;

        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_대여) {

            String Gps = GpsN.getbicyclename();
            Intent settingIntent = new Intent(this, RentalActivity.class);
            settingIntent.putExtra("name", Gps);
            startActivity(settingIntent);
            state0 = mDatabase.getReference(Gps+"/state");
            state0.setValue("1");

        }

        return super.onOptionsItemSelected(item);
    }

}