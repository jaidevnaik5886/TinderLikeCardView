package com.shaadi.peopleinteractive.bindings

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shaadi.peopleinteractive.R

@BindingAdapter("image", "placeholder")
fun setImage(image: ImageView, url: String?, placeHolder: Drawable) {
    if (!url.isNullOrEmpty()) {
        Glide.with(image.context).load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .apply(RequestOptions.circleCropTransform())
            .into(image)
    } else {
        image.setImageDrawable(placeHolder)
    }
}