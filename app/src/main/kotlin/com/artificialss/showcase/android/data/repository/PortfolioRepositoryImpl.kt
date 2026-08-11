package com.artificialss.showcase.android.data.repository

import com.artificialss.showcase.android.data.mock.PortfolioMockGenerator
import com.artificialss.showcase.android.domain.model.PortfolioItem
import com.artificialss.showcase.android.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PortfolioRepositoryImpl : PortfolioRepository {
    override fun getPortfolioItems(): Flow<List<PortfolioItem>> = flowOf(PortfolioMockGenerator.generate())
}
