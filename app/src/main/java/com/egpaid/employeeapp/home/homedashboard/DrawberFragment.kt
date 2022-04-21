package com.egpaid.employeeapp.home.homedashboard

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.home.homedashboard.adapter.DrawerAdapter
import com.egpaid.employeeapp.home.homedashboard.entitied.DrawerItem
import com.egpaid.employeeapp.home.homedashboard.listner.ClickListener
import com.egpaid.employeeapp.home.homedashboard.listner.FragmentDrawerListener
import com.egpaid.employeeapp.home.view.entities.Menu
import com.egpaid.employeeapp.home.view.entities.Submenu
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_drawer.*

class DrawberFragment : Fragment() ,FragmentDrawerListener {

    private lateinit var adapter: DrawerAdapter

    private var drawerListener: FragmentDrawerListener? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var containerView: View? = null


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        try {
            drawerListener = context as FragmentDrawerListener
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_drawer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var titles = activity?.resources?.getStringArray(R.array.nav_drawer_labels)
        val data = ArrayList<DrawerItem>()
        if (titles != null) {
            for (i in titles.indices) {
                val menu = Menu(
                    1,
                    usertype = null,
                    pagename = titles[i],
                    icon = "",
                    location = null,
                    treeview = 0,
                    parent = null,
                    orderby = null,
                    status = 0
                )
                val subMenuItem = Submenu(
                    1,
                    usertype = null,
                    pagename = "",
                    icon = "",
                    location = null,
                    treeview = 0,
                    parent = null,
                    orderby = null,
                    status = 0
                )
                var arrayListOfSubMenu = arrayListOf<Submenu>()
                arrayListOfSubMenu.add(subMenuItem)
                val navItem = DrawerItem(menu, arrayListOfSubMenu)
                data.add(navItem)
            }
        }

        adapter = DrawerAdapter(data,this)
        rv_drawer_list.adapter = adapter
        rv_drawer_list.layoutManager = LinearLayoutManager(activity)
        rv_drawer_list.addOnItemTouchListener(
            RecyclerTouchListener(
                requireContext(),
                rv_drawer_list,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        drawerListener?.onDrawerItemSelected(view, position)
                        containerView?.let { mDrawerLayout?.closeDrawer(it) }
                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )



    }

    fun init(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        containerView = activity?.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        val drawerToggle = object : ActionBarDrawerToggle(
            activity,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                activity?.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                activity?.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                toolbar.alpha = 1 - slideOffset / 2
            }
        }

        mDrawerLayout?.addDrawerListener(drawerToggle)
        mDrawerLayout?.post { drawerToggle.syncState() }

    }

    internal class RecyclerTouchListener(
        context: Context,
        recyclerView: RecyclerView,
        private val clickListener: ClickListener?
    ) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(
                            child,
                            recyclerView.getChildAdapterPosition(child)
                        )
                    }
                }
            })

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }

    override fun onDrawerItemSelected(view: View, position: Int) {
        drawerListener?.onDrawerItemSelected(view, position)
        containerView?.let { mDrawerLayout?.closeDrawer(it) }
    }
}