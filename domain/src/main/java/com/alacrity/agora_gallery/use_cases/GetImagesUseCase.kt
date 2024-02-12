package com.alacrity.agora_gallery.use_cases

import com.alacrity.agora_gallery.entity.Image

interface GetImagesUseCase {

    suspend operator fun invoke(apiKey: String): List<Image>

}