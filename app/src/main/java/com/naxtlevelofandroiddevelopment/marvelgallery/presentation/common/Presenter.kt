package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common

import io.reactivex.disposables.CompositeDisposable

abstract class Presenter {

    var subscriptions = CompositeDisposable()

    // Here view might not be loaded yet
    open fun onCreate() {}

    open fun onStart() {}

    open fun onDestroy() {
        subscriptions.clear()
    }
}