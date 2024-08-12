package com.canerture.valorantcmp.domain.repository

import com.canerture.valorantcmp.data.model.agents.Agent
import com.canerture.valorantcmp.data.model.competitivetiers.CompetitiveTier
import com.canerture.valorantcmp.data.model.maps.Map
import com.canerture.valorantcmp.data.model.weapons.Weapon

interface ValorantRepository {
    suspend fun getAgents(): Result<List<Agent>?>
    suspend fun getAgentDetail(id: String): Result<Agent?>
    suspend fun getMaps(): Result<List<Map>?>
    suspend fun getMapDetail(id: String): Result<Map?>
    suspend fun getWeapons(): Result<List<Weapon>?>
    suspend fun getWeaponDetail(id: String): Result<Weapon?>
    suspend fun getTiers(): Result<List<CompetitiveTier>?>
}
