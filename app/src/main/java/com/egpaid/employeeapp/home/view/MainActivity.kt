package com.egpaid.employeeapp.home.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.homedashboard.DrawerFragment
import com.egpaid.employeeapp.home.homedashboard.listner.FragmentDrawerListener
import com.egpaid.employeeapp.home.monitor.view.HomeFragment
import com.egpaid.employeeapp.home.profile.ProfileFragment
import com.egpaid.employeeapp.home.viewmodel.HomeViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), FragmentDrawerListener {
    private lateinit var drawerFragment: DrawerFragment

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        homeViewModel.apply {
            getMyAppSideBar()
            observe(stateLiveData, ::getMySideBarData)
        }

    }

    private fun getMySideBarData(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.MySideBarData -> {
                app_wise_progress_circular.visibility = View.GONE
                displayView()
            }
            is BaseViewModel.State.Loading -> {
                app_wise_progress_circular.visibility = View.VISIBLE
            }
            is BaseViewModel.State.Error -> {
                app_wise_progress_circular.visibility = View.GONE
                displayView()
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDrawerItemSelected(view: View, position: Int) {
        displayView(position)
    }

    private fun displayView() {
        drawerFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
        drawerFragment.init(R.id.fragment_navigation_drawer, drawer_layout, toolbar)
        displayView(0)
    }

    private fun displayView(position: Int) {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        when (position) {
            0 -> {
                fragment = HomeFragment()
                title = "Monitor" //getString(R.string.nav_item_one)
            }
            1 -> {
                fragment = ProfileFragment()
                title = "Profile"//getString(R.string.nav_item_two)
            }
            2 -> {
//                fragment = CFragment()
//                title = getString(R.string.nav_item_three)
            }
            else -> {
            }
        }

        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_content, fragment)
            fragmentTransaction.commit()
            supportActionBar?.title = title
        }
    }
}