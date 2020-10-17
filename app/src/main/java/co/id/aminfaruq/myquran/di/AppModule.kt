package co.id.aminfaruq.myquran.di

import co.id.aminfaruq.core.domain.usecase.AyatInteractor
import co.id.aminfaruq.core.domain.usecase.AyatUseCase
import co.id.aminfaruq.core.domain.usecase.SuratInteractor
import co.id.aminfaruq.core.domain.usecase.SuratUseCase
import co.id.aminfaruq.myquran.ui.ayat.AyatVM
import co.id.aminfaruq.myquran.ui.surat.SuratVM
import org.koin.dsl.module

//model

val useCaseModule = module {
    factory<SuratUseCase> { SuratInteractor(get()) }
    factory<AyatUseCase> { AyatInteractor(get()) }
}

val vieModelModule = module {
    single { SuratVM(get()) }
    single { AyatVM(get()) }
}
