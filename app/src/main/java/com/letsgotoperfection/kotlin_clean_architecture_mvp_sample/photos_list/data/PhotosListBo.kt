package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.PhotosList
import io.reactivex.Single

/**
 * @author hossam.
 */
class PhotosListBo(private val photosListDao: BasePhotosListDao) {
    fun fetchPhotos(): Single<PhotosList> {
        return photosListDao.fetchPhotosList()
    }
}