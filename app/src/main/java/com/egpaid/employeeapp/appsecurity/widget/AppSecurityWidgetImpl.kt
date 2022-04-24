package com.egpaid.employeeapp.appsecurity.widget

import android.content.Context
import android.view.View
import android.widget.Toast
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.livedata.SingleLiveData
import kotlinx.android.synthetic.main.activity_app_secutiry.*
import kotlinx.android.synthetic.main.activity_app_secutiry.view.*
import kotlinx.android.synthetic.main.view_register_pin.view.*
import javax.inject.Inject

class AppSecurityWidgetImpl @Inject constructor(
    val context: Context,
    val appPreference: AppPreference,
    override val onClicked: SingleLiveData<AppSecurityWidget.CallToAction>
) : AppSecurityWidget {
    lateinit var containerView: View


    override fun initView(contentView: View) {
        this.containerView = contentView
        contentView.apply {
            tv_go_to_previous.setOnClickListener {
                onClicked.value = AppSecurityWidget.CallToAction.OnBackButton
            }
            txt_enable_pin.setOnClickListener {
                showAppSecurityOption(1)
            }
            txt_enable_pattern.setOnClickListener {
                showAppSecurityOption(2)
                appPreference.setAppSecurityOption(2)
            }
            tx_enable_biomeeric.setOnClickListener {
                showAppSecurityOption(3)
                appPreference.setAppSecurityOption(3)
            }
            btn_register_pin.setOnClickListener {
                appPreference.setAppSecurityOption(1)
                appPreference.saveAppLockPin(et_enter_password.text.toString())
                Toast.makeText(context, "SuccessFully Register", Toast.LENGTH_LONG).show()
                onClicked.value = AppSecurityWidget.CallToAction.OnBackButton
            }
        }

    }

    override fun showAppSecurityOption(position: Int) {
        containerView.apply {
            when (position) {
                1 -> {
                    displayAppEnterPinRegister()
                }
                2 -> {
                    txt_enable_pin_stats.visibility = View.GONE
                    txt_pattern_status.visibility = View.VISIBLE
                    txt_biometric_status.visibility = View.GONE
                }
                3 -> {
                    txt_enable_pin_stats.visibility = View.GONE
                    txt_pattern_status.visibility = View.GONE
                    txt_biometric_status.visibility = View.VISIBLE
                }
                else -> {
                    txt_enable_pin_stats.visibility = View.GONE
                    txt_pattern_status.visibility = View.GONE
                    txt_biometric_status.visibility = View.GONE
                }
            }

        }


    }

    private fun displayAppEnterPinRegister() {
        containerView.apply {

            if (appPreference.getAppLockPin().isEmpty()) {
                app_security_main_container.visibility = View.GONE
                container_register_pin.visibility = View.VISIBLE
            } else {
                appPreference.setAppSecurityOption(1)
                txt_enable_pin_stats.visibility = View.VISIBLE
                txt_pattern_status.visibility = View.GONE
                txt_biometric_status.visibility = View.GONE
            }
        }

    }

}