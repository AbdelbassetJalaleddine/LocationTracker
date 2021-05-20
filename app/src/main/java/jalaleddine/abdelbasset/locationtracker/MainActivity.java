package jalaleddine.abdelbasset.locationtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fr.quentinklein.slt.LocationTracker;
import fr.quentinklein.slt.TrackerSettings;


public class MainActivity extends AppCompatActivity {

    double longy;
    double laty;
    TextView textview_location;
    LocationTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview_location = findViewById(R.id.textview_location);
        TrackerSettings settings =
                new TrackerSettings()
                        .setUseGPS(true)
                        .setUseNetwork(true)
                        .setUsePassive(true)
                        .setMetersBetweenUpdates(3);
        tracker = new LocationTracker(this, settings) {

            @Override
            public void onLocationFound(@NonNull @org.jetbrains.annotations.NotNull Location location) {
                longy = location.getLongitude();
                laty = location.getLatitude();
                textview_location.setText("longy " + longy + "\nlaty " + laty);
                System.out.println("longy " + longy + "\nlaty " + laty);
                Toast.makeText(MainActivity.this, "Got Location", Toast.LENGTH_SHORT).show();
                tracker.stopListening();
            }

            @Override
            public void onTimeout() {

            }
        };
    }

    public void getloc(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "add permission wla :p", Toast.LENGTH_SHORT).show();
            return;
        }
        tracker.startListening();

    }
}