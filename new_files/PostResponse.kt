package com.example.aventurape_androidmobile.utils.models

data class PostResponse(
    val id: Long,
    val entrepreneurId: Long,
    val nameActivity: String,
    val description: String,
    val timeDuration: Long,
    val image: String,
    val cantPeople: Long,
    val cost: Float
)
