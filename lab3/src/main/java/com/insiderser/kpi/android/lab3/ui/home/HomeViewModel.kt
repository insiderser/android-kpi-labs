package com.insiderser.kpi.android.lab3.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.insiderser.kpi.android.lab3.data.AndroidLabsDatabase
import com.insiderser.kpi.android.lab3.data.Color
import com.insiderser.kpi.android.lab3.data.HistoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val historyDao = AndroidLabsDatabase(application).historyDao

    private val _saveSuccessEventFlow = MutableSharedFlow<SaveResult>()
    val saveSuccessEventFlow: Flow<SaveResult> get() = _saveSuccessEventFlow

    fun onSaveClick(text: String, color: Color) {
        viewModelScope.launch {
            val insertedId = historyDao.insert(HistoryEntity(text, color))
            _saveSuccessEventFlow.emit(SaveResult(insertedId))
        }
    }

    data class SaveResult(val newHistoryEntryId: Long)
}
