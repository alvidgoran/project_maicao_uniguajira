package com.example.project_maicao_uniguajira.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_maicao_uniguajira.R

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Configurar el Spinner
        // Dentro de onCreate de loginActivity.kt
        val textViewNoAccount: TextView = findViewById(R.id.textViewNoAccount)
        textViewNoAccount.setOnClickListener {
            // Redirige a la primera pantalla de registro
            val intent = Intent(this, logUpActivity::class.java)
            startActivity(intent)
        }

        val iniciarsesion: TextView = findViewById(R.id.buttonLogin)
        textViewNoAccount.setOnClickListener {
            // Redirige a la pantalla de usuario
            val intent = Intent(this, dashboardActivity::class.java)
            startActivity(intent)
        }

    }
}