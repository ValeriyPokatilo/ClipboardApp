package app.xl.clipboardapp.screens

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.clipboardapp.data.DatabaseDriverFactory
import app.xl.clipboardapp.screens.view.DetailsButton
import app.xl.clipboardapp.viewModel.DetailsViewModel

@Composable
fun DetailsScreen(
    id: String?,
    popBackStack: () -> Unit,

) {
    val context = LocalContext.current.applicationContext
    val viewModel: DetailsViewModel = viewModel {
        DetailsViewModel(id = id, DatabaseDriverFactory(context))
    }

    val title by viewModel.title.collectAsStateWithLifecycle()
    val value by viewModel.value.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            label = {
                Text(
                    text = "Заголовок"
                )
            },
            onValueChange = {
                viewModel.title.value = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            label = {
                Text(
                    text = "Строка"
                )
            },
            onValueChange = {
                viewModel.value.value = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            DetailsButton(
                modifier = Modifier.weight(1f),
                title = "Cancel",
                color = Color.Red,
                onClick = { popBackStack() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            DetailsButton(
                modifier = Modifier.weight(1f),
                title = "Save",
                color = Color.Green,
                enabled = title.isNotBlank() && value.isNotBlank(),
                onClick = {
                    viewModel.saveItem()
                    popBackStack()
                }
            )
        }
    }
}