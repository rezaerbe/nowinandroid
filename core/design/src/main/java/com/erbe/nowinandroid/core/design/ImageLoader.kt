package com.erbe.nowinandroid.core.design

import android.widget.ImageView
import coil.load

fun ImageView.loadImage(image: String) {
    this.load(image) {
        crossfade(true)
        placeholder(R.drawable.image)
        error(R.drawable.image)
    }
}