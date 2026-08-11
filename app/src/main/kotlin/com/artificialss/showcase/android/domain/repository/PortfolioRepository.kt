package com.artificialss.showcase.android.domain.repository

import com.artificialss.showcase.android.domain.model.PortfolioItem
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    fun getPortfolioItems(): Flow<List<PortfolioItem>>
}
