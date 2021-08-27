package com.example.codechallenge.model.pushEvent

data class Payload(
    val before: String,
    val distinct_size: Int,
    val head: String,
    val push_id: Long,
    val ref: String,
    val size: Int
)