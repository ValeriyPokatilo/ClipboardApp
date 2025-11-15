package app.xl.clipboardapp.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllRecords(): Flow<List<ClipboardItem>>
    suspend fun addClipboardItem(title: String, value: String)
    suspend fun deleteClipboardItem(id: Int)
    suspend fun editClipboardItem(item: ClipboardItem)
}