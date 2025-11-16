package app.xl.clipboardapp.viewModel

import app.xl.clipboardapp.data.TestRepositoryImpl
import app.xl.clipboardapp.domain.ClipboardItem
import app.xl.clipboardapp.domain.Repository
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    id: String?
): ViewModel() {
    private val repository: Repository = TestRepositoryImpl
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

    fun saveItem(title: String, value: String) {
        viewModelScope.launch {
            val existing = currentItem

            if (existing == null) {
                repository.addClipboardItem(title, value)
            } else {
                val updated = existing.copy(
                    title = title,
                    value = value
                )
                repository.editClipboardItem(updated)
                currentItem = updated
            }
        }
    }
}