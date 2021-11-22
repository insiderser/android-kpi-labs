package com.insiderser.kpi.android.lab3.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.insiderser.kpi.android.lab3.data.AndroidLabsDatabase
import com.insiderser.kpi.android.lab3.data.HistoryEntity
import kotlinx.coroutines.flow.Flow

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    val historyItems: Flow<List<HistoryEntity>> = AndroidLabsDatabase(application).historyDao.getHistory()
}
