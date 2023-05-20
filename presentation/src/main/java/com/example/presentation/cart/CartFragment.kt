package com.example.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.base.ui.compose.placeHolders.EmptyContent
import com.example.base.ui.compose.sH
import com.example.base.ui.compose.sV
import com.example.base.ui.compose.text.black12TextStyle
import com.example.base.ui.compose.text.blackSubtitleTextStyle
import com.example.base.ui.compose.text.subtitleTextStyle
import com.example.base.ui.compose.text.titleTextStyle
import com.example.base.ui.compose.text.toolbarTextStyle
import com.example.base.ui.theme.AppColors
import com.example.base.ui.theme.AppColors.Black
import com.example.domain_models.products.CartProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt


@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setContent {

                val products = viewModel.productCartList.collectAsState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColors.LBlue100)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        text = "My Cart",
                        style = titleTextStyle()
                    )

                    sV(h = 16)

                    ListContentCart(
                        cartProducts = products.value,
                        onClickDeleteCart = { productItem ->
                            viewModel.deleteProductFromCart(productItem)
                        }
                    )
                }

            }
        }

    @Composable
    fun ListContentCart(
        modifier: Modifier = Modifier,
        cartProducts: List<CartProduct>,
        onClickDeleteCart: (CartProduct) -> Unit
    ) {

        Box(
            Modifier.fillMaxSize()
        ) {


            if (cartProducts.isNotEmpty()) {
                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 32.dp, bottom = 100.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(cartProducts) { index, product ->
                        ContentCart(
                            productItem = product,
                            onClickDeleteCart = { productItem ->
                                onClickDeleteCart.invoke(productItem)
                            },
                            onProductQuantityChange = { productItem ->
                                viewModel.updateProductQuantity(productItem)
                            }
                        )
                    }
                }


                Column(Modifier.align(Alignment.BottomCenter)) {


                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = 2.dp,
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                                .background(AppColors.White)

                        ) {

                            Text(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.CenterVertically),
                                text = "Total :",
                                style = blackSubtitleTextStyle()
                            )

                            Text(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .align(Alignment.CenterVertically),
                                text = "$${
                                    cartProducts.sumOf { it.price * it.quantity }.roundToInt()
                                }",
                                style = blackSubtitleTextStyle().copy(fontWeight = FontWeight.Bold)
                            )

                            Spacer(modifier = Modifier.weight(1f))



                            TextButton(
                                onClick = {
                                    Toast.makeText(
                                        requireContext(),
                                        "Checking out",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                Text(
                                    text = "Checkout" ,
                                    style = subtitleTextStyle().copy(color = AppColors.Yellow4 ))
                            }
                        }
                    }

                }
            } else {
                EmptyContent()
            }

        }

    }

    @Composable
    fun ContentCart(
        modifier: Modifier = Modifier,
        productItem: CartProduct,
        onClickDeleteCart: (CartProduct) -> Unit,
        onProductQuantityChange: (CartProduct) -> Unit
    ) {

        val quantity = remember { mutableStateOf(productItem.quantity) }

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
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


                ProductDescription(
                    modifier = modifier,
                    productItem = productItem,
                    onClickDeleteCart = onClickDeleteCart
                )

                ProductCounter(
                    quantity.value,
                    onQuantityChange = {
                        quantity.value = it
                        onProductQuantityChange.invoke(productItem.copy(quantity = it))
                    }

                )


            }
        }
    }

    @Composable
    private fun ProductDescription(
        modifier: Modifier,
        productItem: CartProduct,
        onClickDeleteCart: (CartProduct) -> Unit
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(width = 64.dp, height = 90.dp)
                    .padding(start = 8.dp),
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = productItem.image)
                        .apply(block = fun ImageRequest.Builder.() {

                            crossfade(true)
                        }).build()
                ),
                contentDescription = ""
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp),
            ) {
                Text(
                    modifier = Modifier.sizeIn(maxWidth = 150.dp),
                    text = productItem.title,
                    style = blackSubtitleTextStyle(),

                    )

                sV(h = 8)

                Text(
                    text = productItem.description,
                    style = black12TextStyle().copy(color = AppColors.Grey200),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column() {

                Text(
                    text = "$${productItem.price}",
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    fontSize = 18.sp,
                )

                sV(h = 24)
                Image(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 8.dp)
                        .clickable {
                            onClickDeleteCart.invoke(productItem)
                        },
                    imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.DarkGray)
                )

            }


        }
    }

    @Composable
    private fun ProductCounter(
        quantity: Int,
        onQuantityChange: (Int) -> Unit
    ) {
        Row(
            modifier = Modifier.padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        )
        {


            com.example.base.ui.compose.buttons.IconButton(
                icon = Icons.Default.Clear,
                onButtonClick = { if (quantity > 0) onQuantityChange(quantity - 1) },
                modifier = Modifier.size(35.dp)
            )

            Text(
                text = quantity.toString(),
                style = blackSubtitleTextStyle()
            )

            com.example.base.ui.compose.buttons.IconButton(
                onButtonClick = { onQuantityChange(quantity + 1) },
                modifier = Modifier.size(35.dp)
            )

        }
    }
}
