package ru.home.mysecrets.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.home.mysecrets.R
import ru.home.mysecrets.databinding.FragmentFirstBinding
import ru.home.mysecrets.models.EntryData
import ru.home.mysecrets.utils.ITEM_ENTRY
import ru.home.mysecrets.utils.NavigationUtils.navigateSafe

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {
    private val binding by viewBinding(FragmentFirstBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            buttonFirst.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

            fab.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_AntyFragment)
            }


            val entries = List(20) { index ->
                EntryData("Title $index", "Description $index")
            }

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            HomeAdapter {
                navigateSafe(
                    findNavController(),
                    R.id.action_FirstFragment_to_AntyFragment,
                    bundleOf(ITEM_ENTRY to it.title)
                )
            }.apply {
                recyclerView.adapter = this
                recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
                this.submitList(entries)
            }
        }
    }
}