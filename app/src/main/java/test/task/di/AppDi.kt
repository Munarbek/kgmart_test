package test.task.di

import androidx.lifecycle.ViewModelProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import test.task.data.apiservice.ApiService
import test.task.data.repository.Repository
import test.task.ui.create.CreateViewModel
import test.task.ui.main.MainViewModel

val module = module {
    single<ApiService> { ApiService() }
    single<Repository> { Repository(get()) }
    viewModel { CreateViewModel(get()) }
    viewModel { MainViewModel(get()) }
}