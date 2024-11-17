package com.example.project_maicao_uniguajira.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_maicao_uniguajira.R
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class logUp2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_up2)

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        // Configura el comportamiento de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recuperar los datos enviados desde logUpActivity
        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val correo = intent.getStringExtra("correo") ?: ""

        // Configura el texto "Ya tienes una cuenta? Iniciar sesión"
        val textViewBackToLogin: TextView = findViewById(R.id.textViewBackToLogin)
        textViewBackToLogin.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

        // Configura el botón de "Registrar"
        val buttonRegister: Button = findViewById(R.id.buttonRegister)
        buttonRegister.setOnClickListener {
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword).text.toString()
            val termsAccepted = findViewById<CheckBox>(R.id.checkBoxTerms).isChecked

            if (password == confirmPassword) {
                if (termsAccepted) {
                    // Registrar usuario en Firebase
                    registerUserFirebase(nombre, apellido, correo, password)

                    // Redirigir al Dashboard
                    val intent = Intent(this, dashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Por favor, acepte los términos y condiciones", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Registrar usuario en Firebase
    private fun registerUserFirebase(nombre: String, apellido: String, correo: String, password: String) {
        val database = FirebaseDatabase.getInstance("https://red-social-8b6db-default-rtdb.firebaseio.com/")
        val usersRef = database.getReference("users")

        val userId = usersRef.push().key // Generar un ID único para el usuario
        if (userId != null) {
            val user = mapOf(
                "id" to userId,
                "nombre" to nombre,
                "apellido" to apellido,
                "correo" to correo,
                "password" to password
            )
            usersRef.child(userId).setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al registrar usuario: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}
