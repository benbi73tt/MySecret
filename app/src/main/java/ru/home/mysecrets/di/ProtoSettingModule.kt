package ru.home.mysecrets.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.home.data.storage.dataStore.ProtoSettingsRepository
import ru.home.data.storage.dataStore.ProtoSettingsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProtoSettingModule {
    @Singleton
    @Provides
    fun provideProtoSettingRepository(
        @ApplicationContext context: Context
    ): ProtoSettingsRepository =
        ProtoSettingsRepositoryImpl(context)
}
