package ru.home.domain.use_case.auth

import ru.home.domain.models.request.AuthRequest
import ru.home.domain.repository.AuthorizationRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthorizationRepository
) {
    operator fun invoke(authRequest: AuthRequest) = repository.signIn(authRequest)
}