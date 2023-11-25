package ru.home.mysecrets.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.home.data.repository.AuthorizationRepositoryImpl
import ru.home.data.storage.network.header.TokenManager
import ru.home.data.storage.network.header.TokenManagerImpl
import ru.home.domain.repository.AuthorizationRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
//    @Binds
//    abstract fun bindTokenRepository(
//        tokenRepositoryImpl: TokenRepositoryImpl
//    ): TokenRepository

    @Binds
    abstract fun bindTokenManager(
        tokenManager: TokenManagerImpl,
    ): TokenManager

    @Binds
    abstract fun bindAuthRepository(
        authorizationRepositoryImpl: AuthorizationRepositoryImpl,
    ): AuthorizationRepository

//    @Binds
//    abstract fun bindRequestRepository(
//        repositoryImpl: RequestRepositoryImpl
//    ): RequestRepository
}
