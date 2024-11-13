package com.example.project_maicao_uniguajira.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_maicao_uniguajira.R

class logUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_up)

        // Botón "Siguiente"
        val buttonSiguiente: Button = findViewById(R.id.buttonSiguiente)

        // Configurar el OnClickListener para el botón Siguiente
        buttonSiguiente.setOnClickListener {
            val intent = Intent(this, logUp2Activity::class.java)
            startActivity(intent)
        }

        // Configurar el OnClickListener para el texto "Ya tengo una cuenta"
        val textViewLogin: TextView = findViewById(R.id.textViewLogin)
        textViewLogin.setOnClickListener {
            // Redirigir al Login (tercera pantalla)
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

        // Configurar el borde y los márgenes en los insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

