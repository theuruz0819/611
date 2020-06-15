package com.base444.android.a611

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.grid_cell.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        content_page_id_text_view.text = intent.getIntExtra("id", 0).toString()
        content_page_title_text_view.text = intent.getStringExtra("title")

        GlobalScope.launch {
            try {
                val url = URL(intent.getStringExtra("url"))
                val cn = url.openConnection();
                cn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
                val inStream: InputStream = cn.getInputStream()
                val thumbBitmap = BitmapFactory.decodeStream(inStream)
                withContext(Dispatchers.Main){
                    content_page_image_view.setImageBitmap(thumbBitmap)
                }
            } catch (e: Exception){
                Log.e("Main3Activity", e.message)
            }
        }

    }

}
