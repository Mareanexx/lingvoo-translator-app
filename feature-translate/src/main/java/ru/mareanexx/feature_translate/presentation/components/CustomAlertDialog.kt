package ru.mareanexx.feature_translate.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.R

@Composable
fun CustomAlertDialog(
    @StringRes title: Int,
    @StringRes mainText: Int,
    @DrawableRes topIcon: Int,
    onDismissRequest: () -> Unit,
    onConfirmClicked: () -> Unit = {},
    confirmButton: (@Composable () -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = stringResource(title), color = MaterialTheme.colorScheme.onSecondary) },
        text = {
            Text(
                text = stringResource(mainText),
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        confirmButton = {
            if (confirmButton == null) {
                TextButton(onClick = { onConfirmClicked(); onDismissRequest() }) {
                    Text(
                        text = stringResource(R.string.btn_txt_delete),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            } else {
                confirmButton()
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    text = stringResource(R.string.btn_txt_cancel),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        },
        icon = {
            Icon(
                painter = painterResource(topIcon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        },
        containerColor = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun RetryButton(onRetryClicked: () -> Unit) {
    Button(onClick = onRetryClicked) {
        Text(text = stringResource(R.string.btn_txt_retry))
    }
}

@Preview(name = "Delete Favorites Confirmation Dialog")
@Composable
private fun PreviewDeleteConfirmationDialog() {
    LingvooTranslatorAppTheme {
        CustomAlertDialog(
            title = R.string.dialog_ttl_delete_all_favorites,
            mainText = R.string.dialog_sub_delete_favorites,
            topIcon = R.drawable.warning_icon,
            onDismissRequest = {},
            onConfirmClicked = {}
        )
    }
}

@Preview(name = "No Internet Connection Dialog")
@Composable
private fun PreviewNoInternetConnectionDialog() {
    LingvooTranslatorAppTheme {
        CustomAlertDialog(
            title = R.string.dialog_ttl_no_internet_connection,
            mainText = R.string.dialog_sub_no_internet_connection,
            topIcon = R.drawable.no_internet_connection_icon,
            onDismissRequest = {},
            onConfirmClicked = {},
            confirmButton = { RetryButton(onRetryClicked = {}) }
        )
    }
}