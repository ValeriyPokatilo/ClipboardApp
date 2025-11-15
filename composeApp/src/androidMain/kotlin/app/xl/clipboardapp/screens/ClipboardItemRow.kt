package app.xl.clipboardapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.xl.clipboardapp.domain.ClipboardItem

@Composable
fun ClipboardItemRow(
    item: ClipboardItem
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = item.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.value,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
    }
}