package com.egpaid.employeeapp.home.homedashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.home.homedashboard.listner.FragmentDrawerListener
import com.egpaid.employeeapp.home.homedashboard.listner.GrdViewListener
import com.egpaid.employeeapp.home.view.entities.HomeModel
import kotlinx.android.synthetic.main.item_grid_view.view.*

class MyGridViewAdapter(
    val context: Context,
    private val data: List<HomeModel>,
    val listener: GrdViewListener
) :
    RecyclerView.Adapter<MyGridViewAdapter.ViewHolder>() {

    lateinit var mClickListener: GrdViewListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_view, parent, false)

        // return the view holder
        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // display the current animal
        mClickListener = listener
        holder.tvTitleHome.text = data[position].menu?.pagename
        Glide.with(context)
            .load("https://console.ezyone.in/assets/" + data[position].menu?.icon)
            .into(holder.imgHomeItem)
        holder.cardHomeItem.setOnClickListener {
            mClickListener.onHomeItemSelected(holder.itemView, position)
        }

    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return data.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitleHome: TextView = itemView.tv_title_home_item
        val imgHomeItem: ImageView = itemView.img_home_item
        val cardHomeItem: CardView = itemView.card_home_item

    }


    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }
}