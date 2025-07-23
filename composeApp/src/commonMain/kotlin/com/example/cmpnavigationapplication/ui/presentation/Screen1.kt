package com.example.cmpnavigationapplication.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.toRoute
import com.example.cmpnavigationapplication.ui.data.ScreenData

@Composable
fun Screen1(
    navController: NavController,
) {
    val screenData = navController.currentBackStackEntry?.toRoute<ScreenData.Screen1Route>()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text("Screen 1")
        Button(
            content = {
                Text("Next")
            },
            onClick = {
                navController.navigate(ScreenData.Screen2Route)
            }
        )
    }
}