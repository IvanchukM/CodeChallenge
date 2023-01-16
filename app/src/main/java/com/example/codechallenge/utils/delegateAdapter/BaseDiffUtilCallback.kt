package com.example.codechallenge.utils.delegateAdapter

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilCallback(
    private val oldState: BaseAdapterState,
    private val newState: BaseAdapterState,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldState.data.size

    override fun getNewListSize(): Int = newState.data.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldState.itemId(oldItemPosition) == newState.itemId(newItemPosition)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldState.itemContent(oldItemPosition) == newState.itemContent(newItemPosition)

    private fun BaseAdapterState.itemId(position: Int): Any =
        getAdapterByItemPosition(position).itemId(data[position])

    private fun BaseAdapterState.itemContent(position: Int): Any =
        getAdapterByItemPosition(position).itemContent(data[position])
}