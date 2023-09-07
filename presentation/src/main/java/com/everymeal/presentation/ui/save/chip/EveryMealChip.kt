package com.everymeal.presentation.ui.save.chip

import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

data class ChipState(
    var text: String,
    val isSelected: MutableState<Boolean>
)

data class ChipStyle(
    val selectedColor: Color,
    val unselectedColor: Color,
    val chipTextStyle: TextStyle,
    val selectedTextColor: Color,
    val unselectedTextColor: Color,
    val chipModifier: Modifier = Modifier,
)

@Composable
private fun Chip(
    text: String,
    selected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    chipTextStyle: TextStyle,
    selectedTextColor: Color,
    unselectedTextColor: Color,
    @SuppressLint("ModifierParameter")
    chipModifier: Modifier,
    modifier: Modifier = Modifier,
    onChipClicked: (String, Boolean) -> Unit,
) {
    Surface(
        color = when {
            selected -> selectedColor
            else -> unselectedColor
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = when {
                selected -> selectedTextColor
                else -> unselectedTextColor
            },
            style = chipTextStyle,
            modifier = chipModifier
                .clickable { onChipClicked(text, selected) }
        )
    }
}

@Composable
fun Chips(
    modifier: Modifier = Modifier,
    elements: List<ChipState>,
    chipStyle: ChipStyle,
    onChipClicked: (String, Boolean, Int) -> Unit,
) {
    LazyRow(modifier = modifier) {
        items(elements.size) { idx ->
            Chip(
                text = elements[idx].text,
                selected = elements[idx].isSelected.value,
                selectedColor = chipStyle.selectedColor,
                unselectedColor = chipStyle.unselectedColor,
                chipTextStyle = chipStyle.chipTextStyle,
                selectedTextColor = chipStyle.selectedTextColor,
                unselectedTextColor = chipStyle.unselectedTextColor,
                chipModifier = chipStyle.chipModifier,
                onChipClicked = { content, isSelected ->
                    onChipClicked(content, isSelected, idx)
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}
