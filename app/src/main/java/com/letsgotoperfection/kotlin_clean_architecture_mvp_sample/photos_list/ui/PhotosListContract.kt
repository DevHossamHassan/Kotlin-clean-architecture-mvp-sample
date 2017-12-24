package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui

import android.app.Fragment
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.base.BaseContract

/**
 * @author hossam.
 */
class PhotosListContract : BaseContract {

    interface View : BaseContract.View<Fragment> {
        fun showToast(msg: String)
        fun updateDate()
        fun updateInsertedData(itemCount: Int)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter : BaseContract.Presenter {
        fun getPhotosListSize(): Int
        fun onBindPhotoViewAtPosition(position: Int, holder: PhotoListHolder)
        fun onPhotoClicked()
        fun onLoadMore()
    }
}