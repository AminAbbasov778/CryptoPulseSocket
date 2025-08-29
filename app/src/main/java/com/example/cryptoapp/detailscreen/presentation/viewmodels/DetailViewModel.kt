package com.example.cryptoapp.detailscreen.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.core.presentation.base.BaseViewModel
import com.example.cryptoapp.data.presentation.mappers.toCryptoDomainModel
import com.example.cryptoapp.data.presentation.mappers.toCryptoUiModel
import com.example.cryptoapp.data.presentation.mappers.toCryptoUpdateModel
import com.example.cryptoapp.detailscreen.presentation.events.DetailEvents
import com.example.cryptoapp.detailscreen.presentation.navigation.DetailNavigation
import com.example.cryptoapp.detailscreen.presentation.state.DetailState
import com.example.cryptoapp.domain.usecases.CryptoDetailUpdatesUseCase
import com.example.cryptoapp.domain.usecases.MapUpdateToCryptoUseCase
import com.example.cryptoapp.domain.usecases.SubscribeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val cryptoDetailUpdatesUseCase: CryptoDetailUpdatesUseCase,
    private val subscribeDetailUseCase: SubscribeDetailUseCase,
    private val manUpToCryptoUseCase: MapUpdateToCryptoUseCase,
) : BaseViewModel<DetailState, DetailEvents, DetailNavigation>() {
    override val initialState: DetailState
        get() = DetailState()

    fun onEvent(event: DetailEvents) {
        when (event) {
            is DetailEvents.Navigation -> {
                detailNavigate(event.detailNavigation)
            }

            is DetailEvents.OnGoToEndClicked ->onGoToEndClicked(event.goToEnd)
            is DetailEvents.OnAutoScrollClicked -> onAutoScrollClicked(event.isAutoScrollEnabled)
        }
    }

    fun detailNavigate(navigation: DetailNavigation) {
        when (navigation) {
            is DetailNavigation.DetailScreenFromHomeScreen -> navigate(DetailNavigation.DetailScreenFromHomeScreen)
        }
    }
    fun onAutoScrollClicked(isAutoScrollEnabled : Boolean){
        updateState { it.copy(isAutoScrollEnabled = isAutoScrollEnabled) }
    }

    fun onGoToEndClicked(goToEnd : Boolean){
        updateState { it.copy(goToEnd = goToEnd) }
    }

    fun getDetailState(coinName: String) {
        setLoading(true)

        if (!state.value.subscribedCoins.contains(coinName)) {
            updateState {
                it.copy(subscribedCoins = it.subscribedCoins + coinName)
            }
            subscribeDetailUseCase(coinName)
        }

        viewModelScope.launch {
            cryptoDetailUpdatesUseCase().collectLatest { result ->
                if(result.isSuccess){
                    val update = result.getOrNull()
                    if (update?.cryptoName == coinName) {
                        val oldModel = state.value.detailState
                        val newModel = manUpToCryptoUseCase(
                            update.toCryptoUpdateModel(),
                            oldModel?.toCryptoDomainModel()
                        ).toCryptoUiModel()

                        updateState { it.copy(detailState = newModel) }



                    }
                }else{
                    if (isLoading.value) {
                        setLoading(false)
                    }
                }

            }
        }
    }


}