package com.example.cmpnavigationapplication.ui.data

import kotlinx.serialization.Serializable

@Serializable
internal sealed class ScreenData(
    val name: String
) {
    @Serializable
    data object Screen1Route: ScreenData("Screen 1")

    @Serializable
    data object Screen2Route: ScreenData("Screen 2")
}
