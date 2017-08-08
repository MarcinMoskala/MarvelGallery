package com.kotlintest.marvelgallery.view.common

import android.support.v7.app.AppCompatActivity
import com.kotlintest.marvelgallery.presenter.Presenter

abstract class BaseActivityWithPresenter : AppCompatActivity() {

    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
