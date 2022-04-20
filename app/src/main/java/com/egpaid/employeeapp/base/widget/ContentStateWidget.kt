package com.egpaid.employeeapp.base.widget

import kotlinx.android.extensions.LayoutContainer

interface ContentStateWidget<in T> : ContentViewWidget, LayoutContainer {
    fun showLoading()

    fun setContent(content: T)

    fun showGeneralError()

    fun showNetworkError()

    fun onRetry(action: () -> Unit)

    fun show()

    fun hide()
}