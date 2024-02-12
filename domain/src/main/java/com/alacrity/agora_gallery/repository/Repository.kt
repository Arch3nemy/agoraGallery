package com.alacrity.agora_gallery.repository

import com.alacrity.agora_gallery.entity.Image

interface Repository {

    suspend fun getImagesFromAPI(apiKey: String): List<Image>

}