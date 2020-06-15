package com.base444.android.a611

import android.graphics.Bitmap

object ImageCache {
    var imageCacheMap: MutableMap<String, Bitmap> = mutableMapOf()

    fun addToCache(url:String, bitmap: Bitmap){
        imageCacheMap.put(url, bitmap)
    }

    fun getFromCache(url:String): Bitmap?{
        return imageCacheMap.get(url);
    }
}