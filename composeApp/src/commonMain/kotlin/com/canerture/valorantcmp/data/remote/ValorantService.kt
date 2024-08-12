package com.canerture.valorantcmp.data.remote

import com.canerture.valorantcmp.BuildKonfig
import com.canerture.valorantcmp.common.Endpoints
import com.canerture.valorantcmp.data.model.BaseResponse
import com.canerture.valorantcmp.data.model.agents.Agent
import com.canerture.valorantcmp.data.model.competitivetiers.CompetitiveTier
import com.canerture.valorantcmp.data.model.maps.Map
import com.canerture.valorantcmp.data.model.weapons.Weapon
import io.ktor.client.call.body
import io.ktor.client.request.get

class ValorantService : KtorApi() {

    private val baseUrl = BuildKonfig.BASE_URL

    suspend fun getAgents(): BaseResponse<List<Agent>> {
        return client.get(baseUrl.plus(Endpoints.AGENTS)).body<BaseResponse<List<Agent>>>()
    }

    suspend fun getMaps(): BaseResponse<List<Map>> {
        return client.get(baseUrl.plus(Endpoints.MAPS)).body<BaseResponse<List<Map>>>()
    }

    suspend fun getWeapons(): BaseResponse<List<Weapon>> {
        return client.get(baseUrl.plus(Endpoints.WEAPONS)).body<BaseResponse<List<Weapon>>>()
    }

    suspend fun getTiers(): BaseResponse<List<CompetitiveTier>> {
        return client.get(baseUrl.plus(Endpoints.TIERS)).body<BaseResponse<List<CompetitiveTier>>>()
    }

    suspend fun getAgentDetail(id: String): BaseResponse<Agent> {
        return client.get(baseUrl.plus(Endpoints.AGENT_DETAIL.plus("/$id"))).body<BaseResponse<Agent>>()
    }

    suspend fun getMapDetail(id: String): BaseResponse<Map> {
        return client.get(baseUrl.plus(Endpoints.MAPS.plus("/$id"))).body<BaseResponse<Map>>()
    }

    suspend fun getWeaponDetail(id: String): BaseResponse<Weapon> {
        return client.get(baseUrl.plus(Endpoints.WEAPONS.plus("/$id"))).body<BaseResponse<Weapon>>()
    }
}
