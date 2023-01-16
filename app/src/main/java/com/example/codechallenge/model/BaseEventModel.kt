package com.example.codechallenge.model

import com.google.gson.annotations.SerializedName

interface BaseEventModel {
    val actor: Actor
    val createdAt: String
    val id: String
    val isPublic: Boolean
    val repo: Repo
    val type: String
}

data class Actor(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("display_login")
    val displayLogin: String,
    @SerializedName("gravatar_id")
    val avatarId: String,
    val id: Int,
    val login: String,
    val url: String
)

data class Repo(
    val id: Int,
    val name: String,
    val url: String
)

