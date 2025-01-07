package productDetailPage.domain.useCases

interface RemoveProductFromCart {
    suspend operator fun invoke(id: String)
}