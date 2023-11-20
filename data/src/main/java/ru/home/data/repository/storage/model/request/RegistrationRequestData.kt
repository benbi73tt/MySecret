package ru.home.data.repository.storage.model.request

/**
 * Данные запроса регистрации
 */
data class RegistrationRequestData(
    private val login: String,
    private val password: String,
    private val passwordRepeat: String = password,
    private val isDoc: Boolean = false,
)