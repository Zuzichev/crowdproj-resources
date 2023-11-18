package com.crowdproj.resources.app.kafka

import com.crowdproj.resources.app.common.IMkplAppSettings
import com.crowdproj.resources.biz.ResourcesProcessor
import com.crowdproj.resources.common.ResourcesCorSettings
import com.crowdproj.resources.logging.common.MpLoggerProvider

class AppKafkaConfig(
    val kafkaHosts: List<String> = KAFKA_HOSTS,
    val kafkaGroupId: String = KAFKA_GROUP_ID,
    val kafkaTopicInV1: String = KAFKA_TOPIC_IN_V1,
    val kafkaTopicOutV1: String = KAFKA_TOPIC_OUT_V1,
    override val corSettings: ResourcesCorSettings = ResourcesCorSettings(),
    override val processor: ResourcesProcessor = ResourcesProcessor(corSettings),
    override val logger: MpLoggerProvider = MpLoggerProvider(),
): IMkplAppSettings {
    companion object {
        const val KAFKA_HOST_VAR = "KAFKA_HOSTS"
        const val KAFKA_TOPIC_IN_V1_VAR = "KAFKA_TOPIC_IN_V1"
        const val KAFKA_TOPIC_OUT_V1_VAR = "KAFKA_TOPIC_OUT_V1"
        const val KAFKA_GROUP_ID_VAR = "KAFKA_GROUP_ID"

        val KAFKA_HOSTS by lazy { (System.getenv(KAFKA_HOST_VAR) ?: "").split("\\s*[,;]\\s*") }
        val KAFKA_GROUP_ID by lazy { System.getenv(KAFKA_GROUP_ID_VAR) ?: "resourcces" }
        val KAFKA_TOPIC_IN_V1 by lazy { System.getenv(KAFKA_TOPIC_IN_V1_VAR) ?: "resourcces-in-v1" }
        val KAFKA_TOPIC_OUT_V1 by lazy { System.getenv(KAFKA_TOPIC_OUT_V1_VAR) ?: "resourcces-out-v1" }

    }
}