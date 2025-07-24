package com.example.cmpnavigationapplication.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Screen1(
    onNextClick: (List<String>) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            content = {
                Text("Next")
            },
            onClick = {
                onNextClick(
                    listOf(
                        "A",
                        "B",
                        "C",
                    )
                )
            }
        )
    }
}