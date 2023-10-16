package com.example.myapplication

data class Photo(
    val id: Int,
    val photographer: String,
    val src: PhotoSource
)

data class PhotoSource(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String
)

data class PexelsResponse(
    val photos: List<Photo>
)
