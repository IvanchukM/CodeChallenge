package com.example.codechallenge.ui.eventsList.adapters

import com.example.codechallenge.databinding.PushItemBinding
import com.example.codechallenge.model.pushEvent.PushEventModel
import com.example.codechallenge.utils.delegateAdapter.BaseAdapter
import com.example.codechallenge.utils.delegateAdapter.OnItemClickListener

class PushEventsAdapter(private val onItemClickListener: OnItemClickListener) :
    BaseAdapter<PushEventModel, PushItemBinding>(PushItemBinding::inflate) {

    override fun PushItemBinding.onBind(item: PushEventModel) {
        eventId.text = item.id
        eventType.text = item.type
        eventHead.text = item.payload.head
        pushEventLayout.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }
    override fun isForViewType(item: Any): Boolean = item is PushEventModel

    override fun PushEventModel.getItemId(): Any = type

}
