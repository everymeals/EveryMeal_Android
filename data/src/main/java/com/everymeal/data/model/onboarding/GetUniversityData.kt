package com.everymeal.data.model.onboarding

import com.everymeal.domain.model.onboarding.GetUniversityEntity
import kotlinx.serialization.Serializable

@Serializable
data class UniversityData(
    val idx: Int,
    val universityName: String,
    val campusName: String,
    val universityShortName: String
)

fun List<UniversityData>.toUniversityEntity(): GetUniversityEntity {
    val universityDataList = this.map { result ->
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