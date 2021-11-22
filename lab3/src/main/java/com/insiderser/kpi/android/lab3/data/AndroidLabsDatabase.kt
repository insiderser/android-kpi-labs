package com.insiderser.kpi.android.lab3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "android-labs.db"

@Database(
    entities = [
        HistoryEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false,
)
abstract class AndroidLabsDatabase : RoomDatabase() {

    abstract val historyDao: HistoryDao

    companion object {

        private var instance: AndroidLabsDatabase? = null

        operator fun invoke(context: Context): AndroidLabsDatabase {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        buildInstance(context)
                    }
                }
            }
            return instance!!
        }

        private fun buildInstance(context: Context) {
            instance = Room.databaseBuilder(context, AndroidLabsDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
