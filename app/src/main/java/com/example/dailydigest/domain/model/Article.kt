package com.example.dailydigest.domain.model





data class Article(
    val id: String? = null,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val source: String,
    val category: String = "General",
    val isSaved: Boolean = false
)