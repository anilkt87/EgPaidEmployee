package com.egpaid.employeeapp.home.homedashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.home.homedashboard.entitied.DrawerItem
import com.egpaid.employeeapp.home.homedashboard.listner.FragmentDrawerListener
import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.home.view.entities.Submenu
import kotlinx.android.synthetic.main.nav_drawer_row.view.*

class DrawerAdapter(private val data: ArrayList<Submenu>, val listener: FragmentDrawerListener) :
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
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.tv_title

        init {
            itemView.setOnClickListener {
                mClickListener?.onDrawerItemSelected(itemView, layoutPosition)
            }

        }
    }


}