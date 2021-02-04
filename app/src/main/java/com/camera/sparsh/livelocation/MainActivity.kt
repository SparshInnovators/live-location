package com.camera.sparsh.livelocation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {
    private var latitude: String? = null
    private var longitude: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


   /*     if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }*/

//        getNewLocation()

        (findViewById<Button>(R.id.btnGetLocation) as (Button)).setOnClickListener(View.OnClickListener {
            (findViewById<Button>(R.id.btnGetLocation) as (TextView)).text =
                (latitude + ":" + longitude)
            Toast.makeText(
                applicationContext,
                (latitude + ":" + longitude),
                Toast.LENGTH_LONG
            ).show()
        })
    }

    /*Below code is working well*/
  /*  private fun getNewLocation() {
        val mLocationRequest = LocationRequest.create()
        mLocationRequest.interval = 2000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.smallestDisplacement = 0f
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        val mLocationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult == null) {
                    Toast.makeText(
                        applicationContext,
                        "Null Location",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                for (location in locationResult.locations) {
                    if (location != null) {
                        latitude = location.latitude.toString()
                        longitude = location.longitude.toString()
                    }
                }
            }
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }

        LocationServices.getFusedLocationProviderClient(applicationContext)
            .requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }*/
}