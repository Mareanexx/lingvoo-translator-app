package ru.mareanexx.feature_translate.presentation.screen.translate.components.io

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mareanexx.core.ui.theme.HeaderShape
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.R
import ru.mareanexx.feature_translate.presentation.screen.translate.components.LanguageNameText

@Composable
fun WordInputField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    onClearInputClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = HeaderShape
            )
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large
            )
            .padding(vertical = 48.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.height(24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LanguageNameText(
                language = R.string.lbl_english_lang,
                textColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f)
            )
            if (value.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .clickable(onClick = onClearInputClicked),
                    painter = painterResource(R.drawable.close_icon),
                    contentDescription = stringResource(R.string.cd_btn_clear_user_input),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        InputField(value = value, onValueChanged = onValueChanged)
    }
}

@Composable
fun InputField(
    value: String,
    onValueChanged: (String) -> Unit
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        maxLines = 10,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimary),
        textStyle = MaterialTheme.typography.bodyLarge
            .copy(color = MaterialTheme.colorScheme.onPrimary),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) { // placeholder when user input is empty
                Text(
                    text = stringResource(R.string.et_placeholder_enter_word_here),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f)
                )
            }
            innerTextField()
        }
    )
}

@Preview(name = "Empty text field", showBackground = true)
@Composable
private fun PreviewEmptyInputField() {
    LingvooTranslatorAppTheme {
        WordInputField(
            value = "",
            onValueChanged = {},
            onClearInputClicked = {}
        )
    }
}

@Preview(name = "One word text field", showBackground = true)
@Composable
private fun PreviewOneWordInputField() {
    LingvooTranslatorAppTheme {
        WordInputField(
            value = "Hello",
            onValueChanged = {},
            onClearInputClicked = {}
        )
    }
}