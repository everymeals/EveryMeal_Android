package com.everymeal.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.Gray200
import com.everymeal.presentation.ui.theme.Main100
import com.everymeal.presentation.ui.theme.Main800

@Composable
fun EveryMealMainButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(enabled) Main100 else Gray200,
            contentColor = Main800
        ),
        enabled = enabled,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Medium,
            ),
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun EveryMealLineButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
        ),
        enabled = enabled,
        onClick = onClick,
        border = BorderStroke(1.dp, Main100),
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Main100,
                fontWeight = FontWeight.Medium,
            ),
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Preview
@Composable
fun EveryMealMainButtonPreview() {
    EveryMealMainButton(
        text = stringResource(R.string.select),
    ) { }
}

@Preview
@Composable
fun EveryMealLineButtonPreview() {
    EveryMealLineButton(
        text = stringResource(R.string.select),
    ) { }
}