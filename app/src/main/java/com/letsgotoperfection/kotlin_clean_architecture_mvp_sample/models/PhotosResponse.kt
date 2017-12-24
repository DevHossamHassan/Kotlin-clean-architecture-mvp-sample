package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.models

/**
 * @author hossam.
 */

data class PhotosList(
        val title: String = "title", //Uploads from everyone
        val link: String = "link", //https://www.flickr.com/photos/
        val description: String = "description",
        val modified: String = "modified", //2017-12-22T18:34:41Z
        val generator: String = "generator", //https://www.flickr.com
        var items: MutableList<Photo>?
)

data class Photo(
        val title: String, //IMG_2890.jpg
        val link: String, //https://www.flickr.com/photos/afsunehn/24360740827/
        val media: Media,
        val date_taken: String, //2017-12-20T10:10:11-08:00
        val description: String, // <p><a href="https://www.flickr.com/people/afsunehn/">Afsaneh Noorparvar</a> posted a photo:</p> <p><a href="https://www.flickr.com/photos/afsunehn/24360740827/" title="IMG_2890.jpg"><img src="https://farm5.staticflickr.com/4688/24360740827_e401701596_m.jpg" width="218" height="240" alt="IMG_2890.jpg" /></a></p>
        val published: String, //2017-12-22T18:34:41Z
        val author: String, //nobody@flickr.com ("Afsaneh Noorparvar")
        val author_id: String, //128454838@N07
        val tags: String
)

data class Media(
        val m: String //https://farm5.staticflickr.com/4688/24360740827_e401701596_m.jpg
)