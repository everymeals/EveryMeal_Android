package com.everymeal.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.everymeal.presentation.ui.theme.EveryMealTypo
import com.everymeal.presentation.ui.theme.Grey7
import com.everymeal.presentation.ui.theme.MONO_BLACK
import com.everymeal.presentation.ui.theme.Main100

@Composable
fun EveryMealDialog(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    confirmButtonText: String,
    dismissButtonText: String,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    onDisMissClicked: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        content = {
            Surface(
                modifier = modifier,
                shape = RoundedCornerShape(10.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        style = EveryMealTypo.titleLarge,
                        color = MONO_BLACK
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = message,
                        style = EveryMealTypo.bodyMedium,
                        color = Grey7
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row {
                        EveryMealGreyButton(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 4.dp),
                            onClick = onDisMissClicked,
                            buttonText = dismissButtonText
                        )
                        EveryMealRedButton(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp),
                            onClick = onConfirmClick,
                            buttonText = confirmButtonText
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun EveryMealLoadingDialog(
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Main100
        )
    }
}