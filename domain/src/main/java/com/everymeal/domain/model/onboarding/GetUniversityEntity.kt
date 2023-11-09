package com.everymeal.domain.model.onboarding

data class GetUniversityEntity(
    val data: List<UniversityData>
) {
    data class UniversityData(
        val idx: Int,
        val universityName: String,
        val campusName: String,
        val universityShortName: String
    )
}
