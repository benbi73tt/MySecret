package ru.home.data.repository.storage.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("entry")
data class EntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: ByteArray,
    val date: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EntryEntity

        if (id != other.id) return false
        if (title != other.title) return false
        if (!description.contentEquals(other.description)) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + description.contentHashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}