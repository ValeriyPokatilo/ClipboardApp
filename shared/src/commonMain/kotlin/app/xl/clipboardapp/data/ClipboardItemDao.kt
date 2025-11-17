package app.xl.clipboardapp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.xl.clipboardapp.domain.ClipboardItem
import com.example.AppDatabase
import com.example.DbItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class ClipboardItemDao(
    private val db: AppDatabase,
    private val coroutineContext: CoroutineContext
) {
    private val itemsQuery = db.clipboardQueries

    fun getAllItem(): Flow<List<ClipboardItem>> {
        return itemsQuery
            .selectAll()
            .asFlow()
            .mapToList(coroutineContext)
            .map { it.toEntities() }
    }

    suspend fun getItem(id: Int): DbItem? {
        return itemsQuery.selectById(id.toLong()).executeAsOneOrNull()
    }

    suspend fun addItem(title: String, value: String) {
        itemsQuery.insertItem(title, value)
    }

    suspend fun deleteItem(id: Int) {
        itemsQuery.deleteById(id.toLong())
    }

    suspend fun editItem(item: ClipboardItem) {
        itemsQuery.editItem(item.title, item.value, item.id.toLong())
    }
}