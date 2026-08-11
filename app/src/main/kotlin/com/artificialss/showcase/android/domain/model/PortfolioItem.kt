package com.artificialss.showcase.android.domain.model

data class PortfolioItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val tags: List<String>,
    val url: String,
)
