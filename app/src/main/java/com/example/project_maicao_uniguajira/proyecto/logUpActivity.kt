package com.example.project_maicao_uniguajira.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_maicao_uniguajira.R
import com.google.firebase.FirebaseApp

class logUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_up)

        FirebaseApp.initializeApp(this)

        // Capturar los campos de texto
        val editTextNombre: EditText = findViewById(R.id.editTextNombre)
        val editTextApellido: EditText = findViewById(R.id.editTextApellido)
        val editTextCorreo: EditText = findViewById(R.id.editTextCorreo)

        // Botón "Siguiente"
        val buttonSiguiente: Button = findViewById(R.id.buttonSiguiente)

        // Configurar el OnClickListener para el botón Siguiente
        buttonSiguiente.setOnClickListener {
            // Obtener los valores ingresados por el usuario
            val nombre = editTextNombre.text.toString().trim()
            val apellido = editTextApellido.text.toString().trim()
            val correo = editTextCorreo.text.toString().trim()

            // Validar que los campos no estén vacíos
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear el Intent y pasar los datos a la siguiente actividad
            val intent = Intent(this, logUp2Activity::class.java).apply {
                putExtra("nombre", nombre)
                putExtra("apellido", apellido)
                putExtra("correo", correo)
            }
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


