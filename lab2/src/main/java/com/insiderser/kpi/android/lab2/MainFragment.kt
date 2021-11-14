package com.insiderser.kpi.android.lab2

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.insiderser.kpi.android.lab2.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var binding: MainFragmentBinding by viewLifecycleScoped()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.okBtn.setOnClickListener { onOkClick() }
    }

    private fun onOkClick() {
        val input = binding.editText.text
        if (input.isNullOrBlank()) {
            showMessage(R.string.please_input_text)
            return
        }

        val color = when (binding.colorsRadioGroup.checkedRadioButtonId) {
            R.id.redRadioBtn -> Color.RED
            R.id.greenRadioBtn -> Color.GREEN
            R.id.blueRadioBtn -> Color.BLUE
            R.id.blackRadioBtn -> Color.BLACK
            else -> {
                showMessage(R.string.please_select_color)
                return
            }
        }

        findNavController().openSuccess(input.toString(), color)
    }

    private fun showMessage(@StringRes messageRes: Int) = showMessage(getString(messageRes))
    private fun showMessage(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.invalid_input_title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}
