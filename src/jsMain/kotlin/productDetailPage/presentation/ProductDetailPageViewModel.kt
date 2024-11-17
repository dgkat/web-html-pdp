package productDetailPage.presentation

import ProductState
import getProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailPageViewModel {
    private val _state = MutableStateFlow<ProductState>(ProductState.Loading)
    val state: StateFlow<ProductState> = _state

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        CoroutineScope(Dispatchers.Main).launch {
            _state.value = try {
                val product = getProduct()
                ProductState.Success(product)
            } catch (e: Exception) {
                ProductState.Error("Failed to load product: ${e.message}")
            }
        }
    }
}