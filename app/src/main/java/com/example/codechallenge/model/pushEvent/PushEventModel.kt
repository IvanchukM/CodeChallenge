package com.example.codechallenge.model.pushEvent

import com.example.codechallenge.model.Actor
import com.example.codechallenge.model.BaseEventModel
import com.example.codechallenge.model.Repo
import com.google.gson.annotations.SerializedName

data class PushEventModel(
    override val actor: Actor,
    @SerializedName("created_at")
    override val createdAt: String,
    override val id: String,
    val payload: Payload,
    @SerializedName("`public`")
    override val isPublic: Boolean,
    override val repo: Repo,
    override val type: String
) : BaseEventModel
