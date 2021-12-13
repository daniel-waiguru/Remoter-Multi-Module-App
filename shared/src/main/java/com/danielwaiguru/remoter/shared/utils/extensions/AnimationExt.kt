package com.danielwaiguru.remoter.shared.utils.extensions

import android.view.View
import android.view.animation.AnimationUtils
import com.danielwaiguru.remoter.shared.R
import com.danielwaiguru.remoter.shared.utils.AppConstants.ANIMATION_DURATION

fun View.slideOutBottom(duration: Long = ANIMATION_DURATION) {
    val slideBottom = AnimationUtils.loadAnimation(
        context,
        R.anim.slide_out_bottom
    )
    this.apply {
        startAnimation(slideBottom).also {
            visibility = View.GONE
        }
    }
}
fun View.slideInBottom() {
    val slideUp = AnimationUtils.loadAnimation(
        context,
        R.anim.slide_in_bottom
    )
    this.apply {
        startAnimation(slideUp)
        visibility = View.VISIBLE
    }
}