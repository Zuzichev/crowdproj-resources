package ru.otus.otuskotlin.marketplace.app.kafka

fun main() {
    val config = AppKafkaConfig()
    val consumer = AppKafkaConsumer(config, listOf(ConsumerStrategyV1()))
    consumer.run()
}