package ru.home.mysecrets.home

import androidx.recyclerview.widget.DiffUtil
import ru.home.domain.models.request.EntryData


class HomeDiffCallback : DiffUtil.ItemCallback<EntryData>() {

    override fun areItemsTheSame(oldItem: EntryData, newItem: EntryData) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: EntryData, newItem: EntryData) =
        oldItem.title == newItem.title
}