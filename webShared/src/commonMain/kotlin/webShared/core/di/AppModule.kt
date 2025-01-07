package commonMain.kotlin.webShared.core.di

import commonMain.kotlin.webShared.core.data.ProductDao
import commonMain.kotlin.webShared.core.data.ProductDaoImpl
import commonMain.kotlin.webShared.core.data.ProductDataBaseFactory
import commonMain.kotlin.webShared.core.util.TimeProvider
import kotlinx.datetime.Clock
import org.koin.dsl.module

val appModule = module {
    single<Clock> { Clock.System }
    factory { TimeProvider(get()) }

    // Local
    single<ProductDataBaseFactory> { ProductDataBaseFactory() }

    factory<ProductDao> { ProductDaoImpl(get()) }
}