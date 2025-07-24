package com.example.cmpnavigationapplication.ui.presentation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.navigation.toRoute
import cmpnavigationapplication.composeapp.generated.resources.Res
import cmpnavigationapplication.composeapp.generated.resources.screen_1
import cmpnavigationapplication.composeapp.generated.resources.screen_2
import com.example.cmpnavigationapplication.ui.data.ScreenRoute
import org.jetbrains.compose.resources.stringResource
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

    val screen1Route = ScreenRoute.Screen1Route(stringResource(Res.string.screen_1))
    val screen2Route = ScreenRoute.Screen2Route(stringResource(Res.string.screen_2))

    // Get the name of the current screen
    var currentScreen = screen1Route.title
    backStackEntry?.destination?.let {
        currentScreen = when {
            it.hasRoute(ScreenRoute.Screen1Route::class) -> screen1Route.title
            it.hasRoute(ScreenRoute.Screen2Route::class) -> screen2Route.title
            else -> currentScreen
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
            startDestination = screen1Route,
            enterTransition = {
                slideInHorizontally { it }
            },
            popEnterTransition = {
                slideInHorizontally { -it }
            },
            exitTransition = {
                slideOutHorizontally { -it }
            },
            popExitTransition = {
                slideOutHorizontally { it }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            ) {
            composable<ScreenRoute.Screen1Route> {
                Screen1 { list ->
                    navController.navigate(screen2Route.apply {
                        dataList = list
                    })
                }
            }
            composable<ScreenRoute.Screen2Route> {
                Screen2(
                    it.toRoute<ScreenRoute.Screen2Route>().dataList
                )
            }
        }
    }

}