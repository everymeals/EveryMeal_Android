package com.everymeal.presentation.components

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Grey5

@Composable
fun EveryMealTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    isError: Boolean = false,
    supportingText: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    onEnterPressed: (() -> Unit)? = null, // 'onSearch' 대신 'onEnterPressed' 사용
    otherCustomization: (@Composable () -> Unit)? = null
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        isError = isError,
        supportingText = supportingText,
        textStyle = EveryMealTypography.Body1,
        placeholder = {
            Text(
                modifier = Modifier.wrapContentHeight(Alignment.CenterVertically),
                text = placeholderText,
                style = EveryMealTypography.Body1,
                color = Grey5,
                textAlign = TextAlign.Start
            )
        },
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (onEnterPressed != null) ImeAction.Search else ImeAction.Default
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onEnterPressed?.invoke()
            }
        ),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Gray300,
            unfocusedContainerColor = Gray300,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
    otherCustomization?.invoke()
}
