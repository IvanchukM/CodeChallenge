package com.example.codechallenge.utils.delegateAdapter

data class BaseAdapterState(
    private val adapters: List<DelegateAdapter>,
    val data: List<Any> = emptyList()
) {
    private val adapterPosition = Array(data.size) { -1 }

    fun getAdapterPosition(itemPosition: Int): Int =
        adapterPosition[itemPosition].takeIf { it != -1 }
            ?: adapters.indexOfFirst { it.isForViewType(data, itemPosition) }
                .takeIf { it != -1 }
                ?.also { adapterPosition[itemPosition] = it }
            ?: error("Provide adapter for type ${data[itemPosition].javaClass} at position: $itemPosition")

    fun getAdapter(adapterPosition: Int): DelegateAdapter = adapters[adapterPosition]

    fun getAdapterByItemPosition(itemPosition: Int): DelegateAdapter =
        adapters[getAdapterPosition(itemPosition)]
}
