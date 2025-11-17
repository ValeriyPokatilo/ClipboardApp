package app.xl.clipboardapp.viewModel

import app.xl.clipboardapp.data.ClipboardItemDao
import app.xl.clipboardapp.data.DatabaseDriverFactory
import app.xl.clipboardapp.data.SqlRepositoryImpl
import app.xl.clipboardapp.data.TestRepositoryImpl
import app.xl.clipboardapp.domain.ClipboardItem
import app.xl.clipboardapp.domain.Repository
import com.example.AppDatabase
import com.example.AppDatabase.Companion.invoke
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    id: String?,
    private val databaseDriverFactory: DatabaseDriverFactory
): ViewModel() {
    val coroutineScope = viewModelScope

    private val database: AppDatabase by lazy {
        AppDatabase(databaseDriverFactory.createDriver())
    }

    private val dao: ClipboardItemDao by lazy {
        ClipboardItemDao(database, coroutineScope.coroutineContext)
    }
    private val repository: Repository = SqlRepositoryImpl.getInstance(dao)
    private var currentItem: ClipboardItem? = null
    val title: CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
    val value: CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()

    init {
       loadItem(id)
    }

    fun loadItem(id: String?) {
        val parsedId = id?.toIntOrNull() ?: return

        viewModelScope.launch {
            val item = repository.getItem(parsedId)
            if (item != null) {
                currentItem = item
                title.value = item.title
                value.value = item.value
            }
        }
    }

    fun saveItem() {
        viewModelScope.launch {
            val existing = currentItem

            if (existing == null) {
                repository.addClipboardItem(title.value, value.value)
            } else {
                val updated = existing.copy(
                    title = title.value,
                    value = value.value
                )
                repository.editClipboardItem(updated)
                currentItem = updated
            }
        }
    }
}