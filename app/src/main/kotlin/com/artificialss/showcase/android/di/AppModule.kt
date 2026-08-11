package com.artificialss.showcase.android.di

import com.artificialss.showcase.android.data.repository.PortfolioRepositoryImpl
import com.artificialss.showcase.android.domain.repository.PortfolioRepository
import com.artificialss.showcase.android.domain.usecase.GetPortfolioItemsUseCase
import com.artificialss.showcase.android.ui.feature.home.HomeViewModel
import com.artificialss.showcase.android.ui.feature.portfolio.PortfolioViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<PortfolioRepository> { PortfolioRepositoryImpl() }
    factory { GetPortfolioItemsUseCase(get()) }

    viewModel { HomeViewModel() }
    viewModel { PortfolioViewModel(get()) }
}
