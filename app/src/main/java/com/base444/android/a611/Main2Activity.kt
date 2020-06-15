package com.base444.android.a611

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.net.URL
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    var adapter: AlbumAdapter? = null
    lateinit var albumList: List<Album>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        grid_view.layoutManager = GridLayoutManager(this,4)

        var onClickInterface = object : AlbumAdapter.OnItemClickInterface{
            override fun onItemClick(index: Int) {
                var album = albumList[index]
                val intent = Intent(this@Main2Activity, Main3Activity::class.java)
                intent.putExtra("id", album.id)
                intent.putExtra("title", album.title)
                intent.putExtra("url", album.url)
                startActivity(intent)
            }
        }

        GlobalScope.launch {
            var data = fetchDataFromApi()
            withContext(Dispatchers.Main) {
                adapter = AlbumAdapter(data, onClickInterface);
                grid_view.adapter = adapter
            }
        }
    }
    fun fetchDataFromApi(): List<Album>{

        val data = URL("https://jsonplaceholder.typicode.com/photos").readText()
        val gson = Gson()
        val itemType = object : TypeToken<List<Album>>() {}.type
        albumList = gson.fromJson(data, itemType)
        return albumList;
    }


}
