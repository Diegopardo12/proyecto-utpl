package com.example.proyecto_utpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class Home : AppCompatActivity() {

    val funciones = Funciones_base()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        hora()
    }

    fun hora(){
        val c: Calendar = Calendar.getInstance()
        val dia:Int=c.get(Calendar.DAY_OF_WEEK)
        val mes:Int=c.get(Calendar.MONTH)
        val year:Int=c.get(Calendar.YEAR)
        val hora:Int=c.get(Calendar.HOUR)
        val minutos:Int=c.get(Calendar.MINUTE)
        tv_hora.setText("$year-$mes-$dia | $hora:$minutos ")
    }
}
