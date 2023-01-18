package com.example.proyecto_utpl

import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class Home : AppCompatActivity() {
    val funciones = Funciones_base()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        btn_ubi.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
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

}
