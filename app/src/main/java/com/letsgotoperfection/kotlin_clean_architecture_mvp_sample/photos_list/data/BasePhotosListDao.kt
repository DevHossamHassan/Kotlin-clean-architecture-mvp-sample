package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.PhotosList
import io.reactivex.Single

/**
 * @author hossam.
 */
abstract class BasePhotosListDao {
    abstract fun fetchPhotosList(): Single<PhotosList>
}