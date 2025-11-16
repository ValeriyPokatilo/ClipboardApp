package app.xl.clipboardapp.screens.view

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.descriptors.PrimitiveKind

@Composable
fun DetailsButton(
    modifier: Modifier = Modifier,
    title: String,
    color: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = color.copy(alpha = 0.3f),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = title)
    }
}