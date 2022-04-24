package com.egpaid.employeeapp.appsecurity.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.appsecurity.viewmodel.AppSecurityViewModel
import com.egpaid.employeeapp.appsecurity.widget.AppSecurityWidget
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.base.widget.Widget
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_app_secutiry.*
import javax.inject.Inject

class AppSecurityActivity : AppCompatActivity() {
    @Inject
    lateinit var appSecurityWidget: AppSecurityWidget

    @Inject
    lateinit var appSecurityViewModel: AppSecurityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_secutiry)
        initWidget(app_security_container)
        appSecurityViewModel.apply {
            appSecurityOption()
            observe(appSettingLiveData, ::getAppSecurityOption)
        }
    }

    private fun initWidget(view: View) {
        appSecurityWidget.apply {
            initView(view)
            addWidget(this)
            observe(onClicked, ::onAppSecurityClick)
        }

    }

    private fun addWidget(widget: Widget) {
        lifecycle.addObserver(widget)
    }

    private fun onAppSecurityClick(callAction: AppSecurityWidget.CallToAction) {

        when (callAction) {
            is AppSecurityWidget.CallToAction.OnBackButton -> finish()
        }

    }

    private fun getAppSecurityOption(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.Success -> {
                appSecurityWidget.showAppSecurityOption(state.data as Int)
            }
        }
    }
}