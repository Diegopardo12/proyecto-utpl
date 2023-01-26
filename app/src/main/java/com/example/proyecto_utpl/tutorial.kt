package com.example.proyecto_utpl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.activity_tutorial.*
import java.util.*

class tutorial : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        btn_ini.setOnClickListener {
            goToMainActivity()
        }
        btn_tuto.setOnClickListener {
            goToMainActivity1()
        }

        val timer = Timer()
        val splashDuration = 7000L // in milliseconds
        /*timer.schedule(object : TimerTask() {
            override fun run() {
                // Cuando el temporizador finalice, inicia la siguiente actividad
                startActivity(Intent(this@tutorial, principal::class.java) )
                finish()
            }
        },7000)*/
        countDownTimer = object : CountDownTimer(splashDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // do nothing, just wait
            }

            override fun onFinish() {
                goToMainActivity()
                goToMainActivity1()
            }
        }
        countDownTimer.start()

    }
    private fun goToMainActivity() {
        countDownTimer.cancel()
        val intent = Intent(this, principal::class.java)
        startActivity(intent)
        finish()
    }
    private fun goToMainActivity1() {
        countDownTimer.cancel()
        val intent = Intent(this, turotial_fin::class.java)
        startActivity(intent)
        finish()
    }
}