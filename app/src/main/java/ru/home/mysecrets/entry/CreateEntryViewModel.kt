package ru.home.mysecrets.entry

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.home.domain.models.request.EntryData
import ru.home.domain.use_case.home.EntryUseCase
import ru.home.mysecrets.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CreateEntryViewModel @Inject constructor(
    private val entryUseCase: EntryUseCase
) : BaseViewModel() {

    private var saveEntryMutable = MutableUIStateFlow<String>()
    val saveEntry = saveEntryMutable.asStateFlow()


    /**
     * Сохранение записи
     */
    fun saveEntry(entryData: EntryData) {
        viewModelScope.launch(Dispatchers.IO) {
            entryUseCase.invoke(entryData)
        }
    }
}