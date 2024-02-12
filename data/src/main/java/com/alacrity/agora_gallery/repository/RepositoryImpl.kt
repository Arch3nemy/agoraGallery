package com.alacrity.agora_gallery.repository

import com.alacrity.agora_gallery.api.Api
import com.alacrity.agora_gallery.entity.Image
import com.alacrity.agora_gallery.exceptions.AgoraGalleryException
import com.alacrity.agora_gallery.retrofit.NetworkResponse
import timber.log.Timber
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {

    override suspend fun getImagesFromAPI(apiKey: String): List<Image> {
        when (val call = api.getImages(IMAGES_PER_PAGE, apiKey)) {
            is NetworkResponse.Success -> {
                val data = call.body
                Timber.d("data $data")
                return call.body
            }
            is NetworkResponse.ApiError -> {
                throw AgoraGalleryException("Server error, try again later")
            }
            is NetworkResponse.NetworkError -> {
                throw AgoraGalleryException("No network connection")
            }
            is NetworkResponse.UnknownError -> {
                throw AgoraGalleryException("Unknown error")
            }
        }
    }

    companion object {
        const val IMAGES_PER_PAGE = 10
    }
}