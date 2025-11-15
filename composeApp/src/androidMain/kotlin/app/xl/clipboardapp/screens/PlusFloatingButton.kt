package app.xl.clipboardapp.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.xl.clipboardapp.R

@Composable
fun PlusFloatingButton(
    onClick: () -> Unit
) {
    return FloatingActionButton(
        modifier = Modifier
            .height(64.dp)
            .width(64.dp),
        onClick = {
            onClick()
        },
        contentColor = Color.White,
        containerColor = Color.Blue,
        shape = CircleShape
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_add_note),
            contentDescription = "Add button"
        )
    }
}