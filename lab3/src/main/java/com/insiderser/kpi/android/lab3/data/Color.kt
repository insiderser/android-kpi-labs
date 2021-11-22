package com.insiderser.kpi.android.lab3.data

import androidx.annotation.ColorInt
import android.graphics.Color as AndroidColor

enum class Color(@ColorInt val value: Int) {
    RED(AndroidColor.RED),
    GREEN(AndroidColor.GREEN),
    BLUE(AndroidColor.BLUE),
    BLACK(AndroidColor.BLACK),
}
