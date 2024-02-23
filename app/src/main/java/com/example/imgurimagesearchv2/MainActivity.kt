package com.example.imgurimagesearchv2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.imgurimagesearchv2.presentation.ImgurViewModel
import com.example.imgurimagesearchv2.presentation.screens.HomeScreen
import com.example.imgurimagesearchv2.ui.theme.ImgurImageSearchV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImgurImageSearchV2Theme {
                val viewModel = hiltViewModel<ImgurViewModel>()
                val images = viewModel.searchedImages.collectAsLazyPagingItems()

                MyApp {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),
                                title = {
                                    Text("Imgur Image Search")
                                }
                            )
                        }
                    ) {
                        HomeScreen(
                            dataList = images,
                            event = viewModel::onEvent,
                            modifier = Modifier.padding(it)
                        )
                    }
                }

            }
        }
    }
}
@Composable
fun MyApp(content: @Composable ()->Unit) {
    content()
}

