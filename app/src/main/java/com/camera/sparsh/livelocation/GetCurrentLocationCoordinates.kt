package com.camera.sparsh.livelocation

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat


/**
 * Created by Rahul Sharma on 03-02-2021.
 */
class GetCurrentLocationCoordinates {

    private val mContext: Context? = null
    var checkGPS = false
    var checkNetwork = false
    var canGetLocation = false
    var loc: Location? = null
    var latitude = 0.0
    var longitude = 0.0

    private val MIN_DSTANCE_CHANGE_FOR_UPDATES: Float = 10f

    private val MIN_TIME_BW_UPDATES = 1000 * 60 * 1.toLong()
    protected var locationManager: LocationManager? = null

    public fun getLocation(): Location? {
        try {
            locationManager = mContext!!
                .getSystemService(LOCATION_SERVICE) as LocationManager

            // get GPS status
            checkGPS = locationManager!!
                .isProviderEnabled(LocationManager.GPS_PROVIDER)

            // get network provider status
            checkNetwork = locationManager!!
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!checkGPS && !checkNetwork) {
                Toast.makeText(mContext, "No Service Provider is available", Toast.LENGTH_SHORT)
                    .show()
            } else {
                canGetLocation = true

                // if GPS Enabled get lat/long using GPS Services
                if (checkGPS) {
                    if (ActivityCompat.checkSelfPermission(
                            mContext!!,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            mContext, Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                    }
                    locationManager!!.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DSTANCE_CHANGE_FOR_UPDATES, object : LocationListener {
                            override fun onLocationChanged(location: Location?) {
                                try {
                                    latitude = location!!.latitude
                                    longitude = location!!.longitude
                                } catch (e: java.lang.Exception) {
                                    latitude = 00.00
                                    longitude = 00.00
                                }
                            }

                            override fun onStatusChanged(
                                provider: String?,
                                status: Int,
                                extras: Bundle?
                            ) {

                            }

                            override fun onProviderEnabled(provider: String?) {

                            }

                            override fun onProviderDisabled(provider: String?) {

                            }
                        }
                    )

                    if (locationManager != null) {
                        loc = locationManager!!
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        if (loc != null) {
                            latitude = loc!!.latitude
                            longitude = loc!!.longitude
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return loc
    }

}