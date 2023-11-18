package com.crowdproj.resources.app.kafka

import kotlinx.serialization.json.Json
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.MockConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.MockProducer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.Test
import com.crowdproj.resources.api.v1.encodeRequest
import com.crowdproj.resources.api.v1.models.*
import java.util.*
import kotlin.test.assertEquals


class KafkaControllerTest {
    @Test
    fun runKafka() {
        val consumer = MockConsumer<String, String>(OffsetResetStrategy.EARLIEST)
        val producer = MockProducer(true, StringSerializer(), StringSerializer())
        val config = AppKafkaConfig()
        val inputTopic = config.kafkaTopicInV1
        val outputTopic = config.kafkaTopicOutV1

        val app = AppKafkaConsumer(config, listOf(ConsumerStrategyV1()), consumer = consumer, producer = producer)
        consumer.schedulePollTask {
            consumer.rebalance(Collections.singletonList(TopicPartition(inputTopic, 0)))
            consumer.addRecord(
                ConsumerRecord(
                    inputTopic,
                    PARTITION,
                    0L,
                    "test-1",
                    encodeRequest(
                        ResourceCreateRequest(
                            resource = ResourceCreateObject(
                                scheduleId = "Some Ad",
                                ownerId = "some testing ad to check them all",
                                visible = ResourceVisibility.OWNER_ONLY,
                            ),
                            debug = CpBaseDebug(
                                mode = CpRequestDebugMode.STUB,
                                stub = CpRequestDebugStubs.SUCCESS
                            )
                        )
                    )
                )
            )
            app.stop()
        }

        val startOffsets: MutableMap<TopicPartition, Long> = mutableMapOf()
        val tp = TopicPartition(inputTopic, PARTITION)
        startOffsets[tp] = 0L
        consumer.updateBeginningOffsets(startOffsets)

        app.run()

        val message = producer.history().first()
        val result = Json.decodeFromString(IResponse.serializer(), message.value()) as ResourceCreateResponse
        assertEquals(outputTopic, message.topic())
        assertEquals("11111111-1111-1111-1111-111111111111", result.requestId)
    }

    companion object {
        const val PARTITION = 0
    }
}