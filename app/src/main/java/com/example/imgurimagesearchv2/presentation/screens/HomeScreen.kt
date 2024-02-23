package com.example.imgurimagesearchv2.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.imgurimagesearchv2.domain.model.Data
import com.example.imgurimagesearchv2.presentation.Dimens.MediumPadding1
import com.example.imgurimagesearchv2.presentation.SearchEvent

@Composable
fun HomeScreen(
    dataList: LazyPagingItems<Data>,
    event: (SearchEvent) -> Unit,
    modifier: Modifier
) {

    val query: MutableState<String> = remember {
        mutableStateOf("")
    }

    val checked : MutableState<Boolean> =  remember {
        mutableStateOf(true)
    }

    Surface {
        Column(
            modifier = modifier
                .padding(
                    top = MediumPadding1,
                    start = MediumPadding1,
                    end = MediumPadding1
                )
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = query.value,
                onValueChange = {
                    query.value = it
                    event(SearchEvent.SearchImages(query.value))
                },
                enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))

            Row( verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Select Display", fontSize = 20.sp)
                Spacer(modifier = Modifier.padding(12.dp))
                Text(text = "List", fontSize = 20.sp)
                Switch(
                    modifier = Modifier.width(80.dp),
                    checked = checked.value,
                    onCheckedChange = {
                        checked.value = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue,
                        uncheckedTrackColor = Color.Green
                    )
                )
                Text(text = "Grid", fontSize = 20.sp)
            }
            Box(modifier = Modifier.fillMaxSize()){
                if (dataList.loadState.refresh is LoadState.Loading && query.value.isNotEmpty()){
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    if (checked.value){
                        ImageGridDisplay(dataList)
                    }
                    else{
                        ImageListDisplay(dataList)
                    }
                }
            }
        }

    }

}

@Composable
fun ImageListDisplay(data: LazyPagingItems<Data>){
    LazyColumn(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ){
        items(
            data.itemCount,
            key = data.itemKey{ it -> it.id}) { index ->
            data[index]?.let { it.images.forEach { image ->
                ImageListItem(image = image)
            } }
        }
    }
}

@Composable
fun ImageGridDisplay(data: LazyPagingItems<Data>){
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()) {
        items(
            data.itemCount,
            key = data.itemKey{ it -> it.id}){ index ->
            data[index]?.let { it.images.forEach { image ->  
                ImageGridItem(image = image)
            } }
        }

    }
}