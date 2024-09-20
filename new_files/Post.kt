package com.example.aventurape_androidmobile.domains.feed.models

data class Post(
    val id: Long,
    val entrepreneurId: Long,
    val nameActivity: String,
    val description: String,
    val timeDuration: Long,
    val image: String,
    val cantPeople: Long,
    val cost: Float
)
