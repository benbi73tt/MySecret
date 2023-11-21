package ru.home.domain.models.response

data class AuthResponse(
    val access: String = "",
    val refresh: String = "",
    val status: String = "",
)