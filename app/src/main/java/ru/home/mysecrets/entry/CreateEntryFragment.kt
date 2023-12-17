package ru.home.mysecrets.entry


import android.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.home.domain.models.request.EntryData
import ru.home.mysecrets.R
import ru.home.mysecrets.base.BaseScreenFragment
import ru.home.mysecrets.databinding.EntryFragmentBinding
import ru.home.mysecrets.extensions.showToastLong
import ru.home.mysecrets.utils.ITEM_ENTRY_DESC
import ru.home.mysecrets.utils.ITEM_ENTRY_ID
import ru.home.mysecrets.utils.ITEM_ENTRY_TITLE
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class CreateEntryFragment :
    BaseScreenFragment<CreateEntryViewModel, EntryFragmentBinding>(R.layout.entry_fragment) {
    override val binding by viewBinding(EntryFragmentBinding::bind)
    override val viewModel: CreateEntryViewModel by viewModels()


    override fun setupListeners() {
        viewModel.saveEntry.collectUIState(
            onSuccess = {
                showToastLong("Успешно создана запись $it")
            },
            onError = {
                showToastLong("Ошибка при создании записи")
            }
        )
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
        with(binding) {

            appBar.topAppBar.setupWithNavController(findNavController())
            arguments?.let {
                binding.title.setText(it.getString(ITEM_ENTRY_TITLE))
                binding.description.setText(it.getString(ITEM_ENTRY_DESC))
            }

            imgClose.setOnClickListener {
                showAutoStartDialog {
                    findNavController().popBackStack()
                }
            }
            delete.setOnClickListener { findNavController().popBackStack() }
            imgDone.setOnClickListener {
                if (!title.text.isNullOrBlank() && !description.text.isNullOrBlank()) {
                    viewModel.saveEntry(
                        EntryData(
                            id = arguments?.getInt(ITEM_ENTRY_ID) ?: 0,
                            title = title.text.toString(),
                            desc = description.text.toString(),
                            date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                                .format(Date(System.currentTimeMillis()))
                        )
                    )
//                    Cryptage().main()
                    findNavController().popBackStack()
                } else {
                    showToastLong(R.string.emty_title_or_desc)
                }
            }
        }
    }

    /**
     * Отобразить диалог с сообщением
     */
    //TODO Вынести в отдельный класс Utits
    private fun showAutoStartDialog(invoke: () -> Unit) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.exit))
            setMessage(getString(R.string.exit_without_save))
            setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                invoke()
                dialog.cancel()
            }
            setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
    }
}
