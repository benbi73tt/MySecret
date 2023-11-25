package ru.home.data.storage.model.request

/**
 * Данные запроса регистрации
 */
data class RegistrationRequestData(
    private val username: String,
    private val password: String,
)