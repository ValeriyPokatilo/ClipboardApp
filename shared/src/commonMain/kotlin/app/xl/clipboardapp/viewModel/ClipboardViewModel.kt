package app.xl.clipboardapp.viewModel

import app.xl.clipboardapp.data.TestRepositoryImpl
import app.xl.clipboardapp.domain.ClipboardItem
import app.xl.clipboardapp.domain.Repository
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClipboardViewModel(
    private val repository: Repository = TestRepositoryImpl
): ViewModel() {
    private val _records = MutableStateFlow<List<ClipboardItem>>(emptyList()).cMutableStateFlow()
    val record = _records.asStateFlow().cStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllRecords().collect { records ->
                _records.value = records
            }
        }
    }
}