package com.example.aventurape_androidmobile.domains.feed.screens.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aventurape_androidmobile.domains.feed.models.Post
import com.example.aventurape_androidmobile.utils.RetrofitClient
import com.example.aventurape_androidmobile.utils.models.PostResponse

class HomeViewModel(): ViewModel() {
    var state by mutableStateOf(HomeState())
        private set



    private var posts: List<Post> = emptyList()

    fun resetState() {
        state = HomeState()
    }

    suspend fun listPosts() {
        val response = RetrofitClient.placeholder.listPosts()
        if (response.isSuccessful && response.body() != null) {
            val postsResponse = response.body()!!
            val posts = postsResponse.map { mapPostResponseToModel(it) }
            state = state.copy(posts = posts, isLoading = false, errorMessage = null)
        }else{
            state = state.copy(posts = emptyList(), isLoading = false, errorMessage = "Error while fetching posts")
        }
    }
    private fun mapPostResponseToModel(postResponse: PostResponse): Post {
        return Post(
            id = postResponse.id,
            entrepreneurId = postResponse.entrepreneurId,
            nameActivity = postResponse.nameActivity,
            description = postResponse.description,
            timeDuration = postResponse.timeDuration,
            image = postResponse.image,
            cantPeople = postResponse.cantPeople,
            cost = postResponse.cost
        )
    }
}