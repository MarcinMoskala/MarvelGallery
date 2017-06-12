package com.naxtlevelofandroiddevelopment.marvelgallery.network

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object Rx {
    var unitTestMode = false
}

fun <T> Single<T>.applySchedulers(): Single<T> = if (Rx.unitTestMode) this else
    subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.smartSubscribe(
        onStart: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onFinish: (() -> Unit)? = null,
        onSuccess: (T) -> Unit = {}
): Disposable =
        addStartFinishActions(onStart, onFinish)
                .subscribe(onSuccess, { onError?.invoke(it) })

fun <T> Single<T>.addStartFinishActions(onStart: (() -> Unit)? = null, onFinish: (() -> Unit)? = null): Single<T> {
    onStart?.invoke()
    return doAfterTerminate { onFinish?.invoke() }
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
