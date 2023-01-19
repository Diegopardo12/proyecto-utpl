package com.example.proyecto_utpl

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_asistencia.*

class asistencia : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia)
        setup()
    }
    private fun setup() {
        btn_ingresar.setOnClickListener {
            if(et_email.text.isNotEmpty() && et_clave.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    et_email.text.toString(),
                    et_clave.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful){
                            showHome(it.result?.user?.email?:"", ProviderType.Personal)
                        }else{
                            showAlert()
                        }
                }
            }
        }

        btn_acceder.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Bienvenido")
            builder.setPositiveButton("OK") { _, _ ->
                if(et_email.text.isNotEmpty() && et_clave.text.isNotEmpty()){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        et_email.text.toString(),
                        et_clave.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful){
                            showHome2(it.result?.user?.email?:"", ProviderType.Personal)
                        }else{
                            showAlert()
                        }
                    }
                }
            }
            val dialog = builder.create()
            dialog.show()


        }
        /*fun showImageDialog(context: Context, image: Drawable) {
            val dialog = Dialog(context)
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.image_dialog, null)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            imageView.setImageDrawable(image)
            dialog.setContentView(view)
            dialog.show()
        }*/

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Por favor registrese antes de continuar ")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showHome(email:String, provider: ProviderType){
        val homeIntent: Intent = Intent(this,Registro::class.java).apply {
            putExtra("email",email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    private fun showHome2(email:String, provider: ProviderType){
        val homeIntent: Intent = Intent(this,Home::class.java).apply {
            putExtra("email",email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}