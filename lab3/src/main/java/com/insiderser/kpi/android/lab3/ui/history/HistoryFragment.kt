package com.insiderser.kpi.android.lab3.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.insiderser.kpi.android.lab2.databinding.HistoryFragmentBinding
import com.insiderser.kpi.android.lab3.data.HistoryEntity
import com.insiderser.kpi.android.lab3.utils.collectWithLifecycle
import com.insiderser.kpi.android.lab3.utils.viewLifecycleScoped

class HistoryFragment : Fragment() {

    private val viewModel: HistoryViewModel by viewModels()

    private var binding: HistoryFragmentBinding by viewLifecycleScoped()
    private var historyAdapter: HistoryAdapter by viewLifecycleScoped()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyAdapter = HistoryAdapter()
        binding.historyList.adapter = historyAdapter

        viewModel.historyItems.collectWithLifecycle(viewLifecycleOwner) {
            handleHistory(it)
        }
    }

    private fun handleHistory(history: List<HistoryEntity>) {
        historyAdapter.submitList(history)
        binding.noHistoryContent.isVisible = history.isEmpty()
    }
}
