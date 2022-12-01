package com.colddelight.github_info.presentaion

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(str :String){
    Glide.with(this)
        .load("https://avatars.githubusercontent.com/u/84781279?v=4")
        .into(this)

}