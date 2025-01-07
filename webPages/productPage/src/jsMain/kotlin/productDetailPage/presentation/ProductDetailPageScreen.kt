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

    /*DisposableEffect(Unit) {
        onDispose {
            viewModel.onCleared()
            println("onDispose called")
        }
    }*/
    //Navigation
    /*val context = LocalContext.current // Needed for some navigation methods

    // Collect navigation event
    val navigationEvent = remember { viewModel.navigationEvent }
    LaunchedEffect(Unit) {
        navigationEvent.collect { url ->
            // Perform navigation when event is emitted
            window.location.href = url
        }
    }*/

    //On click call viewModel.fetchProductAndNavigate() to trigger navigation

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
        ProductDetailPage(uiProduct = it, extendedProductInfo = state.extendedProductInfo, isInCart = state.isInCart, onEvent = viewModel::onEvent)
    }
}