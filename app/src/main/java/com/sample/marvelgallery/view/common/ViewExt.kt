package com.sample.marvelgallery.view.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.annotation.IdRes
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import android.support.v7.widget.RecyclerView
import android.view.View

fun <T : View> RecyclerView.ViewHolder.bindView(viewId: Int)
        = lazy { itemView.findViewById<T>(viewId) }

fun ImageView.loadImage(photoUrl: String, centerCropped: Boolean = false) {
    Glide.with(context)
            .load(photoUrl)
            .apply { if (centerCropped) centerCrop() }
            .into(this)
}

fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun <T : Parcelable> Activity.extra(key: String, default: T? = null): Lazy<T>
        = lazy { intent?.extras?.getParcelable<T>(key) ?: default ?: throw Error("No value $key in extras") }

inline fun <reified T : Activity> Context.getIntent() = Intent(this, T::class.java)

fun Activity.bindToSwipeRefresh(@IdRes swipeRefreshLayoutId: Int): ReadWriteProperty<Any?, Boolean>
        = SwipeRefreshBinding(lazy { findViewById<SwipeRefreshLayout>(swipeRefreshLayoutId) })

private class SwipeRefreshBinding(lazyViewProvider: Lazy<SwipeRefreshLayout>) : ReadWriteProperty<Any?, Boolean> {

    val view by lazyViewProvider

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return view.isRefreshing
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        view.isRefreshing = value
    }
}
