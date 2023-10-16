package com.example.myapplication

import com.google.gson.annotations.SerializedName
data class ArtistResponse(
    @SerializedName("response") val response: ArtistData
)

data class ArtistData(
    @SerializedName("artist") val artist: Artist
)

data class Artist(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    // Otros campos de información del artista según la documentación
)