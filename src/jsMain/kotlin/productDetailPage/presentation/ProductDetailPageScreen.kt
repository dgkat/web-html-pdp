package productDetailPage.presentation

import ProductState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import productDetailPage.presentation.uiComponents.ProductDetailPage

@Composable
fun ProductDetailPageScreen(viewModel: ProductDetailPageViewModel = ProductDetailPageViewModel()) {
    val state by viewModel.state.collectAsState()


    when (state) {
        is ProductState.Loading -> {
            Div {
                Text("Loading...")
            }
        }

        is ProductState.Success -> {
            val product = (state as ProductState.Success).uiProduct
            ProductDetailPage(product)
        }

        is ProductState.Error -> {
            Div {
                Text("Error: ${(state as ProductState.Error).message}")
            }
        }
    }
}