package com.egpaid.employeeapp.home.monitor.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel.State.SuccessDailyCategoryData
import com.egpaid.employeeapp.base.widget.Widget
import com.egpaid.employeeapp.home.monitor.viewmdel.MonitorDataViewModel
import com.egpaid.employeeapp.home.monitor.widget.MonitorGraphWidget
import com.egpaid.employeeapp.home.monitor.widget.MonitorListIWidget
import com.egpaid.employeeapp.home.monitor.widget.MonitorListIWidget.CallToAction
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_monitor.*
import javax.inject.Inject
import javax.inject.Named


class MonitorFragment : Fragment() {

    @Inject
    @Named("PerFragment")
    lateinit var pdpListWidget: String

    @Inject
    lateinit var monitorListIWidget: MonitorListIWidget

    @Inject
    lateinit var monitorDataViewModel: MonitorDataViewModel

    @Inject
    lateinit var monitorGraphWidget: MonitorGraphWidget


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
        initWidget(container_monitor)
        monitorDataViewModel.apply {
            observe(stateLiveData, ::onChangeState)
        }
    }
    private fun initWidget(view: View) {
        monitorListIWidget.apply {
            initView(view)
            addWidget(this)
            observe(onClicked, ::onSpinnerSelect)
        }
       monitorGraphWidget.apply {
           initView(view)
           addWidget(this)
       }
    }

    private fun addWidget(widget: Widget) {
        lifecycle.addObserver(widget)
    }

    private fun onSpinnerSelect(callToAction: CallToAction) {
        when (callToAction) {
            is CallToAction.SpinnerClick -> {
                if (callToAction.value == "Daily") {
                } else if (callToAction.value == "Weekly") {
                }
            }

        }
    }

    private fun onChangeState(state: BaseViewModel.State) = when (state) {
        is SuccessDailyCategoryData -> {
            monitorListIWidget.setContent(state.data)
        }
        else -> {

        }
    }
}