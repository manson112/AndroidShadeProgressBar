package me.manson112.custom.shadeprogressbar

import android.graphics.RectF
import android.graphics.drawable.Drawable

data class Item (
    var rect: RectF = RectF(),
    var alpha: Int,
    var image: Drawable
)