package com.everymeal.data.model.onboarding

import com.everymeal.domain.model.onboarding.GetUniversityEntity
import kotlinx.serialization.Serializable

@Serializable
data class GetUniversityData(
    val localDateTime: String,
    val message: String,
    val data: List<UniversityData>
) {
    @Serializable
    data class UniversityData(
        val idx: Int,
        val universityName: String,
        val campusName: String,
        val universityShortName: String
    )
}

fun GetUniversityData.toUniversityEntity(): GetUniversityEntity {
    val universityDataList = this.data.map { result ->
        GetUniversityEntity.UniversityData(
            idx = result.idx,
            universityName = result.universityName,
            campusName = result.campusName,
            universityShortName = result.universityShortName
        )
    }

    return GetUniversityEntity(
        data = universityDataList
    )
}