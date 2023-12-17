package ru.home.domain.use_case.home

import ru.home.domain.models.request.EntryData
import ru.home.domain.repository.DbRepository
import javax.inject.Inject

class EntryUseCase @Inject constructor(
    private val repository: DbRepository,
) {

    suspend operator fun invoke(entryData: EntryData) {
        repository.saveEntry(entryData)
    }

    operator fun invoke(entryId: Int) =
        repository.getEntry(entryId)

}

