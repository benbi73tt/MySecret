package ru.home.data.utils

import ru.home.data.repository.storage.model.request.RegistrationRequestData
import ru.home.domain.models.request.RegistrationRequest

fun RegistrationRequest.toRegistrationRequestData() =
    RegistrationRequestData(username = this.login, password = this.password)
