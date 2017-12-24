package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.R
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.listeners.OnRecyclerViewClickListener

/**
 * @author hossam.
 */
class PhotoListAdapter(private val presenter: PhotosListPresenter) : RecyclerView.Adapter<PhotoListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PhotoListHolder {

        return PhotoListHolder((
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.photo_list_item, parent, false)),
                object : OnRecyclerViewClickListener {
                    override fun onRecyclerViewItemClicked(v: View, position: Int) {
                        //todo  implement on photoClicked
                    }
                })

    }

    override fun onBindViewHolder(holder: PhotoListHolder, position: Int) {
        presenter.onBindPhotoViewAtPosition(position, holder)
    }

    override fun getItemCount(): Int {
        return presenter.getPhotosListSize()
    }

}
