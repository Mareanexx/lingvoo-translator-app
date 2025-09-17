package ru.mareanexx.feature_translate.presentation.screen.favorites.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.SharedFlow
import ru.mareanexx.core.utils.clipboard.copyToClipboard
import ru.mareanexx.feature_translate.R
import ru.mareanexx.feature_translate.presentation.components.CustomAlertDialog
import ru.mareanexx.feature_translate.presentation.screen.favorites.viewmodel.event.SecondaryScreenEvent

@Composable
fun EventHandler(
    eventFlow: SharedFlow<SecondaryScreenEvent>,
    @StringRes title: Int,
    @StringRes mainText: Int,
    onDeleteClicked: () -> Unit
) {
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            when(event) {
                is SecondaryScreenEvent.CopyToClipboard -> {
                    val isSuccessful = context.copyToClipboard(event.text)
                    if (isSuccessful && Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
                        Toast.makeText(context, context.getText(R.string.txt_toast_copied_to_clipboard), Toast.LENGTH_SHORT).show()
                    }
                }
                SecondaryScreenEvent.ShowDeleteDialog -> { showDialog.value = true }
                is SecondaryScreenEvent.ShowToast -> {
                    Toast.makeText(context, context.getText(R.string.txt_toast_unknown_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    if (showDialog.value) {
        CustomAlertDialog(
            title = title,
            mainText = mainText,
            topIcon = R.drawable.warning_icon,
            onDismissRequest = { showDialog.value = false },
            onConfirmClicked = onDeleteClicked
        )
    }
}