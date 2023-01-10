package com.example.proyecto_utpl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.activity_tutorial.*
import java.util.*

class tutorial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                // Cuando el temporizador finalice, inicia la siguiente actividad
                startActivity(Intent(this@tutorial, principal::class.java))
                finish()
            }
        },7000)
        btn_ini.setOnClickListener {
            val viajar: Intent = Intent(this, principal::class.java)
            startActivity(viajar)
        }




    }
}