package com.shivam.youtubelikelayout.ui.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder


//@BindingAdapter(
//    "glideSrc",
//    "glideCenterCrop",
//    "glideCircularCrop",
//    requireAll = false
//)
//fun ImageView.bindGlideSrc(
//    drawableRes: String?,
//    centerCrop: Boolean,
//    circleCrop: Boolean
//) {
//    if (drawableRes == null) return
//    createGlideRequest(
//        context,
//        drawableRes,
//        centerCrop,
//        circleCrop
//    )
//}
//private fun createGlideRequest(
//    context: Context,
//    src: String,
//    centerCrop: Boolean,
//    circularCrop: Boolean
//): RequestBuilder<Drawable> {
//    val req = Glide.with(context).load(src)
//    if (centerCrop) req.centerCrop()
//    if (circularCrop) req.circleCrop()
//    return req
//}

@BindingAdapter("glide")
fun glide(view: ImageView,url: String?){
    if (!url.isNullOrBlank()){
        Glide.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("glideCircleCrop")
fun glideCircleCrop(view: ImageView,url: String?){
    if (!url.isNullOrBlank()){
        Glide.with(view.context).load(url).circleCrop().into(view)
    }
}