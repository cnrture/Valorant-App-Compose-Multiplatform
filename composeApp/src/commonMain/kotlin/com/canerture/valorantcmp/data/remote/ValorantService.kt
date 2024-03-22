package com.canerture.valorantcmp.data.remote

import com.canerture.valorantcmp.BuildKonfig
import com.canerture.valorantcmp.data.model.BaseResponse
import com.canerture.valorantcmp.data.model.agents.Agent
import com.canerture.valorantcmp.data.model.competitivetiers.CompetitiveTier
import com.canerture.valorantcmp.data.model.maps.Map
import com.canerture.valorantcmp.data.model.weapons.Weapon
import io.ktor.client.call.body
import io.ktor.client.request.get

class ValorantService : KtorApi() {

    private val baseUrl = BuildKonfig.BASE_URL
    private val agents = baseUrl.plus("v1/agents")
    private val maps = baseUrl.plus("v1/maps")
    private val weapons = baseUrl.plus("v1/weapons")
    private val competitiveTiers = baseUrl.plus("v1/competitivetiers")

    suspend fun getAgents(): BaseResponse<List<Agent>> {
        return client.get(agents.plus("?isPlayableCharacter=true")).body<BaseResponse<List<Agent>>>()
    }

    suspend fun getMaps(): BaseResponse<List<Map>> {
        return client.get(maps).body<BaseResponse<List<Map>>>()
    }

    suspend fun getWeapons(): BaseResponse<List<Weapon>> {
        return client.get(weapons).body<BaseResponse<List<Weapon>>>()
    }

    suspend fun getCompetitiveTiers(): BaseResponse<List<CompetitiveTier>> {
        return client.get(competitiveTiers).body<BaseResponse<List<CompetitiveTier>>>()
    }

    suspend fun getAgentDetail(id: String): BaseResponse<Agent> {
        return client.get(agents.plus("/$id")).body<BaseResponse<Agent>>()
    }

    suspend fun getMapDetail(id: String): BaseResponse<Map> {
        return client.get(maps.plus("/$id")).body<BaseResponse<Map>>()
    }

    suspend fun getWeaponDetail(id: String): BaseResponse<Weapon> {
        return client.get(weapons.plus("/$id")).body<BaseResponse<Weapon>>()
    }
}
