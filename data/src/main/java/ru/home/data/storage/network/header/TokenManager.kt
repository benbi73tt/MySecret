package ru.home.data.storage.network.header

import kotlinx.coroutines.flow.Flow
import ru.home.data.ProtoSettings
import ru.home.data.storage.model.response.AuthResponseData


/**
 *  Управление токенами доступа
 */
interface TokenManager {

    /**
     * Сохранить токены
     */
    suspend fun saveToken(authResponse: AuthResponseData) : ProtoSettings

    /**
     * Получить refresh token
     */
    fun getRefreshToken(): Flow<String>
}