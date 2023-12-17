package ru.home.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.home.domain.models.request.EntryData


/**
 * Репозиторий базы данных
 */
interface DbRepository {

    /**
     * Регистрация нового пользователя
     */
    suspend fun saveEntry(entryData: EntryData)

    /**
     * Получить список записей
     */
    fun getListEntry(): Flow<List<EntryData>?>

    /**
     * Получить запись по ID
     */
    fun getEntry(entryId: Int): EntryData
}
