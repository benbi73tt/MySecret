package ru.home.domain.models.request

data class AuthRequest(
    val login: String,
    val password: String,
)
