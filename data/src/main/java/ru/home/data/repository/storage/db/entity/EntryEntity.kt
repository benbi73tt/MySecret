package ru.home.data.repository.storage.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("entry")
data class EntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
)