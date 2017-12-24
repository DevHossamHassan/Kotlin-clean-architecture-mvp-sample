package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.R
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.base.BaseFragment
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data.MockedPhotosListDao
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data.PhotosListBo
import kotlinx.android.synthetic.main.photo_list_fragment_layout.*

/**
 * @author hossam.
 */
class PhotosListFragment : BaseFragment<PhotosListContract.Presenter>(), PhotosListContract.View {
    override val fragmentLayoutResourceId = R.layout.photo_list_fragment_layout
    private lateinit var adapter: PhotoListAdapter

    override fun init() {
        presenter = PhotosListPresenter(this,
                PhotosListBo(MockedPhotosListDao()), PhotosListModel)
    }

    override fun updateDate() {
        adapter.notifyDataSetChanged()
    }

    override fun updateInsertedData(itemCount: Int) {
        adapter.notifyItemRangeInserted(presenter.getPhotosListSize() - itemCount, itemCount)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PhotoListAdapter(presenter as PhotosListPresenter)
        photosRecyclerView.setHasFixedSize(true)
        photosRecyclerView.itemAnimator = DefaultItemAnimator()
        photosRecyclerView.layoutManager = LinearLayoutManager(activity.applicationContext)
        photosRecyclerView.adapter = adapter
    }

    override fun showToast(msg: String) {
        Toast.makeText(activity.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}