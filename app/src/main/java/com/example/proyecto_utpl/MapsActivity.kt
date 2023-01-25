package com.example.proyecto_utpl

import android.R.layout.*
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.proyecto_utpl.databinding.ActivityMapsBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var zone: Circle

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val utpl = LatLng(-3.9870386238500495, -79.19863502335933)
        mMap.addMarker(MarkerOptions().position(utpl).title("UTPL Loja"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(utpl,15f))

        /*
        //ubicacion en circulo
        val radius = 70.0
        val strokeColor = Color.RED
        val fillColor = Color.argb(64, 255, 0, 0)

        zone = googleMap.addCircle(
            CircleOptions()
            .center(utpl)
            .radius(radius)
            .strokeColor(strokeColor)
            .fillColor(fillColor))

        */

        // Crear un objeto Polygon
        var zone: Polygon = googleMap.addPolygon(
            PolygonOptions()
                .add(
                    LatLng(-3.986312, -79.199742),
                    LatLng(-3.988364, -79.199107),
                    LatLng(-3.988488, -79.198313),
                    LatLng(-3.987578, -79.196934),
                    LatLng(-3.986502, -79.196703),
                    LatLng(-3.985379, -79.198066)
                )
                .strokeColor(Color.RED)
                .fillColor(Color.argb(64, 255, 0, 0))
        )


        val locationClient = LocationServices.getFusedLocationProviderClient(this)
        locationClient.lastLocation.addOnSuccessListener { location ->
            // Verificar si la ubicación actual está dentro de la zona
            val currentLocation = LatLng(location.latitude, location.longitude)
            val isInside = zone.contains(currentLocation)

            if (isInside) {
                // Está dentro de la zona


            } else {
                // No está dentro de la zona


            }
        }


        enableLocation() //Activacion de la Localizacion
    }


    /*

    //comprobar si está dentro de la universidad

    private fun checkIfInsideZone(location: LatLng): Boolean {
        return zone.contains(location)
    }
    */



    private fun isLocationPermissionGranted()= ContextCompat.checkSelfPermission(
        this, android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun enableLocation(){
        if (!::mMap.isInitialized)return
        if(isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }

    //Comprobacion de Permisos


    private fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this,"Ve a ajustes y Acepta los permisos", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty()&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
                mMap.isMyLocationEnabled = true
            }else{
                Toast.makeText(this,"Para activar la localizacion ve a ajustes", Toast.LENGTH_SHORT).show()
            }else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::mMap.isInitialized)return
        if(!isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = false
            Toast.makeText(this,"Para activar la localizacion ve a ajustes", Toast.LENGTH_SHORT).show()
        }
    }








}

private fun Polygon.contains(currentLocation: LatLng): Boolean {
return true
}
