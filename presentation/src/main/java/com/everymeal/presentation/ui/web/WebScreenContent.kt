package com.everymeal.presentation.ui.web

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.everymeal.presentation.R
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebScreenContent(
    url: String,
    staticTitle: String? = null,
    webViewNavigator: WebViewNavigator,
    onBackPressed: (() -> Unit)? = null,
) {

    val webViewState = rememberWebViewState(url = url)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = staticTitle ?: (webViewState.pageTitle ?: "")) },
                navigationIcon = {
                    onBackPressed?.let {
                        Image(
                            modifier = Modifier.clickable {
                                if (webViewNavigator.canGoBack) webViewNavigator.navigateBack()
                                else onBackPressed()
                            },
                            painter = painterResource(id = R.drawable.icon_arrow_back_mono),
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        WebView(
            state = webViewState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navigator = webViewNavigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            client = remember { AccompanistWebViewClient() },
            chromeClient = remember { AccompanistWebChromeClient() }
        )
    }

}
