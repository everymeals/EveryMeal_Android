package com.everymeal.everymeal_android.di

import com.everymeal.data.datasource.onboarding.OnboardingDataSource
import com.everymeal.data.datasource.onboarding.OnboardingDataSourceImpl
import com.everymeal.data.repository.onboarding.OnboardingRepositoryImpl
import com.everymeal.domain.repository.onboarding.OnboardingRepository
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
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository

    @Singleton
    @Binds
    abstract fun bindOnboardingDataSource(
        onboardingDataSourceImpl: OnboardingDataSourceImpl
    ): OnboardingDataSource
}