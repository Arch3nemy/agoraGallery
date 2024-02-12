package com.alacrity.agora_gallery.api

import com.alacrity.agora_gallery.entity.Image
import com.alacrity.agora_gallery.exceptions.AgoraGalleryException
import com.alacrity.agora_gallery.retrofit.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/photos/")
    suspend fun getImages(
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String
    ): NetworkResponse<List<Image>, AgoraGalleryException>
}