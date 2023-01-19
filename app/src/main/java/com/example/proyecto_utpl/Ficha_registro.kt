package com.example.proyecto_utpl

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.proyecto_utpl.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_ficha_registro.*

class Ficha_registro : AppCompatActivity() {

    private val CAMARA_REQUEST_CODE: Int = 23
    private val PERMISO_CAMERA:Int = 99

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_ficha_registro)
        setContentView(binding.root)
        with(binding){
            btn_foto.setOnClickListener {
                solicitarPermisos()
            }
        }
        binding.imageView.setImageResource(R.drawable.back_logo)
    }

    private fun solicitarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED -> {
                    tomarFoto()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    mostrarMensaje("El permiso fue rechazado previamente, ve a configuraciones")
                }
                else -> {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISO_CAMERA)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISO_CAMERA->{
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    tomarFoto()
                }
            }else->{

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



    private fun tomarFoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMARA_REQUEST_CODE)
    }

    fun mostrarMensaje(mensaje:String){
        Toast.makeText(applicationContext,mensaje,Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CAMARA_REQUEST_CODE -> {
                if(resultCode != Activity.RESULT_OK){
                    mostrarMensaje("No se pudo tomar la foto")
                }
                else{
                    val bitmap = data?.extras?.get("data") as Bitmap
                    with(binding){
                        imgFoto.setImageBitmap(bitmap)
                        }
                    }
                }
            }
        }
    }
