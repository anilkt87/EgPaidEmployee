package com.egpaid.employeeapp.signin.view.widget

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.widget.ContentStateWidget
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel

interface SignInWidget : ContentStateWidget<LoginResponseErrorModel> {

    sealed class CallToAction {
        data class ValidateUser(var loginRequest: LoginRequestModel) : CallToAction()
    }
    val onClicked: LiveData<CallToAction>
}