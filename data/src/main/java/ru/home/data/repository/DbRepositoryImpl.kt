package ru.home.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.home.data.repository.base.BaseRepository
import ru.home.data.repository.storage.db.dao.EntryDao
import ru.home.data.storage.model.toEntryEntity
import ru.home.data.storage.model.toListEntryData
import ru.home.domain.models.request.EntryData
import ru.home.domain.repository.DbRepository
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(
    private val entryDao: EntryDao
) : BaseRepository(), DbRepository {
    override suspend fun saveEntry(entryData: EntryData) {
        entryDao.insertEntry(entryData.toEntryEntity())
    }

    override fun getListEntry(): Flow<List<EntryData>?> =
        entryDao.getListEntry()
            .map {
                Log.d("!@#","repo = $it")
                it?.toListEntryData()
            }

}