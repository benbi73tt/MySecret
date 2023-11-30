package ru.home.mysecrets.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.home.data.repository.storage.db.MySecretDataBase
import ru.home.data.repository.storage.db.dao.EntryDao
import ru.home.data.utils.DATA_BASE_NAME
import ru.home.mysecrets.data.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    //База данных
    @Singleton
    @Provides
    fun provideParentalControlDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context.applicationContext,
        MySecretDataBase::class.java,
        DATA_BASE_NAME
    )
        .apply {
            if (BuildConfig.DEBUG) {
                fallbackToDestructiveMigration()
            }
        }
        .build()

    @Singleton
    @Provides
    fun provideEntryDao(mySecretDataBase: MySecretDataBase): EntryDao =
        mySecretDataBase.getEntryDao()
}