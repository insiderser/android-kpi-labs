package com.insiderser.kpi.android.lab3.ui.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.insiderser.kpi.android.lab2.databinding.SuccessFragmentBinding
import com.insiderser.kpi.android.lab3.data.HistoryEntity
import com.insiderser.kpi.android.lab3.utils.collectWithLifecycle
import com.insiderser.kpi.android.lab3.utils.viewLifecycleScoped

class SuccessFragment : Fragment() {

    private val viewModel: SuccessViewModel by viewModels()

    private var binding: SuccessFragmentBinding by viewLifecycleScoped()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SuccessFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelBtn.setOnClickListener { requireActivity().onBackPressed() }

        viewModel.historyItem.collectWithLifecycle(viewLifecycleOwner) {
            handleHistoryItem(it)
        }
    }

    private fun handleHistoryItem(item: HistoryEntity) {
        binding.output.setTextColor(item.color.value)
        binding.output.text = item.text
    }
}
