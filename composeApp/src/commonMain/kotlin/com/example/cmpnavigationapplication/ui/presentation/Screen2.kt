package com.example.cmpnavigationapplication.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Screen2(
    dataList: List<String>?
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(dataList ?: emptyList()) {
                Text(it)
            }
        }
    }
}