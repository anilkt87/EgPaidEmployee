package com.egpaid.employeeapp.home.monitor.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_monitor.*
import com.egpaid.employeeapp.home.homedashboard.adapter.*
import com.egpaid.employeeapp.home.monitor.viewmdel.MonitorDataViewModel
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var monitorDataViewModel: MonitorDataViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_monitor, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        monitorDataViewModel.apply {
            getMySideBarOnHomePageItem()
            observe(stateLiveData, ::getHomeSideBarData)
        }

    }

    private fun getHomeSideBarData(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.MySideBarHomePage -> {

                recyclerView.layoutManager = GridLayoutManager(context, 2)

                recyclerView.adapter = MyGridViewAdapter(state.data)
            }

        }

    }

    private fun setupViewPager(pager: ViewPager?) {
        val adapter = activity?.supportFragmentManager?.let {
            TabAdapter(
                it
            )
        }

        val f1 = AddDynamicFragment.newInstance("Home")
        adapter?.addFragment(f1, "Home")

        val f2 = AddDynamicFragment.newInstance("Dashboard")
        adapter?.addFragment(f2, "Dashboard")

        val f3 = AddDynamicFragment.newInstance("Profile")
        adapter?.addFragment(f3, "Profile")

        pager?.adapter = adapter
    }
}