package com.example.cmpnavigationapplication.ui.data

import kotlinx.serialization.Serializable

@Serializable
internal sealed interface ScreenRoute {

    @Serializable
    data class Screen1Route(
        val title: String,
    ) : ScreenRoute

    @Serializable
    data class Screen2Route(
        val title: String,
        var dataList: List<String>? = null,
    ) : ScreenRoute
}
