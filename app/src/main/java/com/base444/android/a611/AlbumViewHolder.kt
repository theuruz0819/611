package com.base444.android.a611

import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grid_cell.view.*
import kotlinx.coroutines.*
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class AlbumViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    var job: Job? = null;

    fun bindView(album: Album) {
        itemView.grid_cell_id_textview.text = album.id.toString()
        itemView.grid_cell_title_textview.text = album.title
        itemView.grid_cell_imageview.setImageResource(android.R.color.transparent)
        if (ImageCache.getFromCache(album.thumbnailUrl) != null){
            itemView.grid_cell_imageview.setImageBitmap(ImageCache.getFromCache(album.thumbnailUrl))
        } else {
            job?.cancel()
            job = GlobalScope.launch {
                try {
                    val url = URL(album.thumbnailUrl)
                    val cn = url.openConnection();
                    cn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
                    val inStream: InputStream = cn.getInputStream()
                    val thumbBitmap = BitmapFactory.decodeStream(inStream)
                    ImageCache.addToCache(album.thumbnailUrl, thumbBitmap)
                    withContext(Dispatchers.Main){
                        itemView.grid_cell_imageview.setImageBitmap(thumbBitmap)
                    }
                } catch (e:Exception){
                    Log.e("AlbunViewHolder", e.message)
                }
            }
        }
    }
}