package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.data

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.Media
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.Photo
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models.PhotosList
import io.reactivex.Single
import org.json.JSONException
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL


/**
 * @author hossam.
 */
class MockedPhotosListDao : BasePhotosListDao() {
    override fun fetchPhotosList(): Single<PhotosList> {
        return Single.just(getList())
    }

    @Throws(JSONException::class, IOException::class)
    private fun getList(): PhotosList {
        val photo = Photo(
                title = "title",
                description = "description",
                author = "author",
                author_id = "author_id",
                link = "link",
                published = "2017-12-19T00:42:32Z",
                tags = "tags",
                date_taken = "2017-12-18T16:42:32-08:00",
                media = Media("https://farm5.staticflickr.com/4688/24360740827_e401701596_m.jpg"))
        val itemsList = mutableListOf<Photo>()
        for (i in 0..20) {
            itemsList.add(photo)
        }
        return PhotosList(items = itemsList)
    }

    @Throws(IOException::class, JSONException::class)
    private fun getMockedPhotosListJson(): String {
        val classLoader = MockedPhotosListDao::class.java!!.getClassLoader()
        val resource = classLoader.getResource("photoslist.json") as URL

        return readFile(resource.path).replace("\n".toRegex(), "")
    }

    @Throws(IOException::class)
    private fun readFile(fileName: String): String {
        val inputStream = FileInputStream(fileName)
        val fileReader = InputStreamReader(inputStream, "UTF-8")
        val br = BufferedReader(fileReader)
        br.use {
            val sb = StringBuilder()
            var line: String? = it.readLine()

            while (line != null) {
                sb.append(line)
                sb.append("\n")
                line = it.readLine()
            }
            return sb.toString()
        }
    }
}