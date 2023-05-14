package com.example.shoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain_models.network.DataResult
import com.example.usecases.products.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _navigate = MutableSharedFlow<Int>()
    val navigate = _navigate.asSharedFlow()

    private val _viewState = MutableStateFlow("String")
    val viewState = _viewState.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts(){

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getProductsUseCase()
            }

            when (result) {
                is DataResult.Success -> {

                    _viewState.update { result.data.toString() }

                }
                is DataResult.Failure -> Unit
            }
        }

    }
}