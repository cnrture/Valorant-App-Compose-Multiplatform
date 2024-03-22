package com.canerture.valorantcmp.domain.model

data class WeaponUI(
    val category: String,
    val displayIcon: String,
    val displayName: String,
    var skins: List<SkinUI>,
    val id: String,
    val weaponStats: WeaponStatsUI
)

data class SkinUI(
    val displayIcon: String,
    val displayName: String,
)

data class WeaponStatsUI(
    val damageRanges: List<DamageRangeUI>,
)

data class DamageRangeUI(
    val rangeStartMeters: Int,
    val rangeEndMeters: Int,
    val bodyDamage: Double,
    val headDamage: Double,
    val legDamage: Double,
)
