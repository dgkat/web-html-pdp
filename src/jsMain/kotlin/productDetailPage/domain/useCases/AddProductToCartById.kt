package productDetailPage.domain.useCases

interface AddProductToCartById {
    suspend operator fun invoke(id: String)
}