package me.manson112.custom.shadeprogressbar

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class ItemParser(private val context: Context, @DrawableRes val res: Int) {
    fun parse(): Item {
        val drawable = ContextCompat.getDrawable(context, res)
        drawable?.let {
            return Item(alpha = 0, image = drawable)
        } ?: run {
            return Item(alpha = 0, image = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_android_black_24dp, null)!!)
        }
    }

}