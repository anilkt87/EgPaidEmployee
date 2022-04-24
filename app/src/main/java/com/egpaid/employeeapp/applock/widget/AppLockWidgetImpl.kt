package com.egpaid.employeeapp.applock.widget

import android.content.Context
import android.graphics.Color
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.egpaid.employeeapp.base.livedata.SingleLiveData
import kotlinx.android.synthetic.main.activity_app_lock.view.*
import kotlinx.android.synthetic.main.view_app_lock_registraion.*
import kotlinx.android.synthetic.main.view_app_lock_widget.*
import kotlinx.android.synthetic.main.view_change_password.*
import kotlinx.android.synthetic.main.view_enter_pin.*
import javax.inject.Inject


class AppLockWidgetImpl @Inject constructor(
    private val context: Context,
    override val onClicked: SingleLiveData<AppLockWidget.CallToAction>
) : AppLockWidget {
    lateinit var containerView: View


    override fun initView(contentView: View) {
        this.containerView = contentView
        contentView.apply {
            btn_enter_pin_confirm.setOnClickListener {
                onClicked.value =
                    AppLockWidget.CallToAction.ValidateDigit(et_enter_pin.text.toString())
            }
        }
    }


    override fun displayEnterPin() {
        containerView.apply {
            container_pin_option.visibility = View.VISIBLE
            container_pattern_option.visibility = View.GONE
            container_biomtric_option.visibility = View.GONE

        }
    }

    override fun displayPatternOption() {
        containerView.apply {
            container_pin_option.visibility = View.GONE
            container_pattern_option.visibility = View.VISIBLE
            container_biomtric_option.visibility = View.GONE
        }
    }

    override fun displayBiometricOption() {
        containerView.apply {
            container_pin_option.visibility = View.GONE
            container_pattern_option.visibility = View.GONE
            container_biomtric_option.visibility = View.VISIBLE

        }
    }
}


