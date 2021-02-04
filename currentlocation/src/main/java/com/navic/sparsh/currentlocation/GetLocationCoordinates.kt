package com.navic.sparsh.currentlocation

import android.Manifest
import android.Manifest.permission_group.LOCATION
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.functions.Consumer

/**
 * Created by Rahul Sharma on 04-02-2021.
 */
open class GetLocationCoordinates {
    companion object {
        var mLocationRequest = LocationRequest.create()
        lateinit var locationMutableLiveData: MutableLiveData<Location>

        private var rxPermissions: RxPermissions? = null
        private val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        fun getNewLocation(context: Context, activity: Activity) {
            locationMutableLiveData = MutableLiveData()
            mLocationRequest.interval = 2000
            mLocationRequest.fastestInterval = 1000
            mLocationRequest.smallestDisplacement = 0f

            rxPermissions = RxPermissions(activity)
            rxPermissions!!.request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION

            )
                .subscribe(Consumer { granted: Boolean ->
                    if (granted) {

                        /*Got all the permissions here*/
                        getLocation(context)


                    } else {
                        for (permission in permissions) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(
                                    activity,
                                    permission
                                )
                            ) {
                                AlertDialog.Builder(context)
                                    .setTitle("Error")
                                    .setMessage("We need all the permissions")
                                    .setPositiveButton(
                                        "Allow"
                                    ) { dialog: DialogInterface?, which: Int ->
                                        getNewLocation(
                                            context,
                                            activity
                                        )
                                    }
                                    .setNegativeButton(
                                        "Cancel"
                                    ) { dialog: DialogInterface?, which: Int -> false }
                                    .create()
                                    .show()
                            }
                        }
                    }
                })
        }

        private fun getLocation(context: Context) {
            val mLocationCallback: LocationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    if (locationResult == null) {
                        Toast.makeText(
                            context,
                            "Null Location",
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                    for (location in locationResult.locations) {
                        /*if (location != null) {

                        }*/
                        locationMutableLiveData.postValue(location)
                    }
                }
            }

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                LocationServices.getFusedLocationProviderClient(context)
                    .requestLocationUpdates(mLocationRequest, mLocationCallback, null)
            }


        }
    }

}