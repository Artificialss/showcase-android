package com.artificialss.showcase.android.ui.feature.portfolio

import com.artificialss.showcase.android.domain.model.PortfolioItem

sealed class PortfolioUiState {
    data object Loading : PortfolioUiState()
    data class Success(val items: List<PortfolioItem>) : PortfolioUiState()
}
