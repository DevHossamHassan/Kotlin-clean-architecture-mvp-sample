package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.API.RetrofitProvider
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.RxTestRule
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.Media
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.Photo
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.PhotosList
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single.just
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

/**
 * @author hossam.
 */
class PhotosListDaoTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxTestRule()

    private val mockedListDao = PhotosListDao()
    private val retrofitProvider = Mockito.mock(RetrofitProvider::class.java)

    @Before
    fun setUp() {
        val photosListObject: PhotosList = getPhotosListObject()
        `whenever`(retrofitProvider.loadPhotosList()).thenReturn(just(photosListObject))
    }

    @Test
    fun fetchPhotosListTest() {
        val testObserver: TestObserver<PhotosList> = mockedListDao.fetchPhotosList().test()
        testObserver.assertNoErrors()
    }

    @Test
    fun shouldReturnTrueIfPhotosListHas20Items() {
        val result = mockedListDao.fetchPhotosList()

        val testObserver = TestObserver<PhotosList>()

        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertThat(listResult.items?.size, `is`(20))
    }

    private fun getPhotosListObject(): PhotosList {
        val photo = getPreparedPhotoObject()
        val list = mutableListOf<Photo>()
        for (i in 0..20) {
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
}