package ru.home.domain.use_case

import ru.home.domain.repository.AuthorizationRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthorizationRepository
) {
    operator fun invoke() = repository.getRefreshToken()
}
