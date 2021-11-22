package com.insiderser.kpi.android.lab3.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.insiderser.kpi.android.lab2.databinding.HistoryItemBinding
import com.insiderser.kpi.android.lab3.data.HistoryEntity

class HistoryAdapter : ListAdapter<HistoryEntity, HistoryViewHolder>(HistoryEntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryItemBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) = holder.bind(getItem(position))
}

class HistoryViewHolder(
    private val binding: HistoryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HistoryEntity) {
        binding.textView.text = item.text
        binding.textView.setTextColor(item.color.value)
    }
}

private class HistoryEntityDiffCallback : DiffUtil.ItemCallback<HistoryEntity>() {
    override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean = oldItem == newItem
}
