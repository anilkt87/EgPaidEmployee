package com.egpaid.employeeapp.home.homedashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.home.view.entities.HomeModel
import kotlinx.android.synthetic.main.item_grid_view.view.*

class MyGridViewAdapter(private val data: List<HomeModel>) :
    RecyclerView.Adapter<MyGridViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_view, parent, false)

        // return the view holder
        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // display the current animal
        holder.animal.text = data[position].menu?.pagename
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return data.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animal: TextView = itemView.tvAnimal
    }


    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }
}