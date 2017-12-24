package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample

import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleResourceId(): String {
        return resources.getString(R.string.app_name)
    }

    override fun init() {
        //Todo navigate to main fragment
    }
}
