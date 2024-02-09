package com.everymeal.everymeal_android.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Auth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Secured

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Unsecured
