package me.ldgomm.morpheus.app.main.viewmodel.offer

//
//  OfferViewModel.kt
//  Morpheus
//
//  Created by José Ruiz on 11/1/23.
//

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ldgomm.morpheus.app.main.model.remote.entity.offer.OfferApiResponse
import me.ldgomm.morpheus.app.main.model.remote.service.HttpRequestState
import me.ldgomm.morpheus.app.main.model.remote.service.offer.OfferServiceable
import javax.inject.Inject

@HiltViewModel
class OfferViewModel @Inject constructor(private val offerServiceable: OfferServiceable) :
        ViewModel() {
    private val _offerApiResponse: MutableState<HttpRequestState<OfferApiResponse>> =
        mutableStateOf(HttpRequestState.Idle)
    val clientApiResponse: State<HttpRequestState<OfferApiResponse>> = _offerApiResponse

    init {
        getOffers()
    }

    private fun getOffers() {
        _offerApiResponse.value = HttpRequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = offerServiceable.getOffers()
                _offerApiResponse.value = HttpRequestState.Success(response)
            }
        } catch (e: Exception) {
            _offerApiResponse.value = HttpRequestState.Error(e)
        }
    }
}