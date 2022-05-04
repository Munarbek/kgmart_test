package test.task.data.model

import java.io.Serializable

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
):Serializable