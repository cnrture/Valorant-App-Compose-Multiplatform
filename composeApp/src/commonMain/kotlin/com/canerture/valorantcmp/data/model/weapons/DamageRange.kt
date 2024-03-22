package com.canerture.valorantcmp.data.model.weapons

import kotlinx.serialization.Serializable

@Serializable
data class DamageRange(
    val rangeStartMeters: Int?,
    val rangeEndMeters: Int?,
    val bodyDamage: Double?,
    val headDamage: Double?,
    val legDamage: Double?,
)
