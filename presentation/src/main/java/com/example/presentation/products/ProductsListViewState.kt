package com.example.presentation.products

data class ProductsListViewState (
    val isLoading : Boolean = false,
    val categories:List<String> = listOf(),
    val currentCategories: List<String> = listOf(),
    val searchKeyword : String ="",
)