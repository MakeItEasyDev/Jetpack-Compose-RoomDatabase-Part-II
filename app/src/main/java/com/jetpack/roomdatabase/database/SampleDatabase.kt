package com.jetpack.roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jetpack.roomdatabase.dao.SampleDao
import com.jetpack.roomdatabase.entity.SampleEntity

@Database(entities = [SampleEntity::class], version = 1, exportSchema = false)
abstract class SampleDatabase: RoomDatabase() {
    abstract fun sampleDao(): SampleDao

    companion object {
        @Volatile
        private var INSTANCE: SampleDatabase? = null

        fun getInstance(context: Context): SampleDatabase {
            val sampleInstance = INSTANCE
            if (sampleInstance != null) {
                return sampleInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SampleDatabase::class.java,
                    "sample_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}