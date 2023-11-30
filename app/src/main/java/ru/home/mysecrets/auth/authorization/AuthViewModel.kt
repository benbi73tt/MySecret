package ru.home.mysecrets.auth.authorization

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import ru.home.domain.models.request.AuthRequest
import ru.home.domain.use_case.auth.SignInUseCase
import ru.home.mysecrets.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    private val _signInState = MutableUIStateFlow<String>()
    val signInState = _signInState.asStateFlow()

    fun signIn(authRequest: AuthRequest) {
        signInUseCase(authRequest).collectNetworkRequest(_signInState) { it.status }
    }

    fun resetState() {
        _signInState.reset()
    }
}