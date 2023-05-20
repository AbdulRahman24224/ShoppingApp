package com.example.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product
import com.example.usecases.products.GetCachedProductsUseCase
import com.example.usecases.products.GetProductsByCategoryUseCase
import com.example.usecases.products.GetRemoteProductsUseCase
import com.example.usecases.products.InsertProductInCartUseCase
import com.example.usecases.products.SearchProductsByKeywordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getProductsUseCase: GetRemoteProductsUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val searchProductsByKeywordUseCase: SearchProductsByKeywordUseCase,
    private val insertProductInCartUseCase: InsertProductInCartUseCase,
    private val getCachedProductsUseCase: GetCachedProductsUseCase

) : ViewModel() {

    private val _navigate = MutableSharedFlow<Int>()
    val navigate = _navigate.asSharedFlow()

    private val _viewState = MutableStateFlow(ProductsListViewState())
    val viewState = _viewState.asStateFlow()

    private val _productsList = MutableStateFlow<List<Product>>(listOf())
    private val _productsListFiltered = MutableStateFlow<List<Product>>(listOf())
    val productsList = _productsListFiltered.asSharedFlow()

    private val _searchKeyword = MutableStateFlow("")

    init {
        getProducts()
    }

   private fun getProducts() {

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getProductsUseCase()
            }

            when (result) {
                is DataResult.Success -> {

                    val categories = result.data.map { it.category }.distinct()
                    val products = result.data

                    _productsList.emit(products)
                    _productsListFiltered.emit(products)

                    _viewState.update { it.copy(categories = categories) }

                }

                is DataResult.Failure -> {
                    when (result.throwable) {
                        is IOException -> {}
                        is SocketTimeoutException -> { /* log event or retry request*/}
                        is UnknownHostException -> { /* log event or retry request*/ }
                        else -> {/*   check json bodyError*/ }
                    }
                }
            }
        }

    }

    fun insertProductInCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            insertProductInCartUseCase.invoke(product)
        }
    }

    fun onCategoryClicked(category: String) {

        viewModelScope.launch {

            withContext(Dispatchers.IO) {

                val currentCategorires = _viewState.value.currentCategories.toMutableList()

                if (currentCategorires.contains(category))
                    currentCategorires.remove(category)
                else
                    currentCategorires.add(category)

                _viewState.update { it.copy(currentCategories = currentCategorires) }

                if (_searchKeyword.value.isNotBlank()){

                    searchProductsByKeywordUseCase(
                        _searchKeyword.value,
                        currentCategorires
                    )
                        .collect() {
                            _productsListFiltered.emit(it)
                        }
                }else{

                    if (currentCategorires.isEmpty())
                        getCachedProductsUseCase()
                            .collect() {
                                _productsListFiltered.emit(it)
                            }
                    else {

                        getProductsByCategoryUseCase(currentCategorires)
                            .collect() {
                                _productsListFiltered.emit(it)
                            }

                    }

                }

            }

        }

    }

    fun onSearchQueryChanged(keyword: String) {

        viewModelScope.launch {

            _searchKeyword.update { keyword }

            withContext(Dispatchers.IO) {

                searchProductsByKeywordUseCase(keyword, _viewState.value.currentCategories)
                    .collect() {
                        _productsListFiltered.emit(it)
                    }

            }

        }

    }

    fun clearCategories() {
        _viewState.update { it.copy(currentCategories = listOf()) }

        if (_searchKeyword.value.isEmpty()) {

            viewModelScope.launch {
                getCachedProductsUseCase()
                    .collect() {
                        _productsListFiltered.emit(it)
                    }
            }
        }

    }
}