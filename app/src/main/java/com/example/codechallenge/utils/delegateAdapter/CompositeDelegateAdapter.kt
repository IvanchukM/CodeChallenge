package com.example.codechallenge.utils.delegateAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class CompositeDelegateAdapter(vararg adapters: DelegateAdapter) :
    RecyclerView.Adapter<ViewHolder>() {

    protected open var adapterState = BaseAdapterState(adapters.toList())

    override fun getItemViewType(position: Int): Int = adapterState.getAdapterPosition(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        adapterState.getAdapter(viewType).onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        adapterState.getAdapter(getItemViewType(position))
            .onBindViewHolder(holder, adapterState.data, position)

    override fun onViewRecycled(holder: ViewHolder) =
        adapterState.getAdapter(holder.itemViewType).onRecycled(holder)

    override fun getItemCount(): Int = adapterState.data.size

    open fun swapData(data: List<Any>) {
        val newAdapterState = adapterState.copy(data = data)
        val diffCallback = BaseDiffUtilCallback(adapterState, newAdapterState)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        adapterState = newAdapterState
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        adapterState.getAdapter(holder.itemViewType).onAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        adapterState.getAdapter(holder.itemViewType).onDetachedFromWindow(holder)
    }
}