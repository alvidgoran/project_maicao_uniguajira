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
import kotlinx.coroutines.*
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class logUp2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_up2)


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
                    registerUser(nombre, apellido, correo, password)

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

    // Función para registrar al usuario y enviar los datos a la API de PHP
    private fun registerUser(nombre: String, apellido: String, correo: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("http://10.0.2.2/bd_social_media_api/register.php") // Cambia con la URL de tu API
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doOutput = true

                // Crea los datos a enviar en el cuerpo del POST
                val postData = "nombre=$nombre&apellido=$apellido&correo=$correo&password=$password"

                val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
                outputStreamWriter.write(postData)
                outputStreamWriter.flush()

                val responseCode = httpURLConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@logUp2Activity, "Registro2 exitoso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@logUp2Activity, dashboardActivity::class.java))
                        finish()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@logUp2Activity, "Error en el registro", Toast.LENGTH_SHORT).show()
                    }
                }
                httpURLConnection.disconnect()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@logUp2Activity, "Error en la conexión: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
