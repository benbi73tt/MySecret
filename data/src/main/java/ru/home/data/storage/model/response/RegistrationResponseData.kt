package ru.home.data.storage.model.response

import ru.home.data.utils.DataMapper
import ru.home.domain.models.response.RegistrationResponse

/**
 * Данные ответа регистрации
 */
data class RegistrationResponseData(
    val email: String,
    val username: String,
) : DataMapper<RegistrationResponse> {
    override fun mapToDomain(): RegistrationResponse =
        RegistrationResponse(this.email, this.username)
}
