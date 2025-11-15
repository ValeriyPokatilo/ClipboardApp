package app.xl.clipboardapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.clipboardapp.data.TestRepositoryImpl
import app.xl.clipboardapp.viewModel.ClipboardViewModel

@Composable
@Preview
fun ClipboardScreen(
    viewModel: ClipboardViewModel = viewModel {
        ClipboardViewModel(repository = TestRepositoryImpl)
    }
) {
    val state = viewModel.items.collectAsState()

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(state.value) {
                ClipboardItemRow(item = it)
            }
        }
    }
}