package com.d121201085.aplikasimanage.repository

import androidx.lifecycle.LiveData
import com.d121201085.aplikasimanage.data.TugasDao
import com.d121201085.aplikasimanage.model.Tugas


class TugasRepository (private val tugasDao: TugasDao) {
    val observeTasks: LiveData<List<Tugas>> = tugasDao.observeTasks()

    suspend fun insertTask(tugas: Tugas) {
        tugasDao.addTugas(tugas)
    }

    suspend fun updateTask(task: Tugas) {
        tugasDao.updateTask(task)
    }

    suspend fun deleteTask(task: Tugas) {
        tugasDao.deleteTask(task)
    }

    suspend fun deleteAllTasks() {
        tugasDao.deleteAllTasks()
    }
}