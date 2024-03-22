package com.canerture.valorantcmp.data.model.competitivetiers

import kotlinx.serialization.Serializable

@Serializable
data class CompetitiveTier(
    val tiers: List<Tier>?,
)
