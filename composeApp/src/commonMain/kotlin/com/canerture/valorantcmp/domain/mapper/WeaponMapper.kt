package com.canerture.valorantcmp.domain.mapper

import com.canerture.valorantcmp.data.model.weapons.DamageRange
import com.canerture.valorantcmp.data.model.weapons.Skin
import com.canerture.valorantcmp.data.model.weapons.Weapon
import com.canerture.valorantcmp.data.model.weapons.WeaponStats
import com.canerture.valorantcmp.domain.model.DamageRangeUI
import com.canerture.valorantcmp.domain.model.SkinUI
import com.canerture.valorantcmp.domain.model.WeaponStatsUI
import com.canerture.valorantcmp.domain.model.WeaponUI

fun List<Weapon>?.mapToWeaponUI() = this?.map {
    WeaponUI(
        category = it.category.orEmpty().replace("EEquippableCategory::", ""),
        displayIcon = it.displayIcon.orEmpty(),
        displayName = it.displayName.orEmpty(),
        skins = it.skins.mapToSkinUI(),
        id = it.id.orEmpty(),
        weaponStats = it.weaponStats.mapToWeaponStatsUI()
    )
}.orEmpty()

fun Weapon?.mapToWeaponUI() = WeaponUI(
    category = this?.category.orEmpty().replace("EEquippableCategory::", ""),
    displayIcon = this?.displayIcon.orEmpty(),
    displayName = this?.displayName.orEmpty(),
    skins = this?.skins.mapToSkinUI(),
    id = this?.id.orEmpty(),
    weaponStats = this?.weaponStats.mapToWeaponStatsUI()
)

fun WeaponStats?.mapToWeaponStatsUI() = WeaponStatsUI(
    damageRanges = this?.damageRanges.mapToDamageRangeUI(),
)

fun List<Skin>?.mapToSkinUI() = this?.map {
    SkinUI(
        displayIcon = it.displayIcon.orEmpty(),
        displayName = it.displayName.orEmpty(),
    )
}.orEmpty().filter { it.displayIcon.isNotEmpty() }

fun List<DamageRange>?.mapToDamageRangeUI() = this?.map {
    DamageRangeUI(
        rangeStartMeters = it.rangeStartMeters ?: 0,
        rangeEndMeters = it.rangeEndMeters ?: 0,
        bodyDamage = it.bodyDamage ?: 0.0,
        headDamage = it.headDamage ?: 0.0,
        legDamage = it.legDamage ?: 0.0,
    )
}.orEmpty()
