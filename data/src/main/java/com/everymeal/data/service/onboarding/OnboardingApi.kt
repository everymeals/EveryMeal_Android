package com.everymeal.data.service.onboarding

import com.everymeal.data.model.BaseResponse
import com.everymeal.data.model.onboarding.UniversityData
import retrofit2.http.GET

interface OnboardingApi {

    @GET("/api/v1/universities")
    suspend fun getUniversity(): BaseResponse<List<UniversityData>>
}