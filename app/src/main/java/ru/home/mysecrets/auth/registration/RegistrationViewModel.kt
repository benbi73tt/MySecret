package ru.home.mysecrets.auth.registration

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import ru.home.domain.models.request.RegistrationRequest
import ru.home.domain.use_case.SignUpUseCase
import ru.home.mysecrets.base.BaseViewModel
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel() {


    private val _signUpState = MutableUIStateFlow<String>()
    val signUpState = _signUpState.asStateFlow()

    fun signUp(regRequest: RegistrationRequest) {
        signUpUseCase(regRequest).collectNetworkRequest(_signUpState) { it.email }
    }
}