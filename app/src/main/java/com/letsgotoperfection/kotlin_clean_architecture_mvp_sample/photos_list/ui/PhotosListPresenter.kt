package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.Photo
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data.PhotosListBo
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.utils.DateFormat
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.utils.loadUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.photo_list_item.view.*

/**
 * @author hossam.
 */
class PhotosListPresenter(private var photosListView: PhotosListContract.View,
                          private var photosListBo: PhotosListBo, private var photosListModel: PhotosListModel) : PhotosListContract.Presenter {

    init {
        photosListView.showProgressBar()
        photosListBo.fetchPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it ->
                            if (photosListModel.photoList.items == null) {
                                photosListModel.photoList.items = it.items
                                photosListView.updateDate()
                            } else {
                                photosListModel.photoList.items?.addAll(it.items as MutableList<Photo>)
                                photosListView.updateInsertedData(it.items?.size?:0)
                            }
                            photosListView.hideProgressBar()
                        },
                        { e ->
                            photosListView.hideProgressBar()
                            photosListView.showToast("There is no photos to display them " + e.message)
                        })

    }

    override fun getPhotosListSize(): Int {
        return photosListModel.photoList.items?.size ?: return 0
    }

    override fun onBindPhotoViewAtPosition(position: Int, holder: PhotoListHolder) {
        val photo: Photo = photosListModel.photoList.items!![position]
        holder.itemView.tvTitle.text = photo.title
        holder.itemView.tvPublishedAt.text = DateFormat.formatStringDate(photo.published)
        holder.itemView.imgView.loadUrl(photo.media.m)
    }


    override fun onLoadMore() {
        photosListView.showProgressBar()

        photosListBo.fetchPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it ->
                            if (photosListModel.photoList.items == null) {
                                photosListModel.photoList.items = it.items
                                photosListView.updateDate()

                            } else {
                                photosListModel.photoList.items?.addAll(it.items as MutableList<Photo>)
                                photosListView.updateInsertedData(it.items?.size ?: 0)
                            }
                            photosListView.hideProgressBar()
                        },
                        { e ->
                            photosListView.hideProgressBar()
                            photosListView.showToast("Something went wrong! :" + e.message)
                        })

    }

    override fun onPhotoClicked() {

    }
}