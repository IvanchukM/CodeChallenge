package com.example.codechallenge.utils.delegateAdapter

import com.example.codechallenge.model.BaseEventModel

interface OnItemClickListener {
    fun onItemClick(model: BaseEventModel)
}