package com.example.proyecto_utpl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.activity_asistencia.*

enum class ProviderType{
    Personal,
}

class Registro : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")
        val provider:String? = bundle?.getString("provider")

        setup(email?:"", provider?:"")

    }
    private fun setup(email: String, provider:String){

        tv_email.text= email
        tv_provider.text= provider

        btn_registro.setOnClickListener(){

            db.collection("usuarios").document(email).set(
                hashMapOf(
                    "provider" to provider,
                    "nombre" to et_nombre.text.toString(),
                    "apellido" to et_lastname.text.toString(),
                    "departamento" to sp_dpto.selectedItem.toString(),
                )
            )

            val salto: Intent = Intent(this,Home::class.java)
            startActivity(salto)

        }

    }

}