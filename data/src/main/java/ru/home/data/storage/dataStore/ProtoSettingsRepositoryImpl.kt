package ru.home.data.storage.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import ru.home.data.ProtoSettings
import ru.home.data.storage.model.response.AuthResponseData

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"

class ProtoSettingsRepositoryImpl(private val context: Context) : ProtoSettingsRepository {
    companion object {
        private val Context.protoDataStore: DataStore<ProtoSettings> by dataStore(
            fileName = DATA_STORE_FILE_NAME,
            serializer = ProtoSettingsSerializer
        )
    }

    override suspend fun updateData(authResponse: AuthResponseData) =
        context.protoDataStore.updateData {
            it.toBuilder()
                .setAccessToken(authResponse.access)
                .setRefreshToken(authResponse.refresh)
                .build()
        }
}