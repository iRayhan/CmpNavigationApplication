package com.example.cmpnavigationapplication.ui.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cmpnavigationapplication.ui.data.ScreenData
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(currentScreen)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                Button(
                    onClick = navigateUp,
                    content = {
                        Text("Back")
                    }
                )
            }
        }
    )
}

@Composable
@Preview
fun StartScreen(
    navController: NavHostController = rememberNavController(),
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    var currentScreen = ""
    backStackEntry?.destination?.let {
        currentScreen = when {
            it.hasRoute(ScreenData.Screen1Route::class) -> ScreenData.Screen1Route.name
            it.hasRoute(ScreenData.Screen2Route::class) -> ScreenData.Screen2Route.name
            else -> ScreenData.Screen1Route.name
        }
    }

    Scaffold(
        topBar = {
            ScreenAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = ScreenData.Screen1Route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable<ScreenData.Screen1Route> {
                Screen1(navController)
            }
            composable<ScreenData.Screen2Route> {
                Screen2(navController)
            }
        }
    }

}