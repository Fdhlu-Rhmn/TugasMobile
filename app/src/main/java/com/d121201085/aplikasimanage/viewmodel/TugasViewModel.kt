package com.d121201085.aplikasimanage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.d121201085.aplikasimanage.data.TugasDatabase
import com.d121201085.aplikasimanage.repository.TugasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.d121201085.aplikasimanage.model.Tugas

class TugasViewModel (application: Application):AndroidViewModel(application) {
    val observeTasks: LiveData<List<Tugas>>
    private val repository: TugasRepository

    init {
        val tugasDao = TugasDatabase.getDatabase(application).tugasDao()
        repository = TugasRepository(tugasDao)
        observeTasks = repository.observeTasks
    }
    fun addTugas(tugas: Tugas){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertTask(tugas)
        }
    }
    fun updateTask(tugas: Tugas) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(tugas)
        }
    }

    fun deleteTask(tugas: Tugas) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(tugas)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }
}