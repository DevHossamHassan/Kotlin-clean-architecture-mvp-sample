package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.base.BaseActivity
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.photos_list.ui.PhotosListFragment

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleResourceId(): String {
        return resources.getString(R.string.app_name)
    }

    override fun init() {
        NavigationManager.attachAsRoot(this, PhotosListFragment())
    }
}
