package com.demo.githubrepocatalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.githublogin.presentation.GithubLoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, GithubLoginFragment())
            .commitNow()
    }
}
