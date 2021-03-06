package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.NavigationManager

/**
 * @author hossam.
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract fun getLayoutResourceId(): Int
    abstract fun getTitleResourceId(): String
    abstract fun init()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())

        title = getTitleResourceId()
        if (savedInstanceState == null) {
            init()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        NavigationManager.navigateBack(this)
    }

}