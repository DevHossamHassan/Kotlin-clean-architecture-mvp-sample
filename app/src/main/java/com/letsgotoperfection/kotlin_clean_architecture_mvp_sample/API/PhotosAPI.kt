package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.API

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.PhotosList
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author hossam.
 */
interface PhotosAPI {

    @GET("?format=json&nojsoncallback=1")
    fun getPhotos() :Single<PhotosList>
}