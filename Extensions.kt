package com.colinbrevitz

import android.content.Context
import android.os.Build
import android.support.annotation.LayoutRes
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by Colin Brevitz on 2/20/18.
 *
 * This class contains some handy extensions I wrote
 */

val Context.picasso: Picasso
    get() = Picasso.with(this)

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.load(path: String) {
    context.picasso.load(path)
            .error(context.getDrawable(R.drawable.ic_sneaker_placeholder))
            .into(this, object : Callback {
                override fun onSuccess() {
                    Log.v("Picasso", "Loading success!!")
                }

                override fun onError() {
                    Log.v("Picasso", "Loading fail!!")
                }

            })

}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

@Suppress("DEPRECATION")
fun TextView.setTextFromHtml(text: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    } else {
        this.text = Html.fromHtml(text)
    }

    this.movementMethod = LinkMovementMethod.getInstance()
}