package ru.mareanexx.feature_translate.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.R

@Composable
fun BlueScreenHeader(
    @StringRes headText: Int,
    @DrawableRes buttonIcon: Int,
    @StringRes cdButton: Int,
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceContainerHighest)
            .padding(start = 30.dp, end = 25.dp, top = 15.dp, bottom = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(headText),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        IconButton(
            onClick = onButtonClicked,
            colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.onPrimary)
        ) {
            Icon(
                painter = painterResource(buttonIcon),
                contentDescription = stringResource(cdButton),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun PreviewBlueHeader() {
    LingvooTranslatorAppTheme {
        BlueScreenHeader(
            headText = ru.mareanexx.core.R.string.app_name,
            buttonIcon = R.drawable.settings_icon,
            cdButton = R.string.cd_open_settings,
            onButtonClicked = {}
        )
    }
}