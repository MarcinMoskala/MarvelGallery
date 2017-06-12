package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(photoUrl: String) {
    Glide.with(context)
            .load(photoUrl)
            .into(this)
}

fun ImageView.loadImageCenterCropped(photoUrl: String) {
    Glide.with(context)
            .load(photoUrl)
            .centerCrop()
            .into(this)
}

fun TextView.setHtmlText(html: String) {
    text = Html.fromHtml(html)
}

fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun EditText.addOnTextChangedListener(callback: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            callback(s.toString())
        }

        override fun afterTextChanged(s: Editable) {}
    })
}

fun <T : Parcelable> Activity.extra(key: String, default: T? = null): Lazy<T> = lazy { intent?.extras?.getParcelable<T>(key) ?: default ?: throw Error("No value $key in extras") }

inline fun <reified T : Activity> Context.getIntent() = Intent(this, T::class.java)