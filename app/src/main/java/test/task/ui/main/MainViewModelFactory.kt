package test.task.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.task.data.repository.Repository

class MainViewModelFactory (
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}