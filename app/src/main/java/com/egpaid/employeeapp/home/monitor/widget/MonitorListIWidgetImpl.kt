package com.egpaid.employeeapp.home.monitor.widget

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.livedata.SingleLiveData
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.ParentCategory
import com.egpaid.employeeapp.home.monitor.widget.adapter.CustomExpandableListAdapter
import com.egpaid.employeeapp.home.monitor.widget.adapter.MonitorDailyAdapter
import kotlinx.android.synthetic.main.contaainer_parent_child_list.*
import kotlinx.android.synthetic.main.view_daily_monitor_list_widget.*
import javax.inject.Inject
import javax.inject.Named

class MonitorListIWidgetImpl @Inject constructor(
    private val context: Context,
    private val monitorDailyAdapter: MonitorDailyAdapter,
    @Named("ForMonitorDailyAdapter")
    private val linearLayoutManager: RecyclerView.LayoutManager,
    override val onClicked: SingleLiveData<MonitorListIWidget.CallToAction>
) : MonitorListIWidget {

    override lateinit var containerView: View

    var customExpandableListAdapter: CustomExpandableListAdapter? = null

    override fun initView(contentView: View) {
        this.containerView = contentView
        displayAllAppWise()
        clickActionDisplay()
        displaySpinner()

    }


    private fun displayCategoryWise() {
        categoryListRv?.let {
            it.adapter = customExpandableListAdapter
        }
    }


    private fun displayAllAppWise() {
        daily_monitor_list.apply {
            adapter = monitorDailyAdapter
            layoutManager = linearLayoutManager
        }
    }


    override fun showLoading() {
    }

    override fun setContent(content: HashMap<String, ArrayList<Monitor>>) {
        val data = arrayListOf<ParentCategory>()
        for ((key, value) in content) {
            println("$key = $value")
            data.add(ParentCategory(key, value))
        }

        customExpandableListAdapter = CustomExpandableListAdapter(context)
        customExpandableListAdapter?.setExpandableParentItemList(data)
        categoryListRv.setHasFixedSize(true)
        categoryListRv.layoutManager = LinearLayoutManager(context)
        displayCategoryWise()
        val item = ArrayList<Monitor>()
        for ((key, value) in content) {
            println("$key = $value")
            item.addAll(value)
        }
        monitorDailyAdapter.setMonitorItem(item)
    }


    override fun showGeneralError() {
    }

    override fun showNetworkError() {
    }

    override fun onRetry(action: () -> Unit) {
    }

    override fun show() {
        daily_monitor_list.visibility = View.VISIBLE
    }

    override fun hide() {
    }

    private fun clickActionDisplay() {

        tv_full_view.setOnClickListener {
            ln_container_all_apps_wise.visibility = View.VISIBLE
            ln_container_category_wise.visibility = View.GONE
            tv_full_view.setTextColor(ContextCompat.getColor(context, R.color.black))
            tv_category_view.setTextColor(ContextCompat.getColor(context, R.color.light_gray))
        }
        tv_category_view.setOnClickListener {
            ln_container_category_wise.visibility = View.VISIBLE
            ln_container_all_apps_wise.visibility = View.GONE
            tv_category_view.setTextColor(ContextCompat.getColor(context, R.color.black))
            tv_full_view.setTextColor(ContextCompat.getColor(context, R.color.light_gray))
        }
    }

    private fun displaySpinner() {
        val type = context.resources.getStringArray(R.array.item_content_week_day)
        if (sp_child_parent != null) {
            val adapter = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item, type
            )
            sp_child_parent.adapter = adapter
        }
        sp_child_parent.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                onClicked.value = MonitorListIWidget.CallToAction.SpinnerClick(type[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

}