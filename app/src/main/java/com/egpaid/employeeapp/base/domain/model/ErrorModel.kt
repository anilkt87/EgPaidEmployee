package com.egpaid.employeeapp.base.domain.model

import com.egpaid.employeeapp.base.domain.ResponseServiceCode


/**
 * Default error model that comes from server if something goes wrong with a repository call
 */
data class ErrorModel(
        val key: String = "",
        val message: String = "",
        val code: Int = -1,
        var responseCode: ResponseServiceCode? = null,
        var isServiceError: Boolean = false
)