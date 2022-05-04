package test.task.data.apiservice

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import test.task.data.model.Post

interface ApiService {
    @GET("posts")
    suspend fun getPost(): List<Post>

    @POST("posts")
    suspend fun createPost(
        @Body
        post: Post
    ): Post


    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        operator fun invoke(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(initOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            return logger
        }

        private val interceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        private fun initOkHttpClient(): OkHttpClient {
            val okhttp = OkHttpClient.Builder()
                .addNetworkInterceptor(provideLoggingInterceptor())
                .addInterceptor(interceptor)
            /*   .readTimeout(TIMEOUT_INTERVAL, TimeUnit.MINUTES)
               .connectTimeout(TIMEOUT_INTERVAL, TimeUnit.MINUTES)*/
            return okhttp.build()
        }
    }
}