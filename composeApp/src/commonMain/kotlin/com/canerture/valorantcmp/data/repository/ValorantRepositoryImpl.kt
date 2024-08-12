package com.canerture.valorantcmp.data.repository

import com.canerture.valorantcmp.data.model.BaseResponse
import com.canerture.valorantcmp.data.remote.ValorantService
import com.canerture.valorantcmp.domain.repository.ValorantRepository

class ValorantRepositoryImpl(private val valorantService: ValorantService) : ValorantRepository {

    override suspend fun getAgents() = safeApiCall {
        valorantService.getAgents()
    }

    override suspend fun getAgentDetail(id: String) = safeApiCall {
        valorantService.getAgentDetail(id)
    }

    override suspend fun getMaps() = safeApiCall {
        valorantService.getMaps()
    }

    override suspend fun getMapDetail(id: String) = safeApiCall {
        valorantService.getMapDetail(id)
    }

    override suspend fun getWeapons() = safeApiCall {
        valorantService.getWeapons()
    }

    override suspend fun getWeaponDetail(id: String) = safeApiCall {
        valorantService.getWeaponDetail(id)
    }

    override suspend fun getTiers() = safeApiCall {
        valorantService.getTiers()
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> BaseResponse<T>): Result<T?> {
        return try {
            val response = call()
            if (response.status == SUCCESS) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.error.toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        private const val SUCCESS = 200
    }
}
