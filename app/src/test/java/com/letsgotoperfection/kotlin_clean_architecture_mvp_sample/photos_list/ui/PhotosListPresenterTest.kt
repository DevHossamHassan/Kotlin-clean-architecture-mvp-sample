package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.RxTestRule
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.Media
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.Photo
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.PhotosList
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data.PhotosListBo
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data.PhotosListDao
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

/**
 * @author hossam.
 */
class PhotosListPresenterTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxTestRule()

    private lateinit var presenter: PhotosListPresenter
    private val photoListHolder: PhotoListHolder = mock()
    private val model = PhotosListModel
    private val photosListView: PhotosListContract.View = mock()
    private val photosListDao: PhotosListDao = mock()

    @Before
    fun setUp() {
        val photosListObject: PhotosList = getPhotosListObject()
        model.photoList.items = null
        `whenever`(photosListDao.fetchPhotosList()).thenReturn(Single.just(photosListObject))
        presenter = PhotosListPresenter(photosListView, PhotosListBo(photosListDao), model)
    }

    @Test
    fun modelShouldHas20PhotosAfterPresenterInitialization() {
        Assert.assertThat(presenter.getPhotosListSize(), CoreMatchers.`is`(20))
    }


    private fun getPhotosListObject(): PhotosList {
        val photo = getPreparedPhotoObject()
        val list = mutableListOf<Photo>()
        for (i in 1..20) {
            list.add(photo)
        }
        return PhotosList(items = list)
    }

    private fun getPreparedPhotoObject(): Photo {
        return Photo(
                title = "title",
                description = "description",
                author = "author",
                author_id = "author_id",
                link = "link",
                published = "2017-12-19T00:42:32Z",
                tags = "tags",
                date_taken = "2017-12-18T16:42:32-08:00",
                media = Media("https://farm5.staticflickr.com/4688/24360740827_e401701596_m.jpg"))
    }

    @Test
    fun onHideProgressTest() {
        model.photoList.items = null
        presenter.onLoadMore()
        verify(photosListView, times(2)).hideProgressBar()
    }

    @Test
    fun onShowProgressTest() {
        model.photoList.items = null
        presenter.onLoadMore()
        verify(photosListView, times(2)).showProgressBar()
    }

}
