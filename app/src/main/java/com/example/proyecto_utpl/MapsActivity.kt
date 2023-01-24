package com.example.proyecto_utpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.layout.*
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.proyecto_utpl.databinding.ActivityMapsBinding
import java.util.jar.Manifest


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(utpl))
        enableLocation() //Activacion de la Localizacion
    }

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