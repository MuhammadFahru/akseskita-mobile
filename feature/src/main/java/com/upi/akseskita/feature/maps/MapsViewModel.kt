package com.upi.akseskita.feature.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upi.akseskita.core.domain.model.PlaceModel
import com.upi.akseskita.core.domain.usecase.FacilityUseCase
import com.upi.akseskita.core.ui.UiState
import com.upi.akseskita.core.ui.asUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MapsViewModel(private val facilityUseCase: FacilityUseCase) : ViewModel() {
    private val _topRatedFacilitiesState: MutableStateFlow<UiState<List<PlaceModel>>> = MutableStateFlow(
        UiState.Loading)
    val topRatedFacilitiesState: StateFlow<UiState<List<PlaceModel>>> get() = _topRatedFacilitiesState

    fun getTopRatedFacilities() {
        viewModelScope.launch {
            facilityUseCase.getTopRatedFacilities().asUiState().collectLatest {
                _topRatedFacilitiesState.emit(it)
            }
        }
    }
}