package com.example.proyecto_utpl

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class MainActivity : AppCompatActivity() {
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

                // Inicia un temporizador para mostrar la pantalla de inicio durante 2 segundos
                val timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        // Cuando el temporizador finalice, inicia la siguiente actividad
                        startActivity(Intent(this@MainActivity, tutorial::class.java))
                        finish()
                    }
                }, 2000)
            }



    }
