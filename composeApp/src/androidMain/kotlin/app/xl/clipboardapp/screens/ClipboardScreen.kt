package app.xl.clipboardapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.clipboardapp.screens.view.ClipboardItemRow
import app.xl.clipboardapp.screens.view.PlusFloatingButton
import app.xl.clipboardapp.viewModel.ClipboardViewModel

@Composable
fun ClipboardScreen(
    viewModel: ClipboardViewModel = viewModel(),
    navigateToDetails: (Int?) -> Unit
) {
    val state = viewModel.items.collectAsState()

    Scaffold(
        floatingActionButton = {
            PlusFloatingButton(onClick = {
                navigateToDetails(null)
            })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            items(state.value) { item ->
                ClipboardItemRow(
                    modifier = Modifier.clickable {
                        viewModel.doCopyToClipboard(item.value)
                    },
                    item = item
                )
            }
        }
    }
}