package com.naxtlevelofandroiddevelopment.marvelgallery.network.providers

abstract class Provider<T>(val initializer: () -> T) {

    var original: T? = null
    var override: T? = null

    fun get(): T = override ?: original ?: initializer().apply { original = this }
    fun lazyGet(): Lazy<T> = lazy { get() }
}
