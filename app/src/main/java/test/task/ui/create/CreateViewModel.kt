package test.task.ui.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.task.data.repository.Repository
import test.task.utils.NetworkResponse

class CreateViewModel(private val repository: Repository):ViewModel() {
    private  val _created  = MutableLiveData<Boolean>()
    val create get() = _created

    fun createPost(title: String, desc: String) = viewModelScope.launch {
        when (val repository = repository.createPost(title,desc)){
            is NetworkResponse.Success ->{
                _created.postValue(true)
            }
            is NetworkResponse.Failure->{
                _created.postValue(false)
            }
        }
    }
}