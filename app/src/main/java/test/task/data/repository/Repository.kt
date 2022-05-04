package test.task.data.repository

import test.task.data.apiservice.ApiService
import test.task.data.model.Post

class Repository (private val apiService: ApiService):BaseRepository(){
    suspend fun getPost() = saveApiCall {
        apiService.getPost()
    }
    suspend fun createPost(title: String, desc: String) = saveApiCall {
        apiService.createPost(Post(userId = 1,title = title,body = desc,id = 12))
    }

}