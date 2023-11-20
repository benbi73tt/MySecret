package ru.home.data.repository.storage.model

/**
 * Модель ошибки с сервера. Запрещено изменять названия полей
 */
data class ErrorResponse(
    val error_message: String,
    val details: String?,
    val message: String?,
    val status: Int
)
