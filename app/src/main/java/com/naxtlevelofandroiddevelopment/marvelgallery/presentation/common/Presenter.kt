package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common

import io.reactivex.disposables.CompositeDisposable

abstract class Presenter {

    var subscriptions = CompositeDisposable()

    fun onActivityDestroy() {
        subscriptions.clear()
    }
}