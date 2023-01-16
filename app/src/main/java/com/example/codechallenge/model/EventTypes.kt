package com.example.codechallenge.model

enum class EventTypes(val value: String) {
    PUSH_EVENT("PushEvent"),
    PULL_REQUEST_EVENT("PullRequestEvent"),
    CREATE_EVENT("CreateEvent"),
    WATCH_EVENT("WatchEvent"),
    ISSUE_EVENT("IssueEvent");

    companion object {
        fun getFromValue(value: String): EventTypes? =
            values().find { it.value == value }
    }
}