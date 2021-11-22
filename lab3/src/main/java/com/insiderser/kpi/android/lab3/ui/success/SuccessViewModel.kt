package com.insiderser.kpi.android.lab3.ui.success

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.insiderser.kpi.android.lab3.data.AndroidLabsDatabase
import com.insiderser.kpi.android.lab3.data.HistoryEntity
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SuccessViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle,
) : AndroidViewModel(application) {

    private val historyDao = AndroidLabsDatabase(application).historyDao

    private val _historyItem = MutableSharedFlow<HistoryEntity>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val historyItem: Flow<HistoryEntity> get() = _historyItem

    init {
        val navArgs = SuccessFragmentArgs.fromSavedStateHandle(savedStateHandle)
        init(navArgs.historyEntryId)
    }

    private fun init(itemId: Long) {
        viewModelScope.launch {
            historyDao.getHistoryItemById(itemId).collect { item ->
                _historyItem.emit(item)
            }
        }
    }
}
