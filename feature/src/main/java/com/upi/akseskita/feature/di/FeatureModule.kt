package com.upi.akseskita.feature.di

import com.upi.akseskita.core.domain.usecase.FacilityInteractor
import com.upi.akseskita.core.domain.usecase.FacilityUseCase
import com.upi.akseskita.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FacilityUseCase> { FacilityInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}