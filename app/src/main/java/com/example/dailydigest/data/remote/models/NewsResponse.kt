package com.example.dailydigest.data.remote.models

import com.example.dailydigest.domain.model.Article

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticleDto>
)

data class NewsArticleDto(
    val source: SourceDto? = null,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
) {
    fun toArticle(category: String = "General"): Article {
        return Article(
            id = url.hashCode().toString(),
            title = title,
            description = description ?: "No description available",
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt?.take(10) ?: "Unknown date",
            source = source?.name ?: "Unknown Source",
            category = category,
            isSaved = false
        )
    }
}

data class SourceDto(
    val id: String? = null,
    val name: String? = null
)