package co.id.aminfaruq.core.di

import androidx.room.Room
import co.id.aminfaruq.core.data.mapper.local.AyatEntityMapper
import co.id.aminfaruq.core.data.mapper.local.SuratEntityMapper
import co.id.aminfaruq.core.data.mapper.remote.ItemAyatMapper
import co.id.aminfaruq.core.data.mapper.remote.ItemSuratMapper
import co.id.aminfaruq.core.data.repository.AyatRepositoryImpl
import co.id.aminfaruq.core.data.repository.SuratRepositoryImpl
import co.id.aminfaruq.core.data.source.local.room.QuranDatabase
import co.id.aminfaruq.core.data.source.remote.network.ApiService
import co.id.aminfaruq.core.domain.repository.AyatRepository
import co.id.aminfaruq.core.domain.repository.SuratRepository
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

val databaseModule = module {
    single { getExecutor() }
    factory { get<QuranDatabase>().quranDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            QuranDatabase::class.java, "quran.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.npoint.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/nrugqwM24KAN2RsLABcuXPSxvqJmYofPkUpY0mr92FE=")
            .add(hostname, "sha256/YLh1dUR9y6Kja30RrAn7JKnbQG/uEtLMkBgFF2Fuihg=")
            .add(hostname, "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.npoint.io/99c279bb173a6e28359c/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }

}

fun getExecutor(): Executor {
    return Executors.newFixedThreadPool(2)
}

val repositoryModule = module {
    single {
        SuratRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get()
        ) as SuratRepository
    }

    single {
        AyatRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get()
        ) as AyatRepository
    }
}

val mapperModule = module {
    single { ItemSuratMapper() }
    single { ItemAyatMapper() }
    single { SuratEntityMapper() }
    single { AyatEntityMapper() }
}