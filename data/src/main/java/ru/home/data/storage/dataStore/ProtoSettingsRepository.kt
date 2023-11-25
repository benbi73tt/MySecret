package ru.home.data.storage.dataStore

import kotlinx.coroutines.flow.Flow
import ru.home.data.ProtoSettings
import ru.home.data.storage.model.response.AuthResponseData

interface ProtoSettingsRepository {

    /**
     * Получить все настройки
     */
    val setting: Flow<ProtoSettings>

    /**
     * Обновить данные авторизации
     */
    suspend fun updateData(authResponse: AuthResponseData): ProtoSettings
}