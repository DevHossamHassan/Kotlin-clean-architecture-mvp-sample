package com.letsgotoperfection.kotlin_clean_architecture_mvp_sample.listeners

import android.view.View

/**
 * @author hossam.
 */

interface OnRecyclerViewClickListener {
    fun onRecyclerViewItemClicked(v: View, position: Int)
}

