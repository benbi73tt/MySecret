package ru.home.data.storage.network.header

import ru.home.data.ProtoSettings
import ru.home.data.storage.model.response.AuthResponseData


/**
 *  Управление токенами доступа
 */
interface TokenManager {


    suspend fun saveToken(authResponse: AuthResponseData) : ProtoSettings
}