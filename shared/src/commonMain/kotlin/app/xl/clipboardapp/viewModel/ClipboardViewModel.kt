package app.xl.clipboardapp.viewModel

import app.xl.clipboardapp.data.ClipboardItemDao
import app.xl.clipboardapp.data.DatabaseDriverFactory
import app.xl.clipboardapp.data.SqlRepositoryImpl
import app.xl.clipboardapp.data.TestRepositoryImpl
import app.xl.clipboardapp.domain.ClipboardItem
import app.xl.clipboardapp.domain.Repository
import app.xl.clipboardapp.platform.ClipboardManager
import com.example.AppDatabase
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClipboardViewModel(
    private val databaseDriverFactory: DatabaseDriverFactory
): ViewModel() {

    private val coroutineScope = viewModelScope
    private val database: AppDatabase by lazy {
        AppDatabase(databaseDriverFactory.createDriver())
    }
    private val dao: ClipboardItemDao by lazy {
        ClipboardItemDao(database, coroutineScope.coroutineContext)
    }

    private val repository: Repository = SqlRepositoryImpl.getInstance(dao)

    private val _items = MutableStateFlow<List<ClipboardItem>>(emptyList()).cMutableStateFlow()
    val items = _items.asStateFlow().cStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllItems().collect { records ->
                _items.value = records
            }
        }
    }

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            repository.deleteClipboardItem(id)
        }
    }

    fun doCopyToClipboard(text: String) {
        val clipboardManager = ClipboardManager()
        clipboardManager.copyText(text)
    }
}