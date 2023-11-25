package ru.home.mysecrets.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.home.data.storage.network.AuthorisationApi
import ru.home.data.storage.network.RetrofitInstance
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): RetrofitInstance =
        RetrofitInstance()

//    @Provides
//    @Singleton
//    fun provideRequestService(retrofitInstance: RetrofitInstance): RequestApi =
//        RequestApi.create(retrofitInstance)

    @Provides
    @Singleton
    fun provideUserService(retrofitInstance: RetrofitInstance): AuthorisationApi =
        AuthorisationApi.create(retrofitInstance)
}
