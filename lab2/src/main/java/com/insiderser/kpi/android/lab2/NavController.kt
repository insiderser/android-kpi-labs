package com.insiderser.kpi.android.lab2

import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment

interface NavController {

    fun openSuccess(text: String, @ColorInt color: Int)

    fun navigateBack()
}

fun Fragment.findNavController() = requireActivity() as NavController
