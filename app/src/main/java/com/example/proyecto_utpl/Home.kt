package com.example.proyecto_utpl

import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_ficha_registro.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.tv_fecha
import kotlinx.android.synthetic.main.activity_home.tv_hora
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class Home : AppCompatActivity() {
    val funciones = Funciones_base()
    private val db = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btn_registrarHora.setOnClickListener {
            val ida = Intent(this, Ficha_registro::class.java)
            startActivity(ida)
        }

        btn_ubi.setOnClickListener {
            val saltaMapa = Intent(this, MapsActivity::class.java)
            startActivity(saltaMapa)
        }

        hora()
        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout1)
        val shapeDrawable = ShapeDrawable()
        shapeDrawable.shape = RoundRectShape(floatArrayOf(10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f), null, null)
        frameLayout.background = shapeDrawable

        val bundle:Bundle? = intent.extras
        val nombre:String? = bundle?.getString("nombre")
        val provider:String? = bundle?.getString("provider")
    }



    @RequiresApi(Build.VERSION_CODES.N)
    fun hora(){
        val current = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale.getDefault())
        val dateFormatt = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val dateStringg = dateFormatt.format(current)
        val dateFormatSymbols = dateFormat.dateFormatSymbols
        dateFormatSymbols.months = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
        dateFormat.dateFormatSymbols = dateFormatSymbols
        val dateString = dateFormat.format(current)
        tv_fecha.setText("$dateString")
        tv_hora.setText("$dateStringg")
    }

}
