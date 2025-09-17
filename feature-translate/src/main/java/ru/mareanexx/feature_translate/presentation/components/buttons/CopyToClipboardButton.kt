package ru.mareanexx.feature_translate.presentation.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.mareanexx.feature_translate.R

@Composable
fun CopyToClipboardButton(onContentCopyClicked: () -> Unit) {
    val showCheckIcon = remember { mutableStateOf(false) }

    LaunchedEffect(showCheckIcon.value) {
        if (showCheckIcon.value) {
            delay(1500)
            showCheckIcon.value = false
        }
    }

    IconButton(onClick = { onContentCopyClicked(); showCheckIcon.value = true }) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(
                if (!showCheckIcon.value) R.drawable.content_copy_icon else R.drawable.check_icon
            ),
            contentDescription = stringResource(R.string.cd_btn_copy_to_clipboard),
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}