package com.insiderser.kpi.android.lab1

import android.graphics.Color
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.insiderser.kpi.android.lab1.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val binding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.okBtn.setOnClickListener { onOkClick() }
        binding.cancelBtn.setOnClickListener { onCancelClick() }
    }

    private fun onOkClick() {
        val input = binding.editText.text
        if (input.isNullOrEmpty()) {
            onNoInputText()
            return
        }

        val color = when (binding.colorsRadioGroup.checkedRadioButtonId) {
            R.id.redRadioBtn -> Color.RED
            R.id.greenRadioBtn -> Color.GREEN
            R.id.blueRadioBtn -> Color.BLUE
            R.id.blackRadioBtn -> Color.BLACK
            else -> {
                onColorNotSelected()
                return
            }
        }

        binding.output.setTextColor(color)
        binding.output.text = input
    }

    private fun onColorNotSelected() = showInvalidInputDialog(R.string.please_select_color)
    private fun onNoInputText() = showInvalidInputDialog(R.string.please_input_text)

    private fun showInvalidInputDialog(@StringRes messageRes: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.invalid_input_title)
            .setMessage(messageRes)
            .show()
    }

    private fun onCancelClick() {
        binding.output.text = null
    }
}
