package test.task.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.task.data.model.Post
import test.task.data.repository.Repository
import test.task.utils.NetworkResponse
import java.lang.Exception

class MainViewModel(private val repository: Repository) : ViewModel() {
    val post = MutableLiveData<List<Post>>()
    val errorPost = MutableLiveData<String>()
    fun getPosts() = viewModelScope.launch {
        when (val response = repository.getPost()) {
            is NetworkResponse.Success -> {
                post.postValue(response.data)
            }
            is NetworkResponse.Failure -> {
                errorPost.postValue(response.message)
            }
        }
    }
}