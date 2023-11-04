package com.everymeal.data.service.onboarding

import com.everymeal.data.model.onboarding.GetUniversityData
import retrofit2.Response
import retrofit2.http.GET

interface OnboardingApi {

    @GET("/api/v1/universities")
    suspend fun getUniversity(): GetUniversityData
}