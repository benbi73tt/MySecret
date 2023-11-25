package ru.home.data.storage.network.header

import kotlinx.coroutines.flow.map
import ru.home.data.storage.dataStore.ProtoSettingsRepository
import ru.home.data.storage.model.response.AuthResponseData
import javax.inject.Inject

class TokenManagerImpl @Inject constructor(
    private val protoSettingsRepository: ProtoSettingsRepository,
) : TokenManager {
    override suspend fun saveToken(authResponse: AuthResponseData) =
        protoSettingsRepository.updateData(authResponse)


    override fun getRefreshToken() =
        protoSettingsRepository.setting.map { it.accessToken }

}
