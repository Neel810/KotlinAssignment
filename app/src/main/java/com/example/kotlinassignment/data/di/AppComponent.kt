package com.example.kotlinassignment.data.di


import com.example.kotlinassignment.data.api.RetrofitInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(retrofitInstance: RetrofitInstance)
}