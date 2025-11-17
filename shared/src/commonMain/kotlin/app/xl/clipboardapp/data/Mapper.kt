package app.xl.clipboardapp.data

import app.xl.clipboardapp.domain.ClipboardItem
import com.example.DbItem

fun ClipboardItem.toDbModel(): DbItem {
    return DbItem(id.toLong(), title, value)
}

fun DbItem.toEntity(): ClipboardItem {
    return ClipboardItem(id.toInt(), title, value_)
}

fun List<DbItem>.toEntities(): List<ClipboardItem> {
    return this.map {
        it.toEntity()
    }
}