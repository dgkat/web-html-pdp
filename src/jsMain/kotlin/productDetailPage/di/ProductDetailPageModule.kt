package productDetailPage.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module
import productDetailPage.data.ProductRepositoryImpl
import productDetailPage.data.local.mappers.DomainToLocalProductMapper
import productDetailPage.data.local.mappers.LocalToDomainProductMapper
import productDetailPage.data.remote.mappers.RemoteToDomainExtendedProductInfoMapper
import productDetailPage.data.remote.mappers.RemoteToDomainFeatureMapper
import productDetailPage.data.remote.mappers.RemoteToDomainProductMapper
import productDetailPage.data.remote.service.ProductService
import productDetailPage.data.remote.service.ProductServiceImpl
import productDetailPage.domain.ProductRepository
import productDetailPage.domain.useCases.*
import productDetailPage.presentation.ProductDetailPageViewModel
import productDetailPage.presentation.mappers.DomainToUiExtendedProductInfoMapper
import productDetailPage.presentation.mappers.DomainToUiFeatureMapper
import productDetailPage.presentation.mappers.DomainToUiProductMapper
import productDetailPage.presentation.mappers.UiToDomainProductMapper

val productDetailPageModule = module {
    //Data
    single { Json { ignoreUnknownKeys = true } }
    single<ProductService> { ProductServiceImpl(json = get()) }
    single<ProductRepository> {
        ProductRepositoryImpl(
            productService = get(),
            productDao = get(),
            remoteToDomainProductMapper = get(),
            remoteToDomainExtendedProductInfoMapper = get(),
            localToDomainProductMapper = get(),
            domainToLocalProductMapper = get()
        )
    }
    single { LocalToDomainProductMapper() }
    single { DomainToLocalProductMapper() }
    single { RemoteToDomainFeatureMapper() }
    single { RemoteToDomainExtendedProductInfoMapper(get()) }
    single { RemoteToDomainProductMapper() }

    //Domain
    single<GetProductByIdUseCase> { GetProductByIdUseCaseImpl(get()) }
    single<GetExtendedProductInfoById> { GetExtendedProductInfoByIdImpl(get()) }
    single<ObserveProduct> { ObserveProductImpl(get(), get()) }
    single<AddProductToCart> { AddProductToCartImpl(get()) }
    single<RemoveProductFromCart> { RemoveProductFromCartImpl(get()) }

    //Pres
    single { DomainToUiFeatureMapper() }
    single { DomainToUiExtendedProductInfoMapper(get()) }
    single { DomainToUiProductMapper(get()) }
    single { ProductDetailPageViewModel(get(), get(), get(), get(), get()) }
    single { UiToDomainProductMapper() }
}