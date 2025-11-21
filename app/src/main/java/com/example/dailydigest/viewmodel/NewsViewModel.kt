package com.example.dailydigest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailydigest.data.repository.NewsRepository
import com.example.dailydigest.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    private val _savedArticles = MutableStateFlow<List<Article>>(emptyList())
    val savedArticles: StateFlow<List<Article>> = _savedArticles.asStateFlow()

    private val _selectedCategory = MutableStateFlow("general")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadHeadlines("general")
        loadSavedArticles()
    }

    fun loadHeadlines(category: String) {
        _isLoading.value = true
        _errorMessage.value = null
        _selectedCategory.value = category
        _searchQuery.value = ""

        viewModelScope.launch {
            try {
                val newsArticles = repository.getTopHeadlines(category)
                _articles.value = newsArticles
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load news: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun performSearch() {
        val query = _searchQuery.value.trim()
        if (query.isNotEmpty()) {
            _isLoading.value = true
            viewModelScope.launch {
                try {
                    // Filter existing articles (you can implement API search later)
                    val filteredArticles = _articles.value.filter {
                        it.title.contains(query, ignoreCase = true) ||
                                it.description.contains(query, ignoreCase = true)
                    }
                    _articles.value = filteredArticles
                } catch (e: Exception) {
                    _errorMessage.value = "Search failed: ${e.message}"
                } finally {
                    _isLoading.value = false
                }
            }
        } else {
            loadHeadlines(_selectedCategory.value)
        }
    }

    fun toggleSaveArticle(article: Article) {
        viewModelScope.launch {
            try {
                if (article.isSaved) {
                    repository.deleteArticle(article.id ?: article.url.hashCode().toString())
                } else {
                    repository.saveArticle(article)
                }
                // Refresh to update UI
                loadHeadlines(_selectedCategory.value)
                loadSavedArticles()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to save article: ${e.message}"
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    private fun loadSavedArticles() {
        viewModelScope.launch {
            repository.getSavedArticles().collect { saved ->
                _savedArticles.value = saved
            }
        }
    }

    fun refreshNews() {
        loadHeadlines(_selectedCategory.value)
    }
}