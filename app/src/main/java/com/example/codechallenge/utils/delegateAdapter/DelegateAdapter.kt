package com.example.codechallenge.utils.delegateAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface DelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, items: List<Any>, position: Int)

    fun isForViewType(items: List<Any>, position: Int): Boolean

    fun itemId(item: Any): Any

    fun itemContent(item: Any): Any
}