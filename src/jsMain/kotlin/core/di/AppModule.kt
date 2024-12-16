package core.di

import core.data.DBTest
import core.data.ProductDao
import core.data.ProductDaoImpl
import core.data.ProductDataBaseFactory
import core.util.TimeProvider
import kotlinx.datetime.Clock
import org.koin.dsl.module

val appModule = module {
    single<Clock> { Clock.System }
    factory { TimeProvider(get()) }

    factory { DBTest(get(), get()) }

    // Local
    single<ProductDataBaseFactory> { ProductDataBaseFactory() }

    factory<ProductDao> { ProductDaoImpl(get()) }
}