package test.task.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import test.task.utils.NetworkResponse

abstract class BaseRepository {
    suspend fun <T> saveApiCall( apiCall: suspend () -> T): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResponse.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> NetworkResponse.Failure("HttpException", throwable)
                    else -> NetworkResponse.Failure("Error", throwable)

                }
            }
        }

    }
}
