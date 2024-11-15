package com.example.project_maicao_uniguajira.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_maicao_uniguajira.R
import android.widget.ImageButton
import android.widget.Button

class menuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        // Ajusta los márgenes y el padding para los bordes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura el botón de retroceso
        findViewById<ImageButton>(R.id.btnBackMenu).setOnClickListener {
            onBackPressed()  // Esto hará que el usuario regrese a la pantalla anterior
        }

        // Configura el botón para el menú de opciones
        findViewById<ImageButton>(R.id.btnMenuOptions).setOnClickListener {
            // Aquí puedes agregar alguna acción si quieres que el ícono del menú haga algo.
        }

        // Configura las opciones del menú
        findViewById<Button>(R.id.btnOption1).setOnClickListener {
            // Cerrar sesión: Esto puede ser implementado dependiendo de tu lógica.
            cerrarSesion()
        }

        findViewById<Button>(R.id.btnOption2).setOnClickListener {
            // Navegar al perfil
          // val intent = Intent(this, PerfilActivity::class.java)
          //  startActivity(intent)
        }

        findViewById<Button>(R.id.btnOption3).setOnClickListener {
            // Navegar a la configuración
          //  val intent = Intent(this, ConfiguracionActivity::class.java)
         //   startActivity(intent)
        }
    }

    private fun cerrarSesion() {

        // - Limpiar datos de sesión.
        // - Redirigir al login, etc.
        val intent = Intent(this, loginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
