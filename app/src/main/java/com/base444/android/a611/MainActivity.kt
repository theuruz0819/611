package com.base444.android.a611

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        next_page_btn.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}

