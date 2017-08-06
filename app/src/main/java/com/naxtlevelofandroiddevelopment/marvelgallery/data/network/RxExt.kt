package com.naxtlevelofandroiddevelopment.marvelgallery.data.network

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(): Single<T> = this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.smartSubscribe(
        onStart: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onFinish: (() -> Unit)? = null,
        onSuccess: (T) -> Unit
): Disposable {
    onStart?.invoke()
    return doAfterTerminate { onFinish?.invoke() }
            .subscribe(onSuccess, { onError?.invoke(it) })
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
