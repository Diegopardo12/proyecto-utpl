package com.example.proyecto_utpl


import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.proyecto_utpl.databinding.ActivityFichaRegistroBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_ficha_registro.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.io.File
import java.util.*

class Ficha_registro : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
        private lateinit var binding:ActivityFichaRegistroBinding
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityFichaRegistroBinding.inflate(layoutInflater)
            setContentView(binding.root)
//            setContentView(R.layout.activity_ficha_registro)
            binding.btnFoto.setOnClickListener{
//                openCamera.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{
                    it.resolveActivity(packageManager).also { component ->
                        createPhotoFile()
                        val photoUri: Uri =
                            FileProvider.getUriForFile(
                                this,
                                BuildConfig.APPLICATION_ID+".fileprovider",file)
                        it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    }
                }
                openCamera.launch(intent)
            }
            hora()

//            var email = tv_email.text
//            var fecha = tv_fecha.
//            btn_registrarFicha.setOnClickListener {
//                db.collection(email as String).document(tv_fecha.toString()).set(
//                    hashMapOf(
//                            "fecha" to tv_fecha,
//                            "hora" to tv_hora,
//                            "evidencia" to imgFoto,
//                    )
//                )
//                val regreso = Intent(this, Home::class.java)
//                startActivity(regreso)
//            }



        }


    val openCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
//                val data = result.data!!
//                val bitmap = data.extras!!.get("data") as Bitmap
                val bitmap = BitmapFactory.decodeFile(file.toString())
                binding.imgFoto.setImageBitmap(bitmap)
            }
        }
    private lateinit var file:File
    private fun createPhotoFile():File {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", dir)
        return file;
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun hora(){
            val c: Calendar = Calendar.getInstance()
            val dia:Int=c.get(Calendar.DAY_OF_WEEK)
            val zona:Int=c.get(Calendar.NARROW_FORMAT)
            val mes:Int=c.get(Calendar.MONTH)
            val year:Int=c.get(Calendar.YEAR)
            val hora:Int=c.get(Calendar.HOUR)
            val minutos:Int=c.get(Calendar.MINUTE)
        /* tv_hora.setText("$hora:$minutos ")
            tv_fecha.setText("$year-$mes-$dia")*/
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

