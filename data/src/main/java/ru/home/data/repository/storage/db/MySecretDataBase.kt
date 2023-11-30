package ru.home.data.repository.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.home.data.repository.storage.db.dao.EntryDao
import ru.home.data.repository.storage.db.entity.EntryEntity

/**
 * База данных приложения
 */
@Database(
    entities = [
        EntryEntity::class,
               ],
    version = 1,
    exportSchema = false
)

abstract class MySecretDataBase : RoomDatabase() {
    abstract fun getEntryDao(): EntryDao
}