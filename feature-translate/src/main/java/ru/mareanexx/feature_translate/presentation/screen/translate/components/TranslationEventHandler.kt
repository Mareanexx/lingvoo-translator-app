package ru.mareanexx.feature_translate.presentation.screen.translate.components

import android.os.Build
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.SharedFlow
import ru.mareanexx.core.utils.clipboard.copyToClipboard
import ru.mareanexx.core.utils.common.ErrorType
import ru.mareanexx.feature_translate.R
import ru.mareanexx.feature_translate.presentation.components.CustomAlertDialog
import ru.mareanexx.feature_translate.presentation.components.RetryButton
import ru.mareanexx.feature_translate.presentation.screen.translate.viewmodel.event.TranslationEvent

@Composable
fun TranslatorEventHandler(
    eventFlow: SharedFlow<TranslationEvent>,
    onRetryClicked: () -> Unit
) {
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            when(event) {
                TranslationEvent.ShowNoConnectionDialog -> {
                    showDialog.value = true
                }
                is TranslationEvent.ShowErrorToast -> {
                    val textMessage = when(event.error.type) {
                        ErrorType.ServerError -> R.string.txt_toast_server_error
                        ErrorType.ClientError -> R.string.txt_toast_client_error
                        else -> R.string.txt_toast_unknown_error
                    }
                    Toast.makeText(context, context.getText(textMessage), Toast.LENGTH_SHORT).show()
                }
                TranslationEvent.ShowNotImplementedToast -> {
                    Toast.makeText(context, context.getText(R.string.feature_not_implemented_yet), Toast.LENGTH_SHORT).show()
                }
                is TranslationEvent.CopyToClipboard -> {
                    val isSuccessful = context.copyToClipboard(event.text)
                    if (isSuccessful && Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
                        Toast.makeText(context, context.getText(R.string.txt_toast_copied_to_clipboard), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    if (showDialog.value) {
        CustomAlertDialog(
            title = R.string.dialog_ttl_no_internet_connection,
            mainText = R.string.dialog_sub_no_internet_connection,
            topIcon = R.drawable.no_internet_connection_icon,
            onDismissRequest = { showDialog.value = false },
            confirmButton = { RetryButton(onRetryClicked = { onRetryClicked(); showDialog.value = false }) }
        )
    }
}