package com.example.codechallenge.ui.eventsList

import com.example.codechallenge.databinding.PullRequestItemBinding
import com.example.codechallenge.model.pullRequestEvent.PullRequestEventModel
import com.example.codechallenge.utils.delegateAdapter.BaseAdapter
import com.example.codechallenge.utils.delegateAdapter.OnItemClickListener

class PullRequestEventAdapter(private val onItemClickListener: OnItemClickListener) :
    BaseAdapter<PullRequestEventModel, PullRequestItemBinding>(PullRequestItemBinding::inflate) {

    override fun PullRequestItemBinding.onBind(item: PullRequestEventModel) {
        eventId.text = item.id
        eventType.text = item.type
        eventHead.text = item.payload.action

        pullRequestEventLayout.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }

    override fun isForViewType(item: Any): Boolean = item is PullRequestEventModel

    override fun PullRequestEventModel.getItemId(): Any = type

}
