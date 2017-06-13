package com.naxtlevelofandroiddevelopment.marvelgallery.view.common

import android.support.v7.app.AppCompatActivity
import com.naxtlevelofandroiddevelopment.marvelgallery.presenter.Presenter

abstract class PresenterBaseActivity : AppCompatActivity() {

    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
