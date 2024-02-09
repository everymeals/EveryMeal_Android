package com.everymeal.everymeal_android.di

import com.everymeal.data.intercepter.AuthInterceptor
import com.everymeal.data.service.auth.UsersApi
import com.everymeal.data.service.onboarding.OnboardingApi
import com.everymeal.data.service.restaurant.RestaurantApi
import com.everymeal.data.service.review.StoreReviewApi
import com.everymeal.data.service.search.SearchService
import com.everymeal.everymeal_android.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val contentType = "application/json".toMediaType()
    private val json = Json { ignoreUnknownKeys = true }

    private const val BASE_URL = BuildConfig.BASE_URL

    @Provides
    @Singleton
    @Secured
    fun provideClient(
        @Auth authInterceptor: Interceptor
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @Unsecured
    fun provideUnsecuredClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @Secured
    fun provideRetrofit(
        @Secured client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @Unsecured
    fun provideUnsecuredRetrofit(
        @Unsecured client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    @Auth
    fun provideAuthInterceptor(interceptor: AuthInterceptor): Interceptor = interceptor

    @Provides
    @Singleton
    fun provideOnboardingApi(@Unsecured retrofit: Retrofit): OnboardingApi {
        return retrofit.create(OnboardingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApi(@Unsecured retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRestaurantApi(@Unsecured retrofit: Retrofit): RestaurantApi {
        return retrofit.create(RestaurantApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewApi(@Secured retrofit: Retrofit): StoreReviewApi {
        return retrofit.create(StoreReviewApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchApi(@Unsecured retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}
