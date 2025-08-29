package com.example.cryptoapp.homescreen.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.core.presentation.base.BaseViewModel
import com.example.cryptoapp.data.presentation.mappers.toCryptoDomainModel
import com.example.cryptoapp.data.presentation.mappers.toCryptoUiModel
import com.example.cryptoapp.data.presentation.mappers.toCryptoUpdateModel
import com.example.cryptoapp.detailscreen.presentation.navigation.DetailNavigation
import com.example.cryptoapp.domain.usecases.CryptoListUpdatesUseCase
import com.example.cryptoapp.domain.usecases.GetCryptosUseCase
import com.example.cryptoapp.domain.usecases.MapUpdateToCryptoUseCase
import com.example.cryptoapp.domain.usecases.SubscribeCryptoListUseCase
import com.example.cryptoapp.homescreen.presentation.events.HomeEvents
import com.example.cryptoapp.homescreen.presentation.navigation.HomeNavigation
import com.example.cryptoapp.homescreen.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cryptoListUpdatesUseCase: CryptoListUpdatesUseCase,
    private val mapUpdateToCryptoUseCase: MapUpdateToCryptoUseCase,
    private val getCryptosUseCase: GetCryptosUseCase,
    private val subscribeCryptoListUseCase: SubscribeCryptoListUseCase,
) : BaseViewModel<HomeState, HomeEvents, HomeNavigation>() {
    override val initialState: HomeState
        get() = HomeState()


    init {
        getCryptos()
        subscribeCryptoListUseCase(listOf("BTC", "DOGE", "BNB", "DOT", "DASH", "NEXO", "AMPL"))
        observeUpdates()
    }

    fun onEvent(event: HomeEvents) {
        when (event) {
          is HomeEvents.Navigation -> {homeNavigate(event.homeNavigation)}
        }
    }
    fun homeNavigate(navigation: HomeNavigation){
        when(navigation){
            is HomeNavigation.HomeScreenToDetailScreen -> navigate(
                HomeNavigation.HomeScreenToDetailScreen(
                    navigation.cryptoName
                )
            )
            DetailNavigation.DetailScreenFromHomeScreen -> TODO()
        }
    }



    fun getCryptos() {
        updateState {
            it.copy(cryptoList = getCryptosUseCase().map { it.toCryptoUiModel() })
        }
    }

    private fun observeUpdates() {
        viewModelScope.launch {
            cryptoListUpdatesUseCase().collectLatest { result ->
                if(result.isSuccess){
                    val updates = result.getOrNull()
                    val currentList = state.value.cryptoList.toMutableList()
                    updates?.forEach { update ->
                        val index = currentList.indexOfFirst { it.cryptoName == update.cryptoName }
                        if (index != -1) {
                            val old = currentList[index]
                            currentList[index] = mapUpdateToCryptoUseCase(update.toCryptoUpdateModel(), old.toCryptoDomainModel()).toCryptoUiModel()
                        }
                    }
                    updateState {
                        it.copy(cryptoList = currentList)
                    }
                }

            }
        }
    }
}
