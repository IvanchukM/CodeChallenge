package com.example.codechallenge.utils

import com.example.codechallenge.model.BaseEventModel
import com.example.codechallenge.model.EventTypes
import com.example.codechallenge.model.createEvent.CreateEventModel
import com.example.codechallenge.model.issueEvent.IssueEventModel
import com.example.codechallenge.model.pullRequestEvent.PullRequestEventModel
import com.example.codechallenge.model.pushEvent.PushEventModel
import com.example.codechallenge.model.watchEvent.WatchEventModel
import com.google.gson.*
import java.lang.reflect.Type


object EventsDeserializer : JsonDeserializer<BaseEventModel?> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): BaseEventModel? {
        return when (EventTypes.getFromValue(json.asJsonObject.get("type").asString)) {
            EventTypes.PUSH_EVENT -> Gson().fromJson(json, PushEventModel::class.java)
            EventTypes.PULL_REQUEST_EVENT -> Gson().fromJson(
                json,
                PullRequestEventModel::class.java
            )
            EventTypes.CREATE_EVENT -> Gson().fromJson(json, CreateEventModel::class.java)
            EventTypes.WATCH_EVENT -> Gson().fromJson(json, WatchEventModel::class.java)
            EventTypes.ISSUE_EVENT -> Gson().fromJson(json, IssueEventModel::class.java)
            else -> null
        }
    }
}
