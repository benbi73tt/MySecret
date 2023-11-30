package ru.home.domain.use_case.home

import ru.home.domain.repository.DbRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: DbRepository
) {
    operator fun invoke() = repository.getListEntry()
}