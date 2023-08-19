package com.everymeal.presentation.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500

@Composable
fun SearchScreen() {
    Scaffold(
        topBar = {
            TopBar(
                onBackClick = { }
            )
        }
    ) { innerPadding ->
        SearchDetail(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SearchDetail(
    modifier: Modifier = Modifier
) {

}

@Composable
fun TopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = modifier
                .size(width = 48.dp, height = 48.dp)
                .padding(12.dp)
                .clickable {
                    onBackClick()
                }
        )
        SearchBar()
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
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
                    fontSize = 16.sp,
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
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
            .heightIn(min = 56.dp)
    )
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen()
}