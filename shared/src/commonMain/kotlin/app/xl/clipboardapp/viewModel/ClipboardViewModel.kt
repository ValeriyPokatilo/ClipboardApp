package app.xl.clipboardapp.viewModel

import app.xl.clipboardapp.data.TestRepositoryImpl
import app.xl.clipboardapp.domain.ClipboardItem
import app.xl.clipboardapp.domain.Repository
import app.xl.clipboardapp.platform.ClipboardManager
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClipboardViewModel(): ViewModel() {
    private val repository: Repository = TestRepositoryImpl
    private val _items = MutableStateFlow<List<ClipboardItem>>(emptyList()).cMutableStateFlow()
    val items = _items.asStateFlow().cStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllRecords().collect { records ->
                _items.value = records
            }
        }
    }

    fun doCopyToClipboard(text: String) {
        val clipboardManager = ClipboardManager()
        clipboardManager.copyText(text)
    }
}