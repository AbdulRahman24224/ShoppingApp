package com.example.presentation.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.base.ui.compose.bars.SearchViewBar
import com.example.base.ui.compose.sH
import com.example.base.ui.compose.sV
import com.example.base.ui.compose.text.CircleBackgroundText
import com.example.base.ui.compose.text.black12TextStyle
import com.example.base.ui.compose.text.blackSubtitleTextStyle
import com.example.base.ui.compose.text.hintTextStyle
import com.example.base.ui.compose.text.titleTextStyle
import com.example.base.ui.theme.AppColors
import com.example.domain_models.products.Product
import com.example.shoppingapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val viewModel: ProductsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setContent {

                val viewState = viewModel.viewState.collectAsState()
                val products = viewModel.productsList.collectAsState(listOf())

                val searchQueryState = remember { mutableStateOf("") }


                val debouncedSearchQueryState = remember(searchQueryState.value) {
                    mutableStateOf(searchQueryState.value)
                }

                LaunchedEffect(debouncedSearchQueryState.value) {

                    delay(500)
                    viewModel.onSearchQueryChanged(debouncedSearchQueryState.value)
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColors.LBlue100),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        text = stringResource(com.example.shoppingapp.R.string.products_list),
                        style = titleTextStyle()
                    )

                    sV(h = 2)

                    SearchViewBar(
                        query = searchQueryState.value,
                        hint = "what are you looking for?" ,
                        onClickSearch = {},
                        onValueChange = {
                            searchQueryState.value = it
                            debouncedSearchQueryState.value = it
                        }

                    )

                    AnimatedVisibility(visible = viewState.value.currentCategories.isNotEmpty()   )    {

                        com.example.base.ui.compose.buttons.IconButton(
                            backgroundColor = AppColors.Error ,
                            icon = Icons.Default.Clear,
                            onButtonClick = { viewModel.clearCategories()},
                            modifier =
                            Modifier
                                .padding(start = 12.dp)
                                .size(30.dp)
                        )
                    }

                   Text(
                       modifier = Modifier.padding(start = 12.dp),
                       text = "Products(${products.value.size}) " ,
                       style = blackSubtitleTextStyle())

                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        itemsIndexed(viewState.value.categories) { index, item ->

                            CircleBackgroundText(
                                modifier =
                                Modifier
                                    .padding(vertical = 12.dp, horizontal = 8.dp)
                                    .clickable { viewModel.onCategoryClicked(item) },
                                text = item,
                                circleBackgroundColor =
                                if (viewState.value.currentCategories.contains(item))
                                    AppColors.Orange
                                else
                                    AppColors.Grey200
                            )

                        }
                    }

                    sV(h = 2)

                    LazyColumn() {

                        itemsIndexed(products.value) { index, product ->

                            if (index % 2 == 0) {
                                ProductItem(
                                    product = product,
                                 onAddToCartClick = { viewModel.insertProductInCart(it) },
                                    onOpenDetails = { /*viewModel.onProductClicked(product)*/ }
                                )
                            } else {
                                SimpleProductItem(
                                    productItem = product,
                                    onClickToCart = { viewModel.insertProductInCart(it) },)
                            }
                        }
                    }
                }

            }
        }


    @Composable
    private fun ProductItem(
        product: Product,
        modifier: Modifier = Modifier,
        onAddToCartClick: (Product) -> Unit = {},
        onOpenDetails: (Product) -> Unit = {}

    ) {
        Card(
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth()
                .clickable { onOpenDetails(product) },
            elevation = 2.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = product.image)
                            .apply(block = fun ImageRequest.Builder.() {

                                crossfade(true)
                            }).build()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Inside
                )

                Text(
                    text = product.title,
                    style = blackSubtitleTextStyle().copy(fontWeight = FontWeight.Bold ),
                )

                Text(
                    text = product.category,
                    style = hintTextStyle()
                )

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Top,
                ) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .align(CenterVertically),
                        painter = painterResource(com.example.shoppingapp.R.drawable.ic_add),
                        contentDescription = null,
                        tint = AppColors.Yellow1
                    )
                    Text(
                        modifier = Modifier.align(CenterVertically),
                        text = "${product.rating.rate} (${product.rating.count})",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                }

                Text(
                    text = "$${product.price}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedButton(
                    onClick = { onAddToCartClick(product)},
                    modifier = Modifier
                        .size(40.dp)
                        .align(End),
                    shape = CircleShape,
                    border = BorderStroke(0.dp, Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        backgroundColor = AppColors.Yellow4
                    )
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = null,
                        tint = AppColors.White
                    )
                }


            }

        }
    }

    @Composable
    fun SimpleProductItem(
        modifier: Modifier = Modifier,
        productItem: Product,
        onClickToCart: (Product) -> Unit
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth()
                .clickable {}
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Row(Modifier.fillMaxWidth()) {

                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = productItem.image)
                                .apply(block = fun ImageRequest.Builder.() {

                                    crossfade(true)
                                }).build()
                        ),
                        modifier = Modifier.size(height = 100.dp, width = 100.dp),
                        contentDescription = null,
                    )

                    Column(Modifier.padding(start = 12.dp)) {

                        Text(
                            text = productItem.title,
                            style = blackSubtitleTextStyle().copy(fontWeight = FontWeight.Bold)
                        )

                        sV(h = 6)

                        Text(
                            text = productItem.description,
                            style = black12TextStyle().copy(color = AppColors.Grey500) ,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                        )

                        sV(h = 20)
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "$${productItem.price}",
                        fontWeight = FontWeight.Bold,
                        color = AppColors.Black,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 18.sp
                    )

                   com.example.base.ui.compose.buttons.IconButton(
                       onButtonClick = { onClickToCart(productItem) },
                       modifier = Modifier.size(46.dp)
                   )
                }

            }
        }
    }


}
