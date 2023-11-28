package ru.home.mysecrets.entry


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.home.mysecrets.R
import ru.home.mysecrets.base.BaseScreenFragment
import ru.home.mysecrets.databinding.EntryFragmentBinding
import ru.home.mysecrets.utils.ITEM_ENTRY

@AndroidEntryPoint
class CreateEntryFragment :
    BaseScreenFragment<CreateEntryViewModel, EntryFragmentBinding>(R.layout.entry_fragment) {
    override val binding by viewBinding(EntryFragmentBinding::bind)
    override val viewModel: CreateEntryViewModel by viewModels()


    override fun setupSubscribers() {
        super.setupSubscribers()
        binding.appBar.topAppBar.setupWithNavController(findNavController())
        arguments?.let {
            Log.d("!@#", "${it.getString(ITEM_ENTRY)}")
        }
    }
}
