package ru.home.data.repository.storage.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.home.data.repository.storage.db.entity.EntryEntity

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: EntryEntity)

    @Query("SELECT * FROM entry")
    fun getListEntry(): Flow<List<EntryEntity>?>

}