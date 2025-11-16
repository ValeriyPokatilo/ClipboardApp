package app.xl.clipboardapp.data

import app.xl.clipboardapp.domain.ClipboardItem
import app.xl.clipboardapp.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object TestRepositoryImpl: Repository {

    private val itemListFlow = MutableStateFlow<List<ClipboardItem>>(listOf())

    init {
        initTestRecords()
    }

    override fun getAllItems(): Flow<List<ClipboardItem>> {
        return itemListFlow.asStateFlow()
    }

    override suspend fun getItem(id: Int): ClipboardItem? {
        return itemListFlow
            .value
            .firstOrNull { it.id == id }
    }

    override suspend fun addClipboardItem(title: String, value: String) {
        itemListFlow.update { oldList ->
            val newItem = ClipboardItem(
                id = oldList.size,
                title = title,
                value = value
            )
            oldList + newItem
        }
    }

    override suspend fun deleteClipboardItem(id: Int) {
        itemListFlow.update { oldList ->
            oldList.filterNot { it.id == id }
        }
    }

    override suspend fun editClipboardItem(item: ClipboardItem) {
        itemListFlow.update { oldList ->
            oldList.map { existing ->
                if (existing.id == item.id) item else existing
            }
        }
    }

    private fun initTestRecords() {
        val testItems = listOf(
            ClipboardItem(0, "mail.ru", "sample@mail.ru"),
            ClipboardItem(1, "google", "test@gmail.com"),
            ClipboardItem(2, "Yandex", "noreply@yandex.ru"),
            ClipboardItem(3, "Holder name", "TED LASSO"),
            ClipboardItem(4, "Pin code", "7814"),
            ClipboardItem(5, "Code phrase", "blablabla"),
            ClipboardItem(6, "Foo", "Bar"),
        )

        itemListFlow.value = testItems
    }
}