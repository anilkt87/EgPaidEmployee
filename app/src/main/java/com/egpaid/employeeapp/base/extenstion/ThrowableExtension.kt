package com.egpaid.employeeapp.base.extenstion

    fun Throwable.isNetworkError() = this is java.io.IOException