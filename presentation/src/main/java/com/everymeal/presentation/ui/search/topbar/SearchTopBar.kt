package com.everymeal.presentation.ui.search.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.search.SearchState
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    changeQuery: (String) -> Unit,
    onBackClick: () -> Unit,
    setShowHistory: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 14.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = modifier
                .size(48.dp)
                .padding(12.dp)
                .clickable {
                    onBackClick()
                }
        )
        SearchBar(
            searchQuery = searchQuery,
            changeQuery = changeQuery,
            setShowHistory = setShowHistory,
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    changeQuery: (String) -> Unit,
    setShowHistory: (Boolean) -> Unit
) {
    Box(modifier = modifier) {
        TextField(
            value = searchQuery,
            onValueChange = {
                changeQuery(it)
                setShowHistory(it.isEmpty())
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.icon_search_mono),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.placeholder_search),
                    style = TextStyle(
                        color = Gray500,
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp
                    )
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Gray300,
                unfocusedContainerColor = Gray300,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        )
    }
}
