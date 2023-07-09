package com.everymeal.everymeal_android.di

import com.everymeal.data.datasource.FoodDataSource
import com.everymeal.data.datasource.FoodDataSourceImpl
import com.everymeal.data.repository.FoodRepositoryImpl
import com.everymeal.domain.repository.FoodRepository
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
    abstract fun bindFoodRepository(
        foodRepositoryImpl: FoodRepositoryImpl,
    ) : FoodRepository

    @Singleton
    @Binds
    abstract fun bindFoodV2DataSource(
        foodDataSourceImpl: FoodDataSourceImpl
    ): FoodDataSource
}