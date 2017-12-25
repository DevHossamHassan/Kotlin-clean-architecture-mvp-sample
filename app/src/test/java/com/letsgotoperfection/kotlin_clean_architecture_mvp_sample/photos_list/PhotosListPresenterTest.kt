package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.PhotosList
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data.MockedPhotosListDao
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data.PhotosListBo
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui.PhotoListHolder
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui.PhotosListContract
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui.PhotosListModel
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui.PhotosListPresenter
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

/**
 * @author hossam.
 */
class PhotosListPresenterTest {

    private lateinit var presenter: PhotosListPresenter
    private lateinit var model: PhotosListModel
    private val photoListHolder: PhotoListHolder = mock()
    private val photosListView: PhotosListContract.View = mock()
    private val mockedListDao = MockedPhotosListDao()

    @Before
    fun setUp() {
        model = PhotosListModel
        presenter = PhotosListPresenter(photosListView, PhotosListBo(MockedPhotosListDao()), model)
    }

    @Test
    fun shouldReturn20Photos() {
        val testObserver: TestObserver<PhotosList> = mockedListDao.fetchPhotosList().test()
        testObserver.assertNoErrors()
        testObserver.awaitTerminalEvent()

    }

    @Test
    fun onLoadMoreTest() {
        presenter.onLoadMore()
    }
}
