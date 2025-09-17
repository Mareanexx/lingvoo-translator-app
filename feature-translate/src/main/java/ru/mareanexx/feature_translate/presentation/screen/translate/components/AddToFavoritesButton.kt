package ru.mareanexx.feature_translate.presentation.screen.translate.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import ru.mareanexx.core.ui.theme.FavoriteFilled
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.R
import ru.mareanexx.feature_translate.presentation.components.popUpScaleValue

@Composable
fun AddToFavoriteIconButton(
    isFavorite: Boolean,
    onButtonClicked: () -> Unit
) {
    val containerColor by animateColorAsState(
        targetValue = if (isFavorite) FavoriteFilled
        else MaterialTheme.colorScheme.secondary,
        label = "ContainerColor"
    )

    val iconColor by animateColorAsState(
        targetValue = if (isFavorite) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.tertiary,
        label = "IconColor"
    )

    val scale = popUpScaleValue(isFavorite, peak = 1.3f)

    IconButton(
        modifier = Modifier
            .size(50.dp)
            .dropShadow(
                shape = CircleShape,
                shadow = Shadow(4.dp, spread = 1.dp, alpha = 0.14f, offset = DpOffset(x = 0.dp, y = 2.dp), blendMode = BlendMode.Darken)
            ),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor
        ),
        onClick = onButtonClicked
    ) {
        Icon(
            modifier = Modifier.size(32.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            painter = painterResource(R.drawable.star_icon),
            contentDescription = stringResource(R.string.cd_btn_add_to_favorite),
            tint = iconColor
        )
    }
}

@Preview(name = "Clicked Add to favorite button")
@Composable
private fun PreviewAddToFavoriteIconButton() {
    LingvooTranslatorAppTheme {
        AddToFavoriteIconButton(
            isFavorite = true,
            onButtonClicked = {}
        )
    }
}

@Preview(name = "Not Added to favorite button")
@Composable
private fun PreviewNotClickedFavoriteIconButton() {
    LingvooTranslatorAppTheme {
        AddToFavoriteIconButton(
            isFavorite = false,
            onButtonClicked = {}
        )
    }
}

@Preview(name = "Animation Preview")
@Composable
private fun PreviewAddToFavoriteButtonClickAnimation() {
    val isFavorite = remember { mutableStateOf(false) }

    LingvooTranslatorAppTheme {
        AddToFavoriteIconButton(
            isFavorite = isFavorite.value,
            onButtonClicked = { isFavorite.value = !isFavorite.value }
        )
    }
}