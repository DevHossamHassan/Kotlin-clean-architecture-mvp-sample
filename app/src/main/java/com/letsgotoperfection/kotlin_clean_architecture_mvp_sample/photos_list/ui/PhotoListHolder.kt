package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.listeners.OnRecyclerViewClickListener

/**
 * @author hossam.
 */

class PhotoListHolder(itemView: View,
                      private var onRecyclerViewClickListener: OnRecyclerViewClickListener)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(view: View) {
        onRecyclerViewClickListener.onRecyclerViewItemClicked(view, this.adapterPosition)
    }
}