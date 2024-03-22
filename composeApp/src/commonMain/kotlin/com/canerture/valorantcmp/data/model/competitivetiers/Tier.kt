package com.canerture.valorantcmp.data.model.competitivetiers

import kotlinx.serialization.Serializable

@Serializable
data class Tier(
    val backgroundColor: String?,
    val color: String?,
    val division: String?,
    val divisionName: String?,
    val largeIcon: String?,
    val smallIcon: String?,
    val tier: Int?,
    val tierName: String?
)
