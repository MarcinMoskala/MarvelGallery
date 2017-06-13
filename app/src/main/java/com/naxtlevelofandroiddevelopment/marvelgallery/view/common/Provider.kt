package com.naxtlevelofandroiddevelopment.marvelgallery.view.common

abstract class Provider<T>(private val initializer: () -> T) {

    private var instance: T? = null
    var override: T? = null

    fun get(): T = override ?: instance ?: initializer().apply { instance = this }
    fun lazyGet(): Lazy<T> = lazy { get() }
}
