package com.egpaid.employeeapp.base.domain

import com.egpaid.employeeapp.base.domain.model.ErrorModel


open class ResultData(val data: Any? = null, val errorModel: ErrorModel? = null) {
    companion object {
        fun fromData(data: Any): ResultData {
            return ResultData(data, null)
        }
        
        fun fromError(data: Any? = null, errorModel: ErrorModel): ResultData {
            return ResultData(data, errorModel)
        }
        
    }
    
    fun isError(): Boolean {
        return errorModel != null
    }
    
    fun isSuccess(): Boolean {
        return data != null
    }
}