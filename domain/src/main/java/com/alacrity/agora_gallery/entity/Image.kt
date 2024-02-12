package com.alacrity.agora_gallery.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    val id: String,
    val likes: Int,
    val description: String?,
    val urls: Urls
)
data class Urls(
    val full: String,
    val regular: String,
)