package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActividadFotos : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val apiKey = "S0OLNE7RIzrPNKsEl5Cxn3pzGdazgyoy3WK2cOpJ7PP8996TZaFV2NUh" // Reemplaza con tu API key de Pexels
    private val pexelsApi: PexelsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.pexels.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        pexelsApi = retrofit.create(PexelsApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_fotos)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = pexelsApi.searchPhotos("radiohead", apiKey = "Bearer $apiKey")
                val photos = response.photos

                withContext(Dispatchers.Main) {
                    val adapter = PhotoAdapter(photos)
                    recyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                // Handle errors
                e.printStackTrace()
            }
        }
    }
}