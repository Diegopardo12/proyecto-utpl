package com.example.proyecto_utpl

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class Home : AppCompatActivity() {
    val funciones = Funciones_base()
    private val db = FirebaseFirestore.getInstance()
    lateinit var toogle: ActionBarDrawerToggle
    private lateinit var textView: TextView




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


        textView = findViewById(R.id.tv_ubi)
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            // Obtener la última ubicación conocida del dispositivo
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            // Actualizar el textView con la ubicación
            textView.text = "Latitud: ${location?.latitude}\nLongitud: ${location?.longitude}"
        } catch (e: SecurityException) {
            // Handle security exception
        }
        /*myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val nombre = dataSnapshot.getValue(String::class.java)
                textView.text = nombre
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })*/

    }



    fun hora(){
        val c: Calendar = Calendar.getInstance()
        val dia:Int=c.get(Calendar.DAY_OF_WEEK)
        val zona:Int=c.get(Calendar.NARROW_FORMAT)
        val mes:Int=c.get(Calendar.MONTH)
        val year:Int=c.get(Calendar.YEAR)
        val hora:Int=c.get(Calendar.HOUR)
        val minutos:Int=c.get(Calendar.MINUTE)
        tv_hora.setText("$hora:$minutos ")
        tv_fecha.setText("$year-$mes-$dia")
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
