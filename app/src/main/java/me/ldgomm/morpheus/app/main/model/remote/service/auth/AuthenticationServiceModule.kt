package me.ldgomm.morpheus.app.main.model.remote.service.auth

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import me.ldgomm.morpheus.app.util.constant.Constants.base_url
import okhttp3.JavaNetCookieJar
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationServiceModule {

    @Singleton
    @Provides
    fun provideCookieManager(): CookieManager {
        return CookieManager()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cookieManager: CookieManager): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS).cookieJar(JavaNetCookieJar(cookieManager)).build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder().baseUrl(base_url).client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType)).build()
    }

    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationServiceableApi {
        return retrofit.create(AuthenticationServiceableApi::class.java)
    }
}