package ru.home.data.storage.dataStore

import ru.home.data.ProtoSettings
import ru.home.data.storage.model.response.AuthResponseData

interface ProtoSettingsRepository {

    /**
     * Обновить данные авторизации
     */
    suspend fun updateData(authResponse: AuthResponseData): ProtoSettings
}