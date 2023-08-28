package com.everymeal.presentation.ui.save.chip

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everymeal.presentation.ui.theme.Grey2
import com.everymeal.presentation.ui.theme.Grey7
import com.everymeal.presentation.ui.theme.Main100
import com.everymeal.presentation.ui.theme.RED_LIGHT
import com.everymeal.presentation.ui.theme.Typography

data class ChipState(
    var text: String,
    val isSelected: MutableState<Boolean>
)

@Composable
private fun Chip(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onChipClicked: (String, Boolean) -> Unit,
) {
    Surface(
        color = when {
            selected -> RED_LIGHT
            else -> Grey2
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = when {
                selected -> Main100
                else -> Grey7
            },
            style = Typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .clickable { onChipClicked(text, selected) }
        )
    }
}

@Composable
fun Chips(
    modifier: Modifier = Modifier,
    elements: List<ChipState>,
    onChipClicked: (String, Boolean, Int) -> Unit,
) {
    LazyRow(modifier = modifier) {
        items(elements.size) { idx ->
            Chip(
                text = elements[idx].text,
                selected = elements[idx].isSelected.value,
                onChipClicked = { content, isSelected ->
                    onChipClicked(content, isSelected, idx)
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}