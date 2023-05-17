package com.demo.kointest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.kointest.view.FetchDataFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, FetchDataFragment())
            .commitNow()
    }
}