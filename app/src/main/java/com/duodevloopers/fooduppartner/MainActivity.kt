package com.duodevloopers.fooduppartner

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MainActivityViewModel by viewModels()

    }
}