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
import kotlinx.android.synthetic.main.view_app_lock_registraion.*
import kotlinx.android.synthetic.main.view_app_lock_widget.*
import kotlinx.android.synthetic.main.view_change_password.*
import kotlinx.android.synthetic.main.view_enter_pin.*
import javax.inject.Inject


class AppLockWidgetImpl @Inject constructor(
        private val context: Context,
        override val onClicked: SingleLiveData<AppLockWidget.CallToAction>
) : AppLockWidget {
    override lateinit var containerView: View

    override fun setContent(content: View) {

    }

    override fun initView(contentView: View) {
        this.containerView = contentView

    }

    override fun displayCreatePin() {
        verify_otp_btn.visibility = View.VISIBLE
        app_lock_change_pin_container.visibility = View.GONE
        container_enter_pin_layout.visibility = View.GONE
        app_lock_registration_pin_container.visibility = View.VISIBLE
        verify_otp_btn.visibility = View.GONE
        tv_set_pin.visibility = View.GONE

        tv_preview.setOnClickListener {
            if (tv_preview.text.toString() == "Preview") {
                et_enter_password.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                et_enter_confirm_password.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                tv_preview.text = "Hide"
            } else {
                et_enter_password.transformationMethod = PasswordTransformationMethod.getInstance()
                et_enter_confirm_password.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                tv_preview.text = "Preview"
            }
        }

        et_enter_password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (et_enter_password.text.length == 4 && isNumber(et_enter_password.text.toString())) {
                    et_enter_password.visibility = View.GONE
                    et_enter_confirm_password.visibility = View.VISIBLE
                    tv_preview.text = "Confirm Pin"
                    verify_otp_btn.visibility = View.VISIBLE
                    tv_set_pin.visibility = View.VISIBLE

                } else {
                    Toast.makeText(context, "Please enter valid Pin", Toast.LENGTH_LONG).show()
                }

                true
            } else {
                false
            }
        }

        et_enter_confirm_password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                confirmPassWordLogin()

                true
            } else {
                false
            }
        }
        tv_set_pin.setOnClickListener {
            confirmPassWordLogin()
        }

        verify_otp_btn.setOnClickListener { _ ->

//            if (et_enter_password.text.length == 4 &&
//                et_enter_confirm_password.text.length == 4 &&
//                isNumber(et_enter_password.text.toString()) &&
//                isNumber(et_enter_confirm_password.text.toString())
//                && et_enter_password.text.toString() == et_enter_confirm_password.text.toString()
//            ) {
//
//                et_error_view.visibility = View.GONE
//                onClicked.value = AppLockWidget.CallToAction.CreatePassword(
//                    et_enter_password.text.toString()
//                )
//            } else {
//                et_error_view.visibility = View.VISIBLE
//            }

        }

    }

    override fun displayEnterPin() {
//        verify_otp_btn.visibility = View.GONE
        app_lock_change_pin_container.visibility = View.GONE
        container_enter_pin_layout.visibility = View.VISIBLE
        app_lock_registration_pin_container.visibility = View.GONE
        tv_change_password.visibility = View.GONE
        val edit = ArrayList<EditText>()
        edit.add(otp_edit_box1)
        edit.add(otp_edit_box2)
        edit.add(otp_edit_box3)
        edit.add(otp_edit_box4)
        otp_edit_box1.addTextChangedListener(GenericTextWatcher(otp_edit_box1, edit))
        otp_edit_box2.addTextChangedListener(GenericTextWatcher(otp_edit_box2, edit))
        otp_edit_box3.addTextChangedListener(GenericTextWatcher(otp_edit_box3, edit))
        otp_edit_box4.addTextChangedListener(GenericTextWatcher(otp_edit_box4, edit))


        verify_otp_btn.text = "Login"
        verify_otp_btn.setOnClickListener { _ ->
            var pin =
                    "${otp_edit_box1.text}${otp_edit_box2.text}${otp_edit_box3.text}${otp_edit_box4.text}"
            Log.d("PINVALUE", pin)
            if (pin.length == 4) {
                onClicked.value = AppLockWidget.CallToAction.ValidateDigit(pin)
            }

        }

        tv_change_password.setOnClickListener {
            displayChangePasswordView()
        }

    }

    override fun wrongPasswordEntry() {
        et_password_error_view.visibility = View.VISIBLE
    }

    private fun displayChangePasswordView() {
//        verify_otp_btn.visibility = View.GONE
        app_lock_change_pin_container.visibility = View.VISIBLE
        container_enter_pin_layout.visibility = View.GONE
        app_lock_registration_pin_container.visibility = View.GONE
        verify_otp_btn.text = "Change"

        verify_otp_btn.setOnClickListener {
            if (et_enter_old_password.text.length == 4 &&
                    et_enter_new_change_password.text.length == 4 &&
                    et_enter_new_change_confirm_password.text.length == 4 &&
                    et_enter_new_change_password.text.toString() == et_enter_new_change_confirm_password.text.toString()
            ) {
                et_password_error_view.visibility = View.GONE
                onClicked.value = AppLockWidget.CallToAction.ChangePassword(
                        et_enter_old_password.text.toString(),
                        et_enter_new_change_confirm_password.text.toString()
                )
            } else {
                et_password_error_view.visibility = View.VISIBLE
            }
        }

    }

    override fun show() {
    }

    override fun hide() {
    }

    override fun showLoading() {
    }

    override fun showGeneralError() {
    }

    override fun showNetworkError() {
    }

    override fun onRetry(action: () -> Unit) {
    }

    fun isNumber(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (ex: NumberFormatException) {
            false
        }
    }

    private fun confirmPassWordLogin() {
        if (et_enter_confirm_password.text.length == 4 &&
                isNumber(et_enter_confirm_password.text.toString())
                && et_enter_password.text.toString() == et_enter_confirm_password.text.toString()) {
            onClicked.value = AppLockWidget.CallToAction.CreatePassword(
                    et_enter_password.text.toString()
            )
        } else {
            et_enter_confirm_password.setTextColor(Color.parseColor("#EE4B2B"))
        }

    }
}