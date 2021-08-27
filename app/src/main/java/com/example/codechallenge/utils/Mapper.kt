package com.example.codechallenge.utils

import com.example.codechallenge.model.BaseEventModel
import com.example.codechallenge.model.DetailsModel
import com.example.codechallenge.model.Params
import com.example.codechallenge.model.pullRequestEvent.PullRequestEventModel
import com.example.codechallenge.model.pushEvent.PushEventModel
import java.util.*

object Mapper {
    fun mapToUIModel(model: BaseEventModel): DetailsModel? {
        return when (model) {
            is PushEventModel -> DetailsModel(

                params = listOf(
                    Params(
                        name = model.payload::head.name.toUpperCase(Locale.ROOT),
                        value = model.payload.head
                    ),
                    Params(
                        name = model.payload::before.name.toUpperCase(Locale.ROOT),
                        value = model.payload.before
                    ),
                    Params(
                        name = model.payload::ref.name.toUpperCase(Locale.ROOT),
                        value = model.payload.ref
                    )
                )
            )
            is PullRequestEventModel -> DetailsModel(
                params = listOf(
                    Params(
                        name = model.actor::avatarId.name.toUpperCase(Locale.ROOT),
                        value = model.actor.avatarId
                    ),
                    Params(
                        name = model.actor::avatarUrl.name.toUpperCase(Locale.ROOT),
                        value = model.actor.avatarUrl
                    ),
                    Params(
                        name = model.actor::displayLogin.name.toUpperCase(Locale.ROOT),
                        value = model.actor.displayLogin
                    )
                )
            )
            else -> null
        }
    }
}