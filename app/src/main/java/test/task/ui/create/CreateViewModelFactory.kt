package test.task.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.task.data.repository.Repository
import test.task.ui.main.MainViewModel

class CreateViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateViewModel(repository) as T
    }
}