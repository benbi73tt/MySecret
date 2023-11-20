package ru.home.domain.repository

import ru.home.domain.domain.core.RemoteWrapper
import ru.home.domain.models.request.RegistrationRequest
import ru.home.domain.models.response.RegistrationResponse

/**
 * Репозиторий аутентификации
 */
interface AuthorizationRepository {

//    /**
//     * Авторизация пользователя
//     */
//    fun signIn(authRequest: AuthRequest): RemoteWrapper<AuthResponse>

    /**
     * Регистрация нового пользователя
     */
    fun signUp(registrationRequest: RegistrationRequest): RemoteWrapper<RegistrationResponse>

//    suspend fun sendAgreement(): Flow<String>
}
