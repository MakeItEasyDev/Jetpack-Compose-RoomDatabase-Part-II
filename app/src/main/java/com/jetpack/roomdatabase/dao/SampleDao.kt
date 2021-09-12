package com.jetpack.roomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jetpack.roomdatabase.entity.SampleEntity

@Dao
interface SampleDao {
    @Query("SELECT * FROM SampleData")
    fun getAllData(): LiveData<List<SampleEntity>>

    @Query("SELECT * FROM SampleData WHERE id = :id")
    fun getById(id: Int): SampleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE) //conflict the data
    suspend fun insert(item: List<SampleEntity>)

    @Update
    suspend fun update(item: SampleEntity)

    @Delete
    suspend fun delete(item: SampleEntity)

    @Query("DELETE FROM SampleData")
    suspend fun deleteAllRecord()
}