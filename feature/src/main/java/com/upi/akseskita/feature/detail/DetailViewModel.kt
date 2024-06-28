package com.upi.akseskita.feature.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upi.akseskita.core.domain.model.DetailPlaceModel
import com.upi.akseskita.core.domain.usecase.FacilityUseCase
import com.upi.akseskita.core.ui.UiState
import com.upi.akseskita.core.ui.asUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailViewModel(private val facilityUseCase: FacilityUseCase) : ViewModel() {
    private val _detailDataState: MutableStateFlow<UiState<DetailPlaceModel>> = MutableStateFlow(
        UiState.Loading
    )
    val detailDataState: StateFlow<UiState<DetailPlaceModel>> get() = _detailDataState

    fun getDetailFacility(id: Int) {
        viewModelScope.launch {
            facilityUseCase.getDetailFacilities(id).asUiState().collectLatest {
                Log.i("DetailDataViewModel", "$it")
                _detailDataState.emit(it)
            }
        }
    }
}