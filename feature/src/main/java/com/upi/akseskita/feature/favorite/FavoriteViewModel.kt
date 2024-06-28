package com.upi.akseskita.feature.favorite

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

class FavoriteViewModel(private val facilityUseCase: FacilityUseCase) : ViewModel() {
    private val _allFacilitiesState: MutableStateFlow<UiState<List<PlaceModel>>> = MutableStateFlow(
        UiState.Loading)
    val allFacilitiesState: StateFlow<UiState<List<PlaceModel>>> get() = _allFacilitiesState

    fun getAllFacilities() {
        viewModelScope.launch {
            facilityUseCase.getAllFacilities().asUiState().collectLatest {
                _allFacilitiesState.emit(it)
            }
        }
    }
}