package com.egpaid.employeeapp.applock.widget

import android.view.View
import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.widget.ContentStateWidget

interface AppLockWidget : ContentStateWidget<View> {
    sealed class CallToAction {
        data class ValidateDigit(var digitValue: String) : CallToAction()
        data class CreatePassword(var password: String) : CallToAction()
        data class ChangePassword(
            var oldPassword: String,
            var newpassword: String
        ) : CallToAction()
    }

    val onClicked: LiveData<CallToAction>

    fun displayCreatePin()
    fun displayEnterPin()

    fun wrongPasswordEntry()
}