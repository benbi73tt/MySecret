package ru.home.mysecrets.presentation.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.home.domain.use_case.AuthUseCase
import ru.home.mysecrets.base.BaseViewModel
import ru.home.mysecrets.extensions.launchIO
import ru.home.mysecrets.extensions.withMain
import javax.inject.Inject


@HiltViewModel
class TokenViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : BaseViewModel() {


    private val tokenMutable = MutableStateFlow("")
    val token = tokenMutable.asStateFlow()

    /**
     * Проверка авторизован ли пользователь
     */
    fun checkAuth(): Job = viewModelScope.launchIO(
        safeAction = {
            authUseCase().collect { token ->
                withMain {
                    tokenMutable.value = token
                }
            }
        },
        onError = { Log.d(this@TokenViewModel.javaClass.simpleName, it.message.toString()) }
    )
}