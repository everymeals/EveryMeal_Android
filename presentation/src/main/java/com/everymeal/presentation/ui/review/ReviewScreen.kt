package com.everymeal.presentation.ui.review

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.search.topbar.SearchBar
import com.everymeal.presentation.ui.theme.Grey9
import com.everymeal.presentation.ui.theme.Typography

@Composable
fun ReviewScreen() {
    Scaffold(
        topBar = {
            ReviewTopBar()
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column {
                ReviewGuideHeader()
                Spacer(modifier = Modifier.size(28.dp))
                ReviewSearchBar()
            }
        }
    }

}

@Composable
fun ReviewSearchBar() {
    val searchQuery = remember {
        mutableStateOf("")
    }

    SearchBar(
        searchQuery = searchQuery.value,
        changeQuery = {
            searchQuery.value = it
        },
        setShowHistory = {
            if (it) {

            }
        }
    )
}

@Composable
fun ReviewGuideHeader(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .padding(start = 24.dp, top = 48.dp),
        text = "다녀온 맛집은\n어디인가요?",
        style = Typography.titleLarge,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "리뷰 작성",
                style = Typography.bodySmall,
                color = Grey9
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp)
                    .padding(end = 4.dp),
                painter = painterResource(id = R.drawable.icon_x_mono),
                contentDescription = null
            )
        }
    )
}

@Composable
@Preview
fun ReviewScreenPreview() {
    ReviewScreen()
}
