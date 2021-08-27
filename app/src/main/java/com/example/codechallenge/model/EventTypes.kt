package com.example.codechallenge.model

enum class EventTypes(val value: String) {
    PUSH_EVENT("PushEvent"),
    PULL_REQUEST_EVENT("PullRequestEvent");

    companion object {
        fun getFromValue(value: String): EventTypes? =
            values().find { it.value == value }
    }
}