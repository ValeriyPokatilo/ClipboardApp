package app.xl.clipboardapp.viewModel

import app.xl.clipboardapp.data.TestRepositoryImpl
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
    val title: CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
    val value: CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()

    init {
        val parsedId = id?.toIntOrNull()

        if (parsedId != null) {
            viewModelScope.launch {
                val item = repository.getItem(parsedId)
                if (item != null) {
                    title.value = item.title
                    value.value = item.value
                }
            }
        }
    }

    fun saveItem(title: String, value: String) {
        viewModelScope.launch {
            repository.addClipboardItem(title, value)
        }
    }
}