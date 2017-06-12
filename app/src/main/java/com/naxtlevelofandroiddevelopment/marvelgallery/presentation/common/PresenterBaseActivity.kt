package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common

import android.support.v7.app.AppCompatActivity

abstract class PresenterBaseActivity : AppCompatActivity() {

    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onActivityDestroy()
    }
}
