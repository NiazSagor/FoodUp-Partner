package com.duodevloopers.fooduppartner.acitivities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MainActivityViewModel by viewModels()

    }
}