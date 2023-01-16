package com.example.codechallenge.ui.eventsList.adapters

import com.example.codechallenge.databinding.WatchEventItemBinding
import com.example.codechallenge.model.watchEvent.WatchEventModel
import com.example.codechallenge.utils.delegateAdapter.BaseAdapter
import com.example.codechallenge.utils.delegateAdapter.OnItemClickListener

class WatchEventAdapter(private val onItemClickListener: OnItemClickListener) :
    BaseAdapter<WatchEventModel, WatchEventItemBinding>(WatchEventItemBinding::inflate) {
    override fun WatchEventItemBinding.onBind(item: WatchEventModel) {
        eventId.text = item.id
        eventType.text = item.type
        eventHead.text = item.payload.action

        watchEventLayout.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }

    override fun isForViewType(item: Any): Boolean = item is WatchEventModel

    override fun WatchEventModel.getItemId(): Any = type

}