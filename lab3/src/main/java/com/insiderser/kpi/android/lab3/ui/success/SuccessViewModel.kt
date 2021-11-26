package com.insiderser.kpi.android.lab3.ui.success

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.insiderser.kpi.android.lab3.data.AndroidLabsDatabase
import com.insiderser.kpi.android.lab3.data.HistoryEntity
import kotlinx.coroutines.flow.Flow

class SuccessViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle,
) : AndroidViewModel(application) {

    val historyItem: Flow<HistoryEntity>

    init {
        val navArgs = SuccessFragmentArgs.fromSavedStateHandle(savedStateHandle)
        val historyDao = AndroidLabsDatabase(application).historyDao
        historyItem = historyDao.getHistoryItemById(navArgs.historyEntryId)
    }
}
