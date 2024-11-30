package productDetailPage.domain.useCases

import kotlinx.coroutines.coroutineScope

class AddProductToCartAndRecentlySeenImpl(
    private val addProductToCartById: AddProductToCartById,
    private val addProductToRecentlySeen: AddProductToRecentlySeen
):AddProductToCartAndRecentlySeen {
    override suspend fun invoke() {
        coroutineScope {
            addProductToCartById
        }
        coroutineScope {
            addProductToRecentlySeen
        }
    }
}