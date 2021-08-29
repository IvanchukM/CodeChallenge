package com.example.codechallenge.model.issueEvent

data class Payload(
    val action: String,
    val issue: Issue
)