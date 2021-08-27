package com.example.codechallenge.utils.delegateAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.codechallenge.model.BaseEventModel

abstract class BaseAdapter<T : BaseEventModel, VB : ViewBinding>(
    private val inflater: (LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> VB
) : DelegateAdapter {

    final override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = inflater.invoke(layoutInflater, parent, false)
        return ViewBindingHolder(
            viewBinding
        )
    }

    @Suppress("UNCHECKED_CAST")
    final override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        items: List<Any>,
        position: Int
    ) {
        holder as ViewBindingHolder<VB>
        holder.viewBinding.onBind(items[position] as T)
    }

    abstract fun VB.onBind(item: T)

    @Suppress("UNCHECKED_CAST")
    override fun onRecycled(holder: RecyclerView.ViewHolder) {
        (holder as ViewBindingHolder<VB>).viewBinding
    }

    abstract fun isForViewType(item: Any): Boolean

    abstract fun T.getItemId(): Any

    override fun itemContent(item: Any): Any = item

    @Suppress("UNCHECKED_CAST")
    final override fun itemId(item: Any): Any = (item as T).getItemId()

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return isForViewType(items[position])
    }

    @Suppress("UNCHECKED_CAST")
    final override fun onAttachedToWindow(holder: RecyclerView.ViewHolder) =
        (holder as ViewBindingHolder<VB>).viewBinding.onAttachedToWindow()

    @Suppress("UNCHECKED_CAST")
    final override fun onDetachedFromWindow(holder: RecyclerView.ViewHolder) =
        (holder as ViewBindingHolder<VB>).viewBinding.onDetachedFromWindow()

    open fun VB.onAttachedToWindow() {}
    open fun VB.onDetachedFromWindow() {}

    private class ViewBindingHolder<VB : ViewBinding>(
        val viewBinding: VB
    ) : RecyclerView.ViewHolder(viewBinding.root)
}
