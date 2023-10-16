package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GeniusApiService {
    // Endpoint para buscar información del artista por su ID
    @GET("artists/{id}")
    suspend fun getArtistInfo(
        @Header("Authorization") token: String,
        @Path("id") artistId: Int
    ): Response<ArtistResponse>
}
