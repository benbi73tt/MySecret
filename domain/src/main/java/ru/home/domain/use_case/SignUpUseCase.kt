package ru.home.domain.use_case

import ru.home.domain.models.request.RegistrationRequest
import ru.home.domain.repository.AuthorizationRepository
import javax.inject.Inject

/**
 * Регистрация пользователя
 */
class SignUpUseCase @Inject constructor(
    private val repository: AuthorizationRepository
) {
    operator fun invoke(registrationRequest: RegistrationRequest) =
        repository.signUp(registrationRequest)
}