package com.example.codechallenge.model.createEvent

import com.google.gson.annotations.SerializedName

data class Payload(
    val description: Any,
    @SerializedName("master_branch")
    val masterBranch: String,
    @SerializedName("pusher_type")
    val pusherType: String,
    val ref: String,
    @SerializedName("ref_type")
    val refType: String
)