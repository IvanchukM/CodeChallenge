package com.example.codechallenge.model.pullRequestEvent

data class Payload(
    val action: String,
    val number: Int,
    val pull_request: PullRequest
)