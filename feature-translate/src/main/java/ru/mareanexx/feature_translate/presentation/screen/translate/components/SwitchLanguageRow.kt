package ru.mareanexx.feature_translate.presentation.screen.translate.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.R

@Composable
fun SwitchLanguageRow(
    modifier: Modifier = Modifier,
    onSwitchClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 32.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 16.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LanguageBlock(
            languageName = R.string.lbl_english_lang,
            languageIcon = R.drawable.eng_flag,
            isLeft = true
        )
        
        SwapLanguagesButton(onSwitchClicked)

        LanguageBlock(
            languageName = R.string.lbl_russian_lang,
            languageIcon = R.drawable.russian_flag,
            isLeft = false
        )
    }
}

@Composable
private fun SwapLanguagesButton(onSwitchClicked: () -> Unit) {
    Button(
        modifier = Modifier.width(50.dp),
        onClick = onSwitchClicked,
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(R.drawable.swap_horiz_icon),
            contentDescription = stringResource(R.string.cd_swap_languages),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun LanguageNameText(
    @StringRes language: Int,
    textColor: Color = MaterialTheme.colorScheme.onSecondary
) {
    Text(
        text = stringResource(language),
        style = MaterialTheme.typography.titleSmall,
        color = textColor
    )
}

@Composable
private fun LanguageBlock(
    @StringRes languageName: Int,
    @DrawableRes languageIcon: Int,
    isLeft: Boolean
) {
    @Composable
    fun LanguageImage(
        @DrawableRes languageIcon: Int,
        @StringRes cdLanguage: Int
    ) {
        Image(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
                .padding(2.dp)
                .clip(CircleShape)
                .size(30.dp),
            painter = painterResource(languageIcon),
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(cdLanguage)
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (isLeft) {
            LanguageImage(languageIcon, languageName)
            LanguageNameText(languageName)
        } else {
            LanguageNameText(languageName)
            LanguageImage(languageIcon, languageName)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 1111)
@Composable
private fun PreviewSwitchLanguageRow() {
    LingvooTranslatorAppTheme {
        SwitchLanguageRow(onSwitchClicked = {})
    }
}