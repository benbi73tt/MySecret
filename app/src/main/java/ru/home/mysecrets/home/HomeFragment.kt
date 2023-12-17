package ru.home.mysecrets.home

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.home.domain.models.request.EntryData
import ru.home.mysecrets.R
import ru.home.mysecrets.base.BaseScreenFragment
import ru.home.mysecrets.databinding.FragmentFirstBinding
import ru.home.mysecrets.utils.ITEM_ENTRY_DESC
import ru.home.mysecrets.utils.ITEM_ENTRY_ID
import ru.home.mysecrets.utils.ITEM_ENTRY_TITLE
import ru.home.mysecrets.utils.NavigationUtils.navigateSafe

@AndroidEntryPoint
class HomeFragment :
    BaseScreenFragment<HomeViewModel, FragmentFirstBinding>(R.layout.fragment_first) {
    override val binding by viewBinding(FragmentFirstBinding::bind)
    override val viewModel: HomeViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.getListEntry()
    }

    override fun setupSubscribers() {
        with(binding) {

            buttonFirst.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

            fab.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_AntyFragment)
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.listEntry.collect {
                    startRecycler(it)
                }
            }
        }
    }

    private fun startRecycler(list: List<EntryData>) {
        with(binding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            HomeAdapter {
                navigateSafe(
                    findNavController(),
                    R.id.action_FirstFragment_to_AntyFragment,
                    bundleOf(
                        ITEM_ENTRY_TITLE to it.title,
                        ITEM_ENTRY_DESC to it.desc,
                        ITEM_ENTRY_ID to it.id,
                    )
                )
            }.apply {
                recyclerView.adapter = this
                recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
                this.submitList(list)
            }
        }
    }
}