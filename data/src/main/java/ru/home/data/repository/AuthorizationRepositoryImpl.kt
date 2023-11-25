package ru.home.data.repository

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import ru.home.domain.models.request.RegistrationRequest
import ru.home.domain.repository.AuthorizationRepository
import ru.home.data.repository.base.BaseRepository
import ru.home.data.storage.network.AuthorisationApi
import ru.home.data.storage.network.header.TokenManager
import ru.home.data.utils.toRegistrationRequestData
import ru.home.domain.models.request.AuthRequest
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val authorizationApi: AuthorisationApi,
    private val tokenManager: TokenManager,
) : BaseRepository(), AuthorizationRepository {

    override fun signIn(authRequest: AuthRequest) =
        doNetworkRequestWithMapping {
            val login = authRequest.login
                .toRequestBody("text/plain".toMediaTypeOrNull())

            val password = authRequest.password
                .toRequestBody("text/plain".toMediaTypeOrNull())

            authorizationApi.signIn(login, password)
                .onSuccess {
                    //сохраняем токены полученные при авторизации
                    tokenManager.saveToken(it)
                }
        }

    override fun signUp(registrationRequest: RegistrationRequest) =
        doNetworkRequestWithMapping {
            authorizationApi.signUp(registrationRequest.toRegistrationRequestData())
        }

//    override suspend fun sendAgreement(): Flow<String> =
//
//        flow {
//            val res = authorizationApi.sendAgree(AGREEMENT_TERMS).code().toString()
//            Log.d("tagg", "sendAgree response $res")
//            emit(res)
//        }.catch {
//            Log.d("tagg", "sendAgree error $it") }
}
