package ru.home.mysecrets.entry


import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.crypto.tink.hybrid.HybridConfig
import dagger.hilt.android.AndroidEntryPoint
import ru.home.domain.models.request.EntryData
import ru.home.mysecrets.R
import ru.home.mysecrets.base.BaseScreenFragment
import ru.home.mysecrets.databinding.EntryFragmentBinding
import ru.home.mysecrets.encrypt.Cryptage
import ru.home.mysecrets.extensions.showToastLong
import ru.home.mysecrets.utils.ITEM_ENTRY_DESC
import ru.home.mysecrets.utils.ITEM_ENTRY_ID
import ru.home.mysecrets.utils.ITEM_ENTRY_TITLE
import ru.home.mysecrets.utils.PRIVATE_KEY
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
            HybridConfig.register()
            val privateKeysetHandle = PRIVATE_KEY
            val publicKeysetHandle = privateKeysetHandle.publicKeysetHandle

            decrypt.setOnClickListener {
                showEnterContextInfo { code ->
                    arguments?.getInt(ITEM_ENTRY_ID)?.let { id -> viewModel.decryptDescription(id) }
                    viewModel.decryptEntry.collectSafely {
                        val decrypt = Cryptage().decrypt(privateKeysetHandle, it, code)
                        description.setText(decrypt)
                    }
                }
            }

            imgClose.setOnClickListener {
                showAutoStartDialog {
                    findNavController().popBackStack()
                }
            }
            delete.setOnClickListener { findNavController().popBackStack() }
            imgDone.setOnClickListener {
                if (!title.text.isNullOrBlank() && !description.text.isNullOrBlank()) {
                    showEnterContextInfo { code ->
                        val encrypt = Cryptage().encrypt(
                            publicKeysetHandle,
                            description.text.toString(),
                            code
                        )
                        viewModel.saveEntry(
                            EntryData(
                                id = arguments?.getInt(ITEM_ENTRY_ID) ?: 0,
                                title = title.text.toString(),
                                desc = encrypt!!,
                                date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                                    .format(Date(System.currentTimeMillis()))
                            )
                        )
                        findNavController().popBackStack()
                    }
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


    /**
     * Отобразить диалог с сообщением
     */
    //TODO Вынести в отдельный класс Utits
    private fun showEnterContextInfo(invoke: (String) -> Unit) {
        val layoutInflater = LayoutInflater.from(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)

        val inputField = dialogView.findViewById<EditText>(R.id.input_field)
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.context_info))
            setMessage(getString(R.string.description_context_info))
            setView(dialogView)
            setPositiveButton(getString(R.string.confirm)) { dialog, _ ->
                invoke(inputField.text.toString())
                dialog.cancel()
            }
            setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
    }
}
