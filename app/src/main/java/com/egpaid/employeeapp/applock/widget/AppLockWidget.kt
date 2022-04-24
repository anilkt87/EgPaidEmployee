package com.egpaid.employeeapp.applock.widget

import android.view.View
import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.widget.ContentStateWidget
import com.egpaid.employeeapp.base.widget.ContentViewWidget

interface AppLockWidget : ContentViewWidget{
    sealed class CallToAction {
        data class ValidateDigit(var digitValue: String) : CallToAction()
        data class CreatePassword(var password: String) : CallToAction()
        data class ChangePassword(
            var oldPassword: String,
            var newpassword: String
        ) : CallToAction()
    }

    val onClicked: LiveData<CallToAction>

    fun displayEnterPin()


    fun displayPatternOption()

    fun displayBiometricOption()
}