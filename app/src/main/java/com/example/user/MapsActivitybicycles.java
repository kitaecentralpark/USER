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

import java.util.Timer;
import java.util.TimerTask;


public class MapsActivitybicycles extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnRevoke, btnLogout;
    private FirebaseAuth mAuth ;
    private ChildEventListener mChildEventListener;
    private String latitude;
    private String longitude;
    private String bicyclename;
    private String Num;
    private int Total;
    private Marker[] bike;

    DatabaseReference databaseReference;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activitybicycles);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getInstance().getReference().child("Gps0/gps");
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

                //String total = dataSnapshot.child("total").getValue(String.class);
                //Total = Integer.parseInt(total);

              //  for (int i = 0; i < Total; i++) { // 총 갯수를 받기에 성공했다면 10대신 Total 변수를 사용할 것

              //      Num = Integer.toString(i);
                bicyclename = "Gps0"; //+ Num;  //gps는 가장 최근의 값만 1초에 한번씩 받아줌

                String lat = dataSnapshot.child("latitude").getValue(String.class);
                String lng = dataSnapshot.child("longitude").getValue(String.class);
                double Lat = Double.parseDouble(lat);
                double Lng = Double.parseDouble(lng);

                LatLng newLocation = new LatLng(Lat, Lng);

                mMap.addMarker(new MarkerOptions().position(newLocation).title("Gps0"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 18));

           //     }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
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
        });


       // GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
         //   @Override
           // public boolean onMarkerClick(Marker marker) {
         //       String markerId = marker.getId();    //파이어베이스 내의 bicyclename 의 값을 GpsId 객체에 저장해준다.
         //       GpsId name = new GpsId();               //이는 MapsActivity에서 지정된 자전거의 위치만을 참조하기 위한 발판이다.
         //       name.setbicyclename(markerId);
         //       return false;
      //      }
       // };
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
            Intent settingIntent = new Intent(this, RentalActivity.class);
            startActivity(settingIntent);

            //GpsId name = new GpsId();
            //String na = name.getbicyclename();

            databaseReference = mDatabase.getInstance().getReference().child("Gps0");
            DatabaseReference state = mDatabase.getReference("/state");

            state.setValue("1");

        }

        return super.onOptionsItemSelected(item);
    }

}