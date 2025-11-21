# 📰 Daily Digest - Smart News Aggregator

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.21-blue.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.7-brightgreen.svg)](https://developer.android.com/jetpack/compose)
[![MVVM](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Clean%20Architecture-orange.svg)](https://developer.android.com/jetpack/guide)
[![Room](https://img.shields.io/badge/Database-Room%20SQLite-red.svg)](https://developer.android.com/training/data-storage/room)
[![Retrofit](https://img.shields.io/badge/Networking-Retrofit%20%2B%20OkHttp-purple.svg)](https://square.github.io/retrofit/)
[![Hilt](https://img.shields.io/badge/DI-Dagger%20Hilt-yellow.svg)](https://dagger.dev/hilt/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

A modern Android news app built with Jetpack Compose that delivers personalized news feeds with offline reading capabilities using real NewsAPI integration.

**Developer**: Mathew Charles

## ✨ Features

- 📱 **Modern UI** - Built with Jetpack Compose and Material Design 3
- 📰 **Personalized News** - Category-based news filtering (General, Technology, Sports, Business, Entertainment, Health)
- 💾 **Offline Reading** - Save articles for later with Room database
- 🔍 **Smart Search** - Real-time search through news articles
- 🌐 **Real-time Data** - Live news from NewsAPI with fallback sample data
- 🏗 **Clean Architecture** - MVVM with Repository pattern
- 💉 **Dependency Injection** - Hilt for clean dependency management
- 🔄 **Reactive UI** - StateFlow and coroutines for seamless user experience

## 🛠 Tech Stack & Dependencies

### Core Framework
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material Design 3** - Latest Material Design components

### Architecture
- **MVVM + Clean Architecture** - Separation of concerns
- **Android Architecture Components** - ViewModel, LiveData, Room
- **Repository Pattern** - Single source of truth

### Networking & Data
- **Retrofit 2** - Type-safe HTTP client
- **Gson** - JSON serialization/deserialization
- **OkHttp Logging Interceptor** - Network debugging
- **Room Database** - Local data persistence
- **NewsAPI** - News data provider

### Async & DI
- **Kotlin Coroutines** - Asynchronous programming
- **Flow** - Reactive streams
- **Dagger Hilt** - Dependency injection
- **Hilt Navigation Compose** - Navigation with DI

### UI & Imaging
- **Coil** - Image loading for Compose
- **Compose Navigation** - Type-safe navigation
- **Material Icons** - Comprehensive icon set

## 🏗 Architecture

### Clean Architecture Layers
📱 Presentation Layer (UI)
├── Screens (NewsFeedScreen, SavedArticlesScreen)
├── Components (ArticleCard, CategoryChip, SearchBar)
├── Navigation (NavGraph, BottomNavigationBar)
└── ViewModels (NewsViewModel)

📍 Domain Layer (Business Logic)
├── Models (Article)
└── Repository Interfaces (NewsRepository)

💾 Data Layer (Data Sources)
├── Repository Implementation (NewsRepositoryImpl)
├── Local Data (Room Database, ArticleDao)
└── Remote Data (Retrofit, NewsApi)

## 📸 Screenshots
<img width="362" height="783" alt="Screenshot 2025-11-21 173424" src="https://github.com/user-attachments/assets/dcc31ed9-cd21-43f4-b5db-1d0f950f5a6c" />
<img width="379" height="794" alt="Screenshot 2025-11-21 173504" src="https://github.com/user-attachments/assets/f98cfaf3-8c27-46be-a8d8-197b5a98a892" />
<img width="1919" height="1079" alt="Screenshot 2025-11-21 173342" src="https://github.com/user-attachments/assets/36e21ab9-c1b5-4084-b548-0e02d44094f0" />


🚀 Getting Started
Prerequisites
Android Studio Hedgehog or later

Android SDK 34

Kotlin 1.9.21

NewsAPI key (free from newsapi.org)

Installation & Setup
Clone the repository

bash
git clone https://github.com/yourusername/daily-digest.git
cd daily-digest
Get Free API Key

Register at newsapi.org

Verify your email and get your free API key

Free tier: 100 requests/day

Configure API Key

Open data/remote/api/NewsApi.kt

Replace YOUR_API_KEY_HERE with your actual API key in both functions:

kotlin
@Query("apiKey") apiKey: String = "your_actual_api_key_here"
Build and Run

bash
./gradlew assembleDebug
or use Android Studio:

Open project in Android Studio

Build → Make Project

Run → Run 'app'

Gradle Configuration
The project uses modern Gradle configuration:

Project-level build.gradle.kts:

kotlin
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}
App-level build.gradle.kts includes all necessary dependencies for:

Compose UI

Room database

Retrofit networking

Hilt dependency injection

Coil image loading

🎯 Key Implementation Details
Data Models
Domain Model (Article.kt):

kotlin
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
Repository Pattern
Repository Interface:

kotlin
interface NewsRepository {
    suspend fun getTopHeadlines(category: String? = null): List<Article>
    fun getSavedArticles(): Flow<List<Article>>
    suspend fun saveArticle(article: Article)
    suspend fun deleteArticle(articleId: String)
}
Repository Implementation handles:

API calls with error handling

Local database operations

Fallback sample data

Data transformation between layers

ViewModel with State Management
NewsViewModel uses StateFlow for reactive UI:

kotlin
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    // State management methods...
}
Compose UI Components
ArticleCard - Displays news articles with:

Coil image loading

Bookmark functionality

Click handling

Responsive layout

CategoryChip - Filter chips for:

General, Technology, Sports, Business, Entertainment, Health

Navigation Setup
BottomNavigationBar with two destinations:

News Feed (news_feed route)

Saved Articles (saved_articles route)

🔧 Configuration
AndroidManifest Permissions
xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
Room Database Configuration
Database name: daily_digest_db

Version: 1

Entity: SavedArticleEntity

DAO: ArticleDao

Supported Android Versions
Minimum SDK: 21 (Android 5.0)

Target SDK: 34 (Android 14)

Compile SDK: 34

🎨 UI/UX Features
Dark/Light theme support through Material Design 3

Smooth animations and transitions

Responsive layout for various screen sizes

Pull-to-refresh functionality

Offline-first approach with Room caching

🔄 Data Flow
User opens app → ViewModel initializes and loads headlines

Select category → API call with category filter

Save article → Room database insertion

Search → Local filtering of loaded articles

Offline mode → Fallback to sample data if API fails

🐛 Troubleshooting
Common Issues
API Key Not Working

Ensure you've replaced YOUR_API_KEY_HERE in NewsApi.kt

Verify your NewsAPI account is activated

App Not Building

Clean project: Build → Clean Project

Rebuild project: Build → Rebuild Project

Check Kotlin and Compose version compatibility

No Articles Loading

Check internet connection

Verify API key is valid

App will show sample data if API fails

🤝 Contributing
We welcome contributions! Please see our Contributing Guide for details.

Fork the repository

Create a feature branch: git checkout -b feature/amazing-feature

Commit changes: git commit -m 'Add amazing feature'

Push to branch: git push origin feature/amazing-feature

Open a Pull Request

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

🙏 Acknowledgments

NewsAPI for providing free news data

Android Developers for excellent documentation

Jetpack Compose team for the modern UI toolkit

Kotlin team for the expressive language

📞 Support

If you have any questions or issues:

Check Troubleshooting section

Open an issue

Provide details about your environment and error logs

<div align="center">
Built with ❤️ using Kotlin, Jetpack Compose, and Modern Android Architecture

Star ⭐ the repo if you find this project helpful!

</div> ```
