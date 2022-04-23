package com.egpaid.employeeapp.home.monitor.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.homedashboard.adapter.MyGridViewAdapter
import com.egpaid.employeeapp.home.homedashboard.listner.GrdViewListener
import com.egpaid.employeeapp.home.monitor.viewmdel.MonitorDataViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_monitor.*
import javax.inject.Inject


class HomeFragment : Fragment(), GrdViewListener {

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

                recyclerView.adapter = MyGridViewAdapter(requireContext(), state.data, this)
            }

            else -> {

            }
        }

    }

    override fun onHomeItemSelected(view: View, position: Int) {
        TODO("Not yet implemented")
    }

}