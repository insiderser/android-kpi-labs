package com.insiderser.kpi.android.lab3.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history ORDER BY timestamp DESC")
    fun getHistory(): Flow<List<HistoryEntity>>

    @Query("SELECT * FROM history WHERE id = :id")
    fun getHistoryItemById(id: Long): Flow<HistoryEntity>

    @Insert
    suspend fun insert(item: HistoryEntity): Long
}
