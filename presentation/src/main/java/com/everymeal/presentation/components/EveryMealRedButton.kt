package com.everymeal.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.ui.theme.Gray200
import com.everymeal.presentation.ui.theme.Main100

@Composable
fun EveryMealRedButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Main100,
            contentColor = Color.White,
            disabledContainerColor = Gray200,
            disabledContentColor = Color.White,
        ),
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
        content = {
            Text(
                text = buttonText,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                )
            )
        }
    )
}
