package com.egpaid.employeeapp.signin.view.widget

import android.app.Person
import android.content.Context
import android.view.View
import com.egpaid.employeeapp.applock.widget.AppLockWidget
import com.egpaid.employeeapp.base.livedata.SingleLiveData
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseModel
import kotlinx.android.synthetic.main.activity_signin.*
import javax.inject.Inject

class SignInWidgetImpl @Inject constructor(
    private val context: Context,
    override val onClicked: SingleLiveData<SignInWidget.CallToAction>
) : SignInWidget {

    override lateinit var containerView: View

    override fun showLoading() {
    }

    override fun setContent(content: LoginResponseModel) {

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
        btn_login.setOnClickListener {
            onClicked.value = SignInWidget.CallToAction.ValidateUser(
                LoginRequestModel(
                    et_mobile.text.toString(),
                    et_password.text.toString()
                )
            )
        }
    }

}