package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class PresenterBaseActivity : AppCompatActivity() {

    abstract val presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
