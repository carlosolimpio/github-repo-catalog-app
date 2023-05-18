package com.demo.githubrepocatalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.repolist.presentation.RepoListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, RepoListFragment())
            .commitNow()
    }
}
