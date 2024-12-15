package productDetailPage.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.koin.mp.KoinPlatform.getKoin
import productDetailPage.presentation.uiComponents.ProductDetailPage

@Composable
fun ProductDetailPageScreen(viewModel: ProductDetailPageViewModel = getKoin().get()) {
    //viewModel: ProductDetailPageViewModel = remember { getKoin().get() }
    //TODO when viewmodel no longer a singleton
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Div {
            Text("Loading...")
        }
    }

    state.error?.let {
        Div {
            Text("Error: $it")
        }
    }

    state.product?.let {
        ProductDetailPage(uiProduct = it, isInCart = state.isInCart, onEvent = viewModel::onEvent)
    }
}