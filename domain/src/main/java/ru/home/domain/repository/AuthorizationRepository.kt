package ru.home.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.home.domain.domain.core.RemoteWrapper
import ru.home.domain.models.request.AuthRequest
import ru.home.domain.models.request.RegistrationRequest
import ru.home.domain.models.response.AuthResponse
import ru.home.domain.models.response.RegistrationResponse

/**
 * Репозиторий аутентификации
 */
interface AuthorizationRepository {

    /**
     * Авторизация пользователя
     */
    fun signIn(authRequest: AuthRequest): RemoteWrapper<AuthResponse>

    /**
     * Регистрация нового пользователя
     */
    fun signUp(registrationRequest: RegistrationRequest): RemoteWrapper<RegistrationResponse>

    /**
     * Получить refresh token
     */
    fun getRefreshToken(): Flow<String>

}
