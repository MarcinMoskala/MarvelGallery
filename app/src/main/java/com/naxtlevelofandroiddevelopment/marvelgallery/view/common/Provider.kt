package com.naxtlevelofandroiddevelopment.marvelgallery.view.common

abstract class Provider<T> {

    abstract fun creator(): T

    private var instance: T? = null
    var override: T? = null

    fun get(): T = override ?: instance ?: creator().apply { instance = this }
    fun lazyGet(): Lazy<T> = lazy { get() }
}