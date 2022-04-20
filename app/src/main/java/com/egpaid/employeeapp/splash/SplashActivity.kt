package com.egpaid.employeeapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.signin.view.view.SigninActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable { //This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this, SigninActivity::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 3000)
    }
}