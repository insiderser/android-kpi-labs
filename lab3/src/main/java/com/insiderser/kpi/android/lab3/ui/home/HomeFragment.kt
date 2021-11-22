package com.insiderser.kpi.android.lab3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.insiderser.kpi.android.lab2.R
import com.insiderser.kpi.android.lab2.databinding.HomeFragmentBinding
import com.insiderser.kpi.android.lab3.data.Color
import com.insiderser.kpi.android.lab3.ui.home.HomeViewModel.SaveResult
import com.insiderser.kpi.android.lab3.utils.collectWithLifecycle
import com.insiderser.kpi.android.lab3.utils.viewLifecycleScoped

class HomeFragment : Fragment() {

    private var binding: HomeFragmentBinding by viewLifecycleScoped()

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.okBtn.setOnClickListener { onOkClick() }
        binding.historyBtn.setOnClickListener { onHistoryClick() }

        viewModel.saveSuccessEventFlow.collectWithLifecycle(viewLifecycleOwner) {
            handleSaveResult(it)
        }
    }

    private fun onOkClick() {
        val input = binding.editText.text?.toString()
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

        viewModel.onSaveClick(input, color)
    }

    private fun handleSaveResult(result: SaveResult) {
        Snackbar.make(binding.root.parent as View, R.string.save_success, Snackbar.LENGTH_SHORT).show()
        findNavController().navigate(HomeFragmentDirections.toSuccess(result.newHistoryEntryId))
    }

    private fun onHistoryClick() {
        findNavController().navigate(HomeFragmentDirections.toHistory())
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
