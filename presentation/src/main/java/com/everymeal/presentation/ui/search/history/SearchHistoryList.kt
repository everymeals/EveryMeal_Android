package com.everymeal.presentation.ui.search.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchHistoryList(
    historyItems: MutableState<List<String>>,
    isVisible: Boolean,
    onHistoryItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (isVisible) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            items(historyItems.value) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.clickable { onHistoryItemClicked(item) }
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Delete history item",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                historyItems.value = historyItems.value.filterNot { it == item }
                            }
                    )
                }
            }
        }
    }
}