package com.abdallah_abdelazim.products_catalog.data.di

import android.content.Context
import com.abdallah_abdelazim.products_catalog.data.BASE_URL
import com.abdallah_abdelazim.products_catalog.data.remote.api.ProductApi
import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRepository
import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRepositoryImpl
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    /* Remote */

    single { provideOkHttpClient(androidContext()) }

    single { provideRetrofit(get()) }

    single { get<Retrofit>().create(ProductApi::class.java) }

    /* Repositories */

    factoryOf(::ProductRepositoryImpl) { bind<ProductRepository>() }

}

private fun provideOkHttpClient(appContext: Context): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(Level.BASIC)
    @Suppress("DEPRECATION")
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ChuckerInterceptor(appContext))
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .build();