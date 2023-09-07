package com.crowdproj.resources.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class ResourcesRequestId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ResourcesRequestId("")
    }
}