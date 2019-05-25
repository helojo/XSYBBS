package com.wsg.kotlin.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.adapter
 *  文件名:   GlideImageLoader
 *  创建者:   wsg
 *  创建时间: 2019/5/25 15:42
 *  描述:     轮播图加载器
 */

class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Glide.with(context).load(path).into(imageView);
    }
}