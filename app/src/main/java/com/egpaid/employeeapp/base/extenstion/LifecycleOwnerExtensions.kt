package com.egpaid.employeeapp.base.extenstion

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, body: (T) -> Unit = {}) {
    liveData.observe(this, Observer { it?.let { body(it) } })
}

fun <T> LifecycleOwner.observeOnce(liveData: LiveData<T>, body: (T) -> Unit = {}) {
    liveData.observe(this, object: Observer<T> {
        override fun onChanged(value: T) {
            liveData.removeObserver(this)
            body(value)
        }
    })
}