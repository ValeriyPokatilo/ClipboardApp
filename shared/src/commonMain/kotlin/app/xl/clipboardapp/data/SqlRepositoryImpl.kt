package app.xl.clipboardapp.data

import app.xl.clipboardapp.domain.ClipboardItem
import app.xl.clipboardapp.domain.Repository
import kotlinx.coroutines.flow.Flow

class SqlRepositoryImpl private constructor(
    private val dao: ClipboardItemDao
) : Repository {
    override fun getAllItems(): Flow<List<ClipboardItem>> {
        return dao.getAllItem()
    }

    override suspend fun getItem(id: Int): ClipboardItem? {
        return dao.getItem(id)?.toEntity()
    }

    override suspend fun addClipboardItem(title: String, value: String) {
        dao.addItem(title, value)
    }

    override suspend fun deleteClipboardItem(id: Int) {
        dao.deleteItem(id)
    }

    override suspend fun editClipboardItem(item: ClipboardItem) {
        dao.editItem(item)
    }

    companion object {
        private var instance: SqlRepositoryImpl? = null

        fun getInstance(dao: ClipboardItemDao): SqlRepositoryImpl {
            return instance ?: SqlRepositoryImpl(dao).also { instance = it }
        }
    }
}