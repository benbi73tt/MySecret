package ru.home.data.repository.storage.model.request

/**
 * Данные запроса регистрации
 */
data class RegistrationRequestData(
    private val username: String,
    private val password: String,
)