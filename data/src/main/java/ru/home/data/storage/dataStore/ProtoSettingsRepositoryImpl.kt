package ru.home.data.storage.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import ru.home.data.ProtoSettings
import ru.home.data.storage.model.response.AuthResponseData
import java.io.IOException

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"

class ProtoSettingsRepositoryImpl(private val context: Context) : ProtoSettingsRepository {
    companion object {
        private val Context.protoDataStore: DataStore<ProtoSettings> by dataStore(
            fileName = DATA_STORE_FILE_NAME,
            serializer = ProtoSettingsSerializer
        )
    }

    override val setting: Flow<ProtoSettings>
        get() = context.protoDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(ProtoSettings.getDefaultInstance())
                } else throw exception
            }

    override suspend fun updateData(authResponse: AuthResponseData) =
        context.protoDataStore.updateData {
            it.toBuilder()
                .setAccessToken(authResponse.access)
                .setRefreshToken(authResponse.refresh)
                .build()
        }
}