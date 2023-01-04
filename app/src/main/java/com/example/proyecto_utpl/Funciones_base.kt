package com.example.proyecto_utpl

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_asistencia.*

class Funciones_base: AppCompatActivity(){
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

    fun obtenerUser(){
        var documento = et_email.text.toString()

        db.collection("usuarios").document(documento).get().addOnSuccessListener { salida->
            if (salida.exists()){
                val nombres:String? = salida.getString("nombre")
                val apellido:String?= salida.getString("apellido")

                Log.d("", "$nombres $apellido")
            }else{
                print("DATOS NO ENCONTRADOS")
            }
        }
    }
}