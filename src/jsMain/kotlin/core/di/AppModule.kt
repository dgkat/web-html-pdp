package core.di

import core.data.DBTest
import core.util.TimeProvider
import kotlinx.datetime.Clock
import org.koin.dsl.module

val appModule = module {
    single<Clock> { Clock.System }
    factory { TimeProvider(get()) }

    factory { DBTest(get()) }
}