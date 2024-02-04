package com.everymeal.everymeal_android.di

import com.everymeal.data.datasource.auth.AuthRemoteDataSource
import com.everymeal.data.datasource.auth.AuthRemoteRemoteDataSourceImpl
import com.everymeal.data.datasource.local.LocalDataSource
import com.everymeal.data.datasource.local.LocalDataSourceImpl
import com.everymeal.data.datasource.onboarding.OnboardingDataSource
import com.everymeal.data.datasource.onboarding.OnboardingDataSourceImpl
import com.everymeal.data.datasource.restaurant.RestaurantDataSource
import com.everymeal.data.datasource.restaurant.RestaurantDataSourceImpl
import com.everymeal.data.datasource.review.ReviewDataSource
import com.everymeal.data.datasource.review.ReviewDataSourceImpl
import com.everymeal.data.repository.auth.DefaultAuthRepository
import com.everymeal.data.repository.local.LocalRepositoryImpl
import com.everymeal.data.repository.onboarding.OnboardingRepositoryImpl
import com.everymeal.data.repository.restaurant.RestaurantRepositoryImpl
import com.everymeal.data.repository.review.DefaultReviewRepository
import com.everymeal.data.repository.search.DefaultSearchRepository
import com.everymeal.domain.repository.auth.AuthRepository
import com.everymeal.domain.repository.local.LocalRepository
import com.everymeal.domain.repository.onboarding.OnboardingRepository
import com.everymeal.domain.repository.restaurant.RestaurantRepository
import com.everymeal.domain.repository.review.ReviewRepository
import com.everymeal.domain.repository.search.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl,
    ): OnboardingRepository

    @Singleton
    @Binds
    abstract fun bindOnboardingDataSource(
        onboardingDataSourceImpl: OnboardingDataSourceImpl,
    ): OnboardingDataSource

    @Singleton
    @Binds
    abstract fun bindLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl,
    ): LocalRepository

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl,
    ): LocalDataSource

    @Singleton
    @Binds
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteRemoteDataSourceImpl,
    ): AuthRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindRestaurantDataSource(
        restaurantDataSourceImpl: RestaurantDataSourceImpl,
    ): RestaurantDataSource

    @Singleton
    @Binds
    abstract fun bindRestaurantRepository(
        restaurantRepositoryImpl: RestaurantRepositoryImpl,
    ): RestaurantRepository

    @Singleton
    @Binds
    abstract fun bindAuthRepository(
        defaultAuthRepository: DefaultAuthRepository,
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun bindReviewDataSource(
        reviewDataSourceImpl: ReviewDataSourceImpl,
    ): ReviewDataSource

    @Singleton
    @Binds
    abstract fun bindReviewRepository(
        defaultReviewRepository: DefaultReviewRepository,
    ): ReviewRepository

    @Singleton
    @Binds
    abstract fun bindSearchRepository(
        defaultSearchRepository: DefaultSearchRepository,
    ): SearchRepository
}
