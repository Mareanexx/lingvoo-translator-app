package ru.mareanexx.feature_translate.presentation.components.navbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mareanexx.feature_translate.R
import ru.mareanexx.feature_translate.presentation.components.popUpScaleValue

@Composable
fun HomeNavItem(
    selected: Boolean,
    modifier: Modifier = Modifier,
    onTabSelected: () -> Unit
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val surfaceColor = MaterialTheme.colorScheme.surface
    val scale = popUpScaleValue(selected)

    Box(
        modifier = modifier
            .size(80.dp)
            .clip(CircleShape)
            .drawBehind {
                drawCircle(
                    color = if (selected) primaryColor
                    else surfaceColor,
                    radius = size.minDimension / 2
                )
            }
            .clickable(onClick = onTabSelected),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            painter = painterResource(R.drawable.home_icon),
            contentDescription = stringResource(R.string.cd_home_route),
            tint = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    }
}