package com.example.cmpnavigationapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform