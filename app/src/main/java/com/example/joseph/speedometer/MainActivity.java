package com.example.joseph.speedometer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class  MainActivity extends AppCompatActivity implements LocationListener {


    TextView txt;
    TextView txt1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = this.findViewById(R.id.textView);
        txt1 = this.findViewById(R.id.textView1);
        Typeface digital  = Typeface.createFromAsset(getAssets(), "fonts/Digital.ttf");
        txt.setTypeface(digital);
        txt1.setTypeface(digital);


        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        LocationManager lm =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.onLocationChanged(null);

    }


    @Override
    public void onLocationChanged(Location location)  {

       if (location==null){
            // if you can't get speed because reasons :)
            txt.setText("000");
        }
       else{
            //int speed=(int) ((location.getSpeed()) is the standard which returns meters per second. In this example i converted it to kilometers per hour
         int speed=(int) ((location.getSpeed()*3600)/1000);

            //txt.setText(speed+ " Km/m");
           txt.setText( String.format("%03d", speed) );
       }


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

