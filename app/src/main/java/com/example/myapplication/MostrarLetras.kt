package com.example.myapplication


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MostrarLetras : AppCompatActivity() {

    private lateinit var editTextArtistId: EditText
    private lateinit var buttonSearch: Button
    private lateinit var textViewArtistInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_letras)

        editTextArtistId = findViewById(R.id.editTextArtistId)
        buttonSearch = findViewById(R.id.buttonSearch)
        textViewArtistInfo = findViewById(R.id.textViewArtistInfo)

        buttonSearch.setOnClickListener {
            val artistId = editTextArtistId.text.toString().toIntOrNull()

            if (artistId != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    val accessToken = "0yW9JyawFKHpO5Z-yzRMk4ICl8dxnfKHwvNiQY0EHQwYkYjwPEPzFxJ9fvdM6JF_" // Reemplaza esto con tu token de acceso real

                    val response = ApiService.geniusApiService.getArtistInfo("Bearer $accessToken", artistId)

                    if (response.isSuccessful) {
                        val artist = response.body()?.response?.artist
                        runOnUiThread {
                            if (artist != null) {
                                textViewArtistInfo.text = "Nombre: ${artist.name}\nID: ${artist.id}"
                                // Mostrar otros campos de información del artista según la documentación
                            } else {
                                textViewArtistInfo.text = "Artista no encontrado."
                            }
                        }
                    } else {
                        runOnUiThread {
                            textViewArtistInfo.text = "Error en la solicitud."
                        }
                    }
                }
            } else {
                runOnUiThread {
                    textViewArtistInfo.text = "ID del artista no válido."
                }
            }
        }
    }
}
