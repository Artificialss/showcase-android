package com.artificialss.showcase.android.data.mock

import com.artificialss.showcase.android.domain.model.PortfolioItem

/**
 * Static, deterministic project list — no backend. Mirrors the portfolio
 * section shown on the Next.js and Compose Multiplatform showcase siblings.
 */
object PortfolioMockGenerator {

    fun generate(): List<PortfolioItem> = listOf(
        PortfolioItem(
            id = "showcase-android",
            title = "Showcase.Android",
            subtitle = "This app's own source — native Kotlin, Jetpack Compose, MVVM, and Clean Architecture with Koin.",
            tags = listOf("Kotlin", "Jetpack Compose", "MVVM"),
            url = "https://github.com/Artificialss/Showcase.Android",
        ),
        PortfolioItem(
            id = "showcase-ios",
            title = "Showcase.iOS",
            subtitle = "Native SwiftUI showcase app — MVVM, Clean Architecture, and the modern @Observable pattern.",
            tags = listOf("Swift", "SwiftUI", "MVVM"),
            url = "https://github.com/Artificialss/Showcase.iOS",
        ),
        PortfolioItem(
            id = "showcase-cmm",
            title = "Showcase.CMM",
            subtitle = "Compose Multiplatform showcase app for Android & iOS — MVP architecture, custom Canvas charts.",
            tags = listOf("Kotlin", "Compose Multiplatform", "MVP"),
            url = "https://github.com/Artificialss/Showcase.CMM",
        ),
        PortfolioItem(
            id = "showcase-nextjs",
            title = "Showcase.NextJS",
            subtitle = "A standalone Next.js landing page adapted from our real design system.",
            tags = listOf("Next.js", "TypeScript", "Tailwind"),
            url = "https://github.com/Artificialss/Showcase.NextJS",
        ),
        PortfolioItem(
            id = "papasar",
            title = "Papasar",
            subtitle = "AI-powered study platform helping students prepare for exams with adaptive question sets.",
            tags = listOf("Product", "AI", "Education"),
            url = "https://papasar.cr",
        ),
        PortfolioItem(
            id = "artificialss-ai",
            title = "Artificialss.ai",
            subtitle = "Our main website — software development, legal advisory, and AI training services.",
            tags = listOf("Next.js", "Firebase", "i18n"),
            url = "https://artificialss.ai",
        ),
    )
}
