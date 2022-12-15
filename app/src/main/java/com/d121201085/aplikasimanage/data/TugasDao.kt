package com.d121201085.aplikasimanage.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.d121201085.aplikasimanage.model.Tugas

@Dao
interface TugasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTugas(tugas: Tugas)

    @Query(value = "SELECT * FROM table_tugas ORDER BY id ASC" )
    fun observeTasks(): LiveData<List<Tugas>>

    @Update
    suspend fun updateTask(tugas: Tugas)

    @Delete
    suspend fun deleteTask(tugas: Tugas)

    @Query("DELETE FROM table_tugas")
    suspend fun deleteAllTasks()
}