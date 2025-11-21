# 📰 Daily Digest - Smart News Aggregator

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.21-blue.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.7-brightgreen.svg)](https://developer.android.com/jetpack/compose)
[![MVVM](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Clean%20Architecture-orange.svg)](https://developer.android.com/jetpack/guide)
[![Room](https://img.shields.io/badge/Database-Room%20SQLite-red.svg)](https://developer.android.com/training/data-storage/room)
[![Retrofit](https://img.shields.io/badge/Networking-Retrofit%20%2B%20OkHttp-purple.svg)](https://square.github.io/retrofit/)
[![Hilt](https://img.shields.io/badge/DI-Dagger%20Hilt-yellow.svg)](https://dagger.dev/hilt/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

A modern Android news app built with Jetpack Compose that delivers personalized news feeds with offline reading capabilities using real NewsAPI integration.

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
