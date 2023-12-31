package ru.home.mysecrets.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.home.domain.models.request.EntryData
import ru.home.mysecrets.databinding.ItemEntryBinding

class HomeAdapter (
    private val clickList: (EntryData) -> Unit,
) : ListAdapter<EntryData, HomeAdapter.ScreenTimeViewHolder>(HomeDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenTimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEntryBinding.inflate(layoutInflater, parent, false)
        return ScreenTimeViewHolder(
            binding,
            onClick = { position -> clickList.invoke(getItem(position)) }
        )
    }

    override fun onBindViewHolder(holder: ScreenTimeViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ScreenTimeViewHolder(
        private val binding: ItemEntryBinding,
        onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onClick(adapterPosition)
                }
            }
        }

        fun bind(item: EntryData) {
            with(binding) {
                title.text = item.title
                desc.text = String(item.desc, Charsets.UTF_8)
                dateTime.text = item.date
            }
        }
    }
}