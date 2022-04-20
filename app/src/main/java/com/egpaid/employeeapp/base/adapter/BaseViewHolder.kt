package com.egpaid.employeeapp.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Base [RecyclerView.ViewHolder]/
 *
 * @param <T> Any object for this item.
</T> */
abstract class BaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(
    itemView!!
) {
    /**
     * Bind the data to your views.
     * Use [BaseViewHolder] for all view related
     * binding in the [RecyclerView].
     *
     * @param t Any object for this item.
     */
    abstract fun bind(t: T)
}
