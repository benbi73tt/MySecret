package ru.home.mysecrets.home


import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.home.domain.models.request.EntryData
import ru.home.domain.use_case.home.HomeUseCase
import ru.home.mysecrets.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private val listEntryMutable = MutableStateFlow<List<EntryData>>(emptyList())
    val listEntry = listEntryMutable.asStateFlow()

    fun getListEntry() {
        viewModelScope.launch {
            homeUseCase.invoke().collect {
                it?.let {
                    listEntryMutable.value = it
                }
            }
        }
    }
}