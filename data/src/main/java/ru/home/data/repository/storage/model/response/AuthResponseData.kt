package ru.home.data.repository.storage.model.response

import ru.home.data.utils.DataMapper
import ru.home.domain.models.response.AuthResponse

data class AuthResponseData(
    val access: String,
    val refresh: String,
    val status: String = "",
) : DataMapper<AuthResponse> {
    override fun mapToDomain(): AuthResponse =
        AuthResponse(this.access, this.refresh, this.status)
}