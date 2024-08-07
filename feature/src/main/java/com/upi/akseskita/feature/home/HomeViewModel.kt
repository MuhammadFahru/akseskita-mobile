package com.upi.akseskita.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upi.akseskita.core.domain.model.PlaceModel
import com.upi.akseskita.core.domain.usecase.FacilityUseCase
import com.upi.akseskita.core.ui.UiState
import com.upi.akseskita.core.ui.asUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class HomeViewModel(private val facilityUseCase: FacilityUseCase) : ViewModel() {
    private val _allFacilitiesState: MutableStateFlow<UiState<List<PlaceModel>>> =
        MutableStateFlow(UiState.Loading)
    val allFacilitiesState: StateFlow<UiState<List<PlaceModel>>> get() = _allFacilitiesState

    private val _topRatedFacilitiesState: MutableStateFlow<UiState<List<PlaceModel>>> =
        MutableStateFlow(UiState.Loading)
    val topRatedFacilitiesState: StateFlow<UiState<List<PlaceModel>>> get() = _topRatedFacilitiesState

    fun getAllFacilities() {
        viewModelScope.launch {
            facilityUseCase.getAllFacilities()
                .transform { list ->
                    emit(list.take(5))
                }
                .asUiState()
                .collectLatest {
                    _allFacilitiesState.emit(it)
                }
        }
    }

    fun getTopRatedFacilities() {
        viewModelScope.launch {
            facilityUseCase.getTopRatedFacilities().asUiState().collectLatest {
                _topRatedFacilitiesState.emit(it)
            }
        }
    }
}