package com.artificialss.showcase.android.domain.usecase

import com.artificialss.showcase.android.domain.model.PortfolioItem
import com.artificialss.showcase.android.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow

class GetPortfolioItemsUseCase(private val repository: PortfolioRepository) {
    operator fun invoke(): Flow<List<PortfolioItem>> = repository.getPortfolioItems()
}
