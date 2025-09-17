package ru.mareanexx.feature_translate.presentation.components.navbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mareanexx.feature_translate.presentation.components.popUpScaleValue

@Composable
fun CommonNavItem(
    @DrawableRes icon: Int,
    @StringRes contentDescription: Int,
    selected: Boolean,
    onTabSelected: () -> Unit
) {
    val scale = popUpScaleValue(selected)

    IconButton(onClick = onTabSelected) {
        Icon(
            modifier = Modifier
                .size(44.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            painter = painterResource(icon),
            contentDescription = stringResource(contentDescription),
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}