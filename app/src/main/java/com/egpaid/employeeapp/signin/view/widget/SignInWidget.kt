package com.egpaid.employeeapp.signin.view.widget

import android.app.Person
import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.widget.ContentStateWidget
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseModel

interface SignInWidget : ContentStateWidget<LoginResponseModel> {

    sealed class CallToAction {
        data class ValidateUser(var loginRequest: LoginRequestModel) : CallToAction()
    }
    val onClicked: LiveData<CallToAction>
}