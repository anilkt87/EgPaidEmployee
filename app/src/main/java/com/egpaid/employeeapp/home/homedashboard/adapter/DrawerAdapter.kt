package com.egpaid.employeeapp.home.homedashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.home.homedashboard.listner.FragmentDrawerListener
import com.egpaid.employeeapp.home.view.entities.Submenu
import kotlinx.android.synthetic.main.nav_drawer_row.view.*

class DrawerAdapter(val context:Context, private val data: ArrayList<Submenu>, val listener: FragmentDrawerListener) :
    RecyclerView.Adapter<DrawerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.nav_drawer_row, parent, false)
        return MyViewHolder(view)
    }

    lateinit var mClickListener: FragmentDrawerListener
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        mClickListener = listener
        val current = data[position]
        holder.title.text = current.pagename

        Glide.with(context)
            .load("https://console.ezyone.in/assets/"+current.icon)
            .into(holder.imageView.img_nav)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.tv_title
        var imageView = itemView.img_nav


        init {
            itemView.setOnClickListener {
                mClickListener?.onDrawerItemSelected(itemView, layoutPosition)
            }

        }
    }


}