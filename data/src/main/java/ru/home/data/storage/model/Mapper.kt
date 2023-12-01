package ru.home.data.storage.model

import ru.home.data.repository.storage.db.entity.EntryEntity
import ru.home.domain.models.request.EntryData


fun EntryData.toEntryEntity(): EntryEntity =
    EntryEntity(this.id, this.title, this.desc, this.date)

fun List<EntryEntity>.toListEntryData(): List<EntryData> =
    this.map {
        EntryData(it.id, it.title, it.description, it.date)
    }
