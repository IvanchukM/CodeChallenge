package com.example.codechallenge

import android.app.Application
import com.example.codechallenge.di.AppComponent
import com.example.codechallenge.di.DaggerAppComponent

class CodeChallengeApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }
}
