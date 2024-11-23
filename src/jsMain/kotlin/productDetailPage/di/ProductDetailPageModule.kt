package productDetailPage.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module
import productDetailPage.data.ProductRepositoryImpl
import productDetailPage.data.remote.service.ProductService
import productDetailPage.data.remote.service.ProductServiceImpl
import productDetailPage.data.remote.mappers.RemoteToDomainProductMapper
import productDetailPage.domain.ProductRepository
import productDetailPage.domain.useCases.GetProductByIdUseCase
import productDetailPage.domain.useCases.GetProductByIdUseCaseImpl
import productDetailPage.presentation.ProductDetailPageViewModel
import productDetailPage.presentation.mappers.DomainToUiProductMapper

val productDetailPageModule = module {
    //Data
    single { Json { ignoreUnknownKeys = true } }
    single<ProductService> { ProductServiceImpl(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    single { RemoteToDomainProductMapper() }

    //Domain
    single<GetProductByIdUseCase> { GetProductByIdUseCaseImpl(get()) }

    //Pres
    single { DomainToUiProductMapper() }
    single { ProductDetailPageViewModel(get(), get()) }
}