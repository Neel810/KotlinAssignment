package com.example.kotlinassignment.data.di


import com.example.kotlinassignment.data.api.ListDataInterface
import com.example.kotlinassignment.data.api.RetrofitInstance
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class AppModule {

    /*Retrofit instance will be created and here and injected. Later we can use same instance anywhere in app*/

    @Provides
    fun providesContentPlayAPIInterface(): ListDataInterface{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ListDataInterface::class.java)
    }

    @Provides
    fun provideRetrofitInstance(): RetrofitInstance {
        return RetrofitInstance()
    }
}