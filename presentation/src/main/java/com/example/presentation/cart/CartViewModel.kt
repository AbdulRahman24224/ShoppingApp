package com.example.presentation.cart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain_models.products.CartProduct
import com.example.domain_models.products.Product
import com.example.domain_models.products.toProduct
import com.example.usecases.products.GetCartProductsUseCase
import com.example.usecases.products.InsertProductInCartUseCase
import com.example.usecases.products.RemoveProductFromCartUseCase
import com.example.usecases.products.UpdateProductQuantityInCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val updateProductQuantityInCartUseCase: UpdateProductQuantityInCartUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase
) : ViewModel() {

    private val isCart = true

    private val _productCartList = MutableStateFlow<List<CartProduct>>(emptyList())
    val productCartList = _productCartList.asStateFlow()

    init {
        getAllProductCartList()
    }

    private fun getAllProductCartList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCartProductsUseCase.invoke().collect { values ->
                _productCartList.value = values
            }

        }
    }

    fun updateProductQuantity ( product: CartProduct){

        viewModelScope.launch(Dispatchers.IO) {

            if (product.quantity == 0) {
                DeleterProduct(product)
            } else {
                updateProductQuantityInCartUseCase(product.toProduct(), product.quantity)

            }
        }
    }

    fun deleteProductFromCart ( product: CartProduct){

        viewModelScope.launch(Dispatchers.IO) {
            DeleterProduct(product)
        }
    }

    private suspend fun CartViewModel.DeleterProduct(product: CartProduct) {
        removeProductFromCartUseCase(product.id)
        _productCartList.update {
            _productCartList.value.toMutableList().filter { it.id == product.id }
        }
    }


}