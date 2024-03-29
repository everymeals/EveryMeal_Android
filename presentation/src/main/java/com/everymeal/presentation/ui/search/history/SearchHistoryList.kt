package com.everymeal.presentation.ui.search.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray900

@Composable
fun SearchHistoryList(
    historyItems: List<String>,
    isVisible: Boolean,
    onHistoryItemClicked: (String) -> Unit,
    removeHistoryItem: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isVisible) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(R.string.recent_searches),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Gray500,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 10.dp),
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(historyItems) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .clickable { onHistoryItemClicked(item) },
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = item,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400),
                                color = Gray900
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Delete history item",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    removeHistoryItem(item)
                                },
                        )
                    }
                }
            }
        }
    }
}
