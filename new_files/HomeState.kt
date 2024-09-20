package com.example.aventurape_androidmobile.domains.feed.screens.states
import com.example.aventurape_androidmobile.domains.feed.models.Post

data class HomeState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
