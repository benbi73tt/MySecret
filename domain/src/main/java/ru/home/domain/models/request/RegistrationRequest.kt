package ru.home.domain.models.request

data class RegistrationRequest(
    val login: String,
    val password: String,
    val isDoc: Boolean = false,
)
