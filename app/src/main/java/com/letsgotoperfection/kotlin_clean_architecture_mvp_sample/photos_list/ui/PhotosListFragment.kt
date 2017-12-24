package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.R
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.base.BaseFragment
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.listeners.OnRecyclerViewScrollToTheEnd
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data.PhotosListBo
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data.PhotosListDao
import kotlinx.android.synthetic.main.photo_list_fragment_layout.*

/**
 * @author hossam.
 */
class PhotosListFragment : BaseFragment<PhotosListContract.Presenter>(), PhotosListContract.View {
    override val fragmentLayoutResourceId = R.layout.photo_list_fragment_layout
    private lateinit var adapter: PhotoListAdapter
    private lateinit var linLayManager: LinearLayoutManager
    override fun init() {
        presenter = PhotosListPresenter(this,
                PhotosListBo(PhotosListDao()), PhotosListModel)
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
        linLayManager = LinearLayoutManager(activity.applicationContext)
        photosRecyclerView.layoutManager = linLayManager
        photosRecyclerView.adapter = adapter
        setRecyclerViewListeners()

    }

    private fun setRecyclerViewListeners() {
        photosRecyclerView.addOnScrollListener(
                object : OnRecyclerViewScrollToTheEnd(linLayManager) {
                    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (linLayManager.findLastCompletelyVisibleItemPosition()
                                == adapter.itemCount - 1) {
                            presenter.onLoadMore()
                        }
                    }
                })
        setSwipeRefreshListeners()
    }

    private fun setSwipeRefreshListeners() {
        swipeRefreshLayout.setOnRefreshListener({ presenter.onLoadMore() })
    }

    override fun showToast(msg: String) {
        Toast.makeText(activity.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        swipeRefreshLayout?.isRefreshing = true
    }

    override fun hideProgressBar() {
        swipeRefreshLayout?.isRefreshing = false
    }

}