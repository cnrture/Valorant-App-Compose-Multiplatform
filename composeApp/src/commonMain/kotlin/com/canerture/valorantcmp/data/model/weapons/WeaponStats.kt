package com.canerture.valorantcmp.data.model.weapons

import kotlinx.serialization.Serializable

@Serializable
data class WeaponStats(
    val damageRanges: List<DamageRange>?,
)
