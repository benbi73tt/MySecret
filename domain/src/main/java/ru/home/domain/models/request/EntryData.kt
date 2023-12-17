package ru.home.domain.models.request

data class EntryData(
    val id: Int = 0,
    val title: String = "",
    val desc: ByteArray = byteArrayOf(),
    val date: String = "",
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EntryData

        if (id != other.id) return false
        if (title != other.title) return false
        if (!desc.contentEquals(other.desc)) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + desc.contentHashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}