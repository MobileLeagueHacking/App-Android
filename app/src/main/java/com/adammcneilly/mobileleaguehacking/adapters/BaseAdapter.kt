package com.adammcneilly.mobileleaguehacking.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.adammcneilly.mobileleaguehacking.models.BaseModel
import java.util.*

/**
 * Base adapter class to avoid code reuse.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
abstract class BaseAdapter<T, V: BaseAdapter.BaseViewHolder<T>>(): RecyclerView.Adapter<V>() {
    var items: List<T> = ArrayList()

    constructor(items: List<T>): this() {
        this.items = items
    }

    fun swapItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bindItem(items[position])
    }

    abstract class BaseViewHolder<in T>(view: View): RecyclerView.ViewHolder(view) {
        abstract fun bindItem(item: T)
    }
}