package com.crowdproj.resources.app.kafka

import com.crowdproj.resources.api.v1.decodeRequest
import com.crowdproj.resources.api.v1.encodeResponse
import com.crowdproj.resources.api.v1.models.IRequestResource
import com.crowdproj.resources.api.v1.models.IResponseResource
import com.crowdproj.resources.common.ResourcesContext
import com.crowdproj.resources.mappers.v1.fromApi
import com.crowdproj.resources.mappers.v1.toApi

class ConsumerStrategyV1 : ConsumerStrategy {
    override fun topics(config: AppKafkaConfig): InputOutputTopics {
        return InputOutputTopics(config.kafkaTopicInV1, config.kafkaTopicOutV1)
    }

    override fun serialize(source: ResourcesContext): String {
        val response: IResponseResource = source.toApi()
        return encodeResponse(response)
    }

    override fun deserialize(value: String, target: ResourcesContext) {
        val request: IRequestResource = decodeRequest(value)
        target.fromApi(request)
    }
}