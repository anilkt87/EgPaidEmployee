package com.egpaid.employeeapp.signin.view.widget

import android.content.Context
import android.text.Editable
import android.view.View
import com.egpaid.employeeapp.base.livedata.SingleLiveData
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import kotlinx.android.synthetic.main.activity_signin.*
import javax.inject.Inject

class SignInWidgetImpl @Inject constructor(
    private val context: Context,
    override val onClicked: SingleLiveData<SignInWidget.CallToAction>
) : SignInWidget {

    override lateinit var containerView: View
    override fun hideProgressBar() {
        app_wise_progress_circular.visibility = View.GONE
    }

    override fun showLoading() {
        app_wise_progress_circular.visibility = View.VISIBLE
    }

    override fun setContent(content: LoginResponseErrorModel) {

    }

    override fun showGeneralError() {
    }

    override fun showNetworkError() {
    }

    override fun onRetry(action: () -> Unit) {
    }

    override fun show() {
    }

    override fun hide() {
    }

    override fun initView(contentView: View) {
        this.containerView = contentView
        et_mobile.apply {
            addTextChangedListener(object : TextWatcherAdapter() {
                override fun afterTextChanged(s: Editable) {
                    ((!et_mobile.text.isNullOrEmpty() || !et_password.text.isNullOrEmpty()) && et_mobile.text.length == 10).also {
                        btn_login.isEnabled = it
                    }


                }
            })
        }

        et_password.apply {
            addTextChangedListener(object : TextWatcherAdapter() {
                override fun afterTextChanged(s: Editable) {
                    ((!et_mobile.text.isNullOrEmpty() || !et_password.text.isNullOrEmpty()) && et_mobile.text.length == 10).also {
                        btn_login.isEnabled = it
                    }
                }
            })
        }
        btn_login.setOnClickListener {
            if (et_mobile.text.isNullOrEmpty() || et_password.text.isNullOrEmpty()) {
                onClicked.value = SignInWidget.CallToAction.EmptyFiled
            } else {
                onClicked.value = SignInWidget.CallToAction.ValidateUser(
                    LoginRequestModel(
                        et_mobile.text.toString(),
                        et_password.text.toString()
                    )
                )
            }
        }
    }

}