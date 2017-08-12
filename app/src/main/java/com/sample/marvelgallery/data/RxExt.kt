package com.sample.marvelgallery.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(): Single<T> = this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun <T> Single<T>.smartSubscribe(
        onStart: (() -> Unit)? = null, // 1
        onError: ((Throwable) -> Unit)? = null, // 2
        onFinish: (() -> Unit)? = null, // 3
        onSuccess: (T) -> Unit // 4
): Disposable {
    onStart?.invoke()
    return doAfterTerminate { onFinish?.invoke() }
            .subscribe(onSuccess, { onError?.invoke(it) })
}
