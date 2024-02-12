package com.alacrity.agora_gallery.use_cases

import com.alacrity.agora_gallery.repository.Repository
import javax.inject.Inject

class GetImagesUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetImagesUseCase {

    override suspend fun invoke(apiKey: String) = repository.getImagesFromAPI(apiKey)

}