package com.example.codechallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codechallenge.R
import com.example.codechallenge.ui.eventsList.EventsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                EventsFragment.newInstance()
            )
            .commit()
    }
}