package com.example.project_maicao_uniguajira.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project_maicao_uniguajira.R
import com.google.firebase.database.*

class loginActivity : AppCompatActivity() {

    // Instancia de Firebase Database
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar la referencia a Firebase Realtime Database
        database = FirebaseDatabase.getInstance().reference

        // Configurar el texto "No tengo una cuenta"
        val textViewNoAccount: TextView = findViewById(R.id.textViewNoAccount)
        textViewNoAccount.setOnClickListener {
            // Redirige a la primera pantalla de registro
            val intent = Intent(this, logUpActivity::class.java)
            startActivity(intent)
        }

        // Botón "Iniciar Sesión"
        val buttonLogin: Button = findViewById(R.id.buttonLogin)

        // Campos de correo y contraseña
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)

        // Configurar el OnClickListener para iniciar sesión
        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            // Validación básica de campos vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Llamar a la función de autenticación
                authenticateUser(email, password)
            }
        }
    }

    // Función para autenticar al usuario en Firebase
    private fun authenticateUser(email: String, password: String) {
        database.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var isAuthenticated = false

                for (userSnapshot in snapshot.children) {
                    val userEmail = userSnapshot.child("correo").value.toString()
                    val userPassword = userSnapshot.child("password").value.toString()

                    if (userEmail == email && userPassword == password) {
                        isAuthenticated = true
                        break
                    }
                }

                if (isAuthenticated) {
                    Toast.makeText(this@loginActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Redirigir al Dashboard
                    startActivity(Intent(this@loginActivity, dashboardActivity::class.java))
                    finish() // Finalizar la actividad para que no se pueda regresar
                } else {
                    Toast.makeText(this@loginActivity, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@loginActivity, "Error al autenticar: ${error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
