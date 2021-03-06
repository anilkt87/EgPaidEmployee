package com.egpaid.employeeapp.merchentonboarding.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.egpaid.employeeapp.R
import dagger.android.AndroidInjection

class MerchantOnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_merchent_onboarding)
    }
}