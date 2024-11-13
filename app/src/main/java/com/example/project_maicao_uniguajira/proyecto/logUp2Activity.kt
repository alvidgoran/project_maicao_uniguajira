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
import com.example.project_maicao_uniguajira.proyecto.loginActivity
import com.example.project_maicao_uniguajira.proyecto.dashboardActivity

class logUp2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_up2)

        // Configura el comportamiento de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura el texto "Ya tienes una cuenta? Iniciar sesión"
        val textViewBackToLogin: TextView = findViewById(R.id.textViewBackToLogin)
        textViewBackToLogin.setOnClickListener {
            // Redirige al loginActivity
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

        // Configura el botón de "Registrar"
        val buttonRegister: Button = findViewById(R.id.buttonRegister)
        buttonRegister.setOnClickListener {
            // Obtiene los valores de los campos de texto
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword).text.toString()
            val termsAccepted = findViewById<CheckBox>(R.id.checkBoxTerms).isChecked

            // Validación básica
            if (password == confirmPassword) {
                if (termsAccepted) {
                    // Si las contraseñas coinciden y los términos están aceptados
                    registerUser()

                    // Redirige al Dashboard
                    val intent = Intent(this, dashboardActivity::class.java)
                    startActivity(intent)
                    finish()  // Para que el usuario no pueda volver a esta pantalla al presionar el botón de retroceso
                } else {
                    // Si no se aceptan los términos
                    Toast.makeText(this, "Por favor, acepte los términos y condiciones", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Si las contraseñas no coinciden
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para registrar al usuario
    private fun registerUser() {
        // Aquí iría la lógica para guardar el usuario en la base de datos
        // Ejemplo básico de mensaje de éxito
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
    }
}
