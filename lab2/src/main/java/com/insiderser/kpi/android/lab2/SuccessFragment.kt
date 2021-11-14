package com.insiderser.kpi.android.lab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.insiderser.kpi.android.lab2.databinding.SuccessFragmentBinding

class SuccessFragment : Fragment() {

    private var binding: SuccessFragmentBinding by viewLifecycleScoped()

    private val text: String by lazy {
        arguments?.getString(ARG_TEXT) ?: throw IllegalArgumentException("Required args not supplied")
    }

    @get:ColorInt
    private val color: Int by lazy {
        arguments?.getInt(ARG_COLOR) ?: throw IllegalArgumentException("Required args not supplied")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SuccessFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.output.setTextColor(color)
        binding.output.text = text

        binding.cancelBtn.setOnClickListener { findNavController().navigateBack() }
    }

    companion object {

        private const val ARG_TEXT = "SuccessFragment::text"
        private const val ARG_COLOR = "SuccessFragment::color"

        fun createArgs(text: String, @ColorInt color: Int): Bundle = bundleOf(
            ARG_TEXT to text,
            ARG_COLOR to color,
        )
    }
}
