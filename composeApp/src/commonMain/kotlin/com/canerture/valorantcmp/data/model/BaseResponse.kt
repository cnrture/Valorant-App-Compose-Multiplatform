package com.canerture.valorantcmp.data.model

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse<T>(
    val status: Int? = null,
    val error: String? = null,
    val data: T? = null,
)
