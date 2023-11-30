package ru.home.mysecrets.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.home.data.repository.AuthorizationRepositoryImpl
import ru.home.data.repository.DbRepositoryImpl
import ru.home.data.storage.network.header.TokenManager
import ru.home.data.storage.network.header.TokenManagerImpl
import ru.home.domain.repository.AuthorizationRepository
import ru.home.domain.repository.DbRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindTokenManager(
        tokenManager: TokenManagerImpl,
    ): TokenManager

    @Binds
    abstract fun bindDbRepository(
        dbRepositoryImpl: DbRepositoryImpl,
    ): DbRepository


    @Binds
    abstract fun bindAuthRepository(
        authorizationRepositoryImpl: AuthorizationRepositoryImpl,
    ): AuthorizationRepository
}
