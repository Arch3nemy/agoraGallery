package com.alacrity.template.repository

import com.alacrity.template.api.Api
import com.alacrity.template.entity.ApiResponse
import com.alacrity.template.exceptions.TemplateException
import com.alacrity.template.retrofit.NetworkResponse
import timber.log.Timber
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {

    override suspend fun getResponse(number: Int): ApiResponse {
        when (val call = api.getFactAboutNumber(number)) {
            is NetworkResponse.Success -> {
                val data = call.body
                Timber.d("data $data")
                return call.body
            }
            is NetworkResponse.ApiError -> {
                throw TemplateException("Server error, try again later")
            }
            is NetworkResponse.NetworkError -> {
                throw TemplateException("No network connection")
            }
            is NetworkResponse.UnknownError -> {
                throw TemplateException("Unknown error")
            }
        }
    }

}