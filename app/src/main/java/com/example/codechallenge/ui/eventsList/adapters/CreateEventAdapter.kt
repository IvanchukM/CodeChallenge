package com.example.codechallenge.ui.eventsList.adapters

import com.example.codechallenge.databinding.CreateEventItemBinding
import com.example.codechallenge.model.createEvent.CreateEventModel
import com.example.codechallenge.utils.delegateAdapter.BaseAdapter
import com.example.codechallenge.utils.delegateAdapter.OnItemClickListener

class CreateEventAdapter(private val onItemClickListener: OnItemClickListener) :
    BaseAdapter<CreateEventModel, CreateEventItemBinding>(CreateEventItemBinding::inflate) {
    override fun CreateEventItemBinding.onBind(item: CreateEventModel) {
        eventId.text = item.id
        eventType.text = item.type
        eventHead.text = item.payload.pusherType

        createEventLayout.setOnClickListener{
            onItemClickListener.onItemClick(item)
        }
    }

    override fun isForViewType(item: Any): Boolean = item is CreateEventModel

    override fun CreateEventModel.getItemId(): Any = type

}