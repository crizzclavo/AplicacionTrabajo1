package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.myapplication.User
import com.example.myapplication.UserDao


class LoginActivity : AppCompatActivity() {
    private lateinit var userDao: UserDao
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var checkBoxRemember: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userDao = AppDatabase.getInstance(application).userDao()
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        checkBoxRemember = findViewById(R.id.checkBoxRemember)
        val buttonLogin: Button = findViewById(R.id.buttonLogin)

        // Verificar si el usuario está marcado como recordado y cargar los datos si es necesario.
        val sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val isChecked = sharedPreferences.getBoolean("isChecked", false)
        val buttonRegister: Button = findViewById(R.id.buttonRegister)


        if (isChecked) {
            editTextUsername.setText(savedUsername)
            editTextPassword.setText(savedPassword)
            checkBoxRemember.isChecked = true
        }
        buttonRegister.setOnClickListener {
            // Abre la actividad de registro cuando se hace clic en el botón de registro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()
            val rememberUser = checkBoxRemember.isChecked

            GlobalScope.launch(Dispatchers.IO) {
                val user = userDao.getUserByUsername(username)

                // Verificar si el usuario existe y la contraseña es correcta
                if (user != null && user.password== password) {
                    runOnUiThread {
                        // Login exitoso
                        Toast.makeText(this@LoginActivity, "Login exitoso, bienvenido "+ user.username, Toast.LENGTH_SHORT)
                            .show()

                        // Guardar credenciales si el usuario marcó la casilla de recordar usuario
                        if (rememberUser) {
                            val editor = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit()
                            editor.putString("username", username)
                            editor.putString("password", password)
                            editor.putBoolean("isChecked", true)
                            editor.apply()
                        }

                        // Redirigir a la siguiente actividad
                        val intent = Intent(this@LoginActivity, MostrarLetras::class.java)
                        startActivity(intent)
                        finish() // Cierra la actividad actual para que el usuario no pueda volver atrás.
                    }
                } else {
                    runOnUiThread {
                        // Credenciales incorrectas, muestra un mensaje de error.
                        Toast.makeText(
                            this@LoginActivity,
                            "Credenciales incorrectas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}