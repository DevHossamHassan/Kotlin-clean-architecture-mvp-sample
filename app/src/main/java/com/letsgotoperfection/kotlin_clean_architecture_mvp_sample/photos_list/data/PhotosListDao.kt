package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.API.RetrofitProvider
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.PhotosList
import io.reactivex.Single

/**
 * @author hossam.
 */
class PhotosListDao: BasePhotosListDao() {
    override fun fetchPhotosList(): Single<PhotosList> {
        return RetrofitProvider.loadPhotosList()
    }
}