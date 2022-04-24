package com.egpaid.employeeapp.appsecurity.widget

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.widget.ContentStateWidget
import com.egpaid.employeeapp.base.widget.ContentViewWidget

interface AppSecurityWidget : ContentViewWidget {
    sealed class CallToAction {
        object OnBackButton : CallToAction()
    }
    val onClicked: LiveData<CallToAction>
    fun showAppSecurityOption(position : Int)
}