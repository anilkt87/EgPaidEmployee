package com.egpaid.employeeapp.signin.view.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.applock.widget.AppLockWidget
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.base.widget.Widget
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.view.widget.SignInWidget
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_signin.*
import javax.inject.Inject
import javax.inject.Named


class SigninActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SignInViewModel

    @Inject
    lateinit var signInWidget: SignInWidget

    @Inject
    @Named("PerActivity")
    lateinit var pdpListWidget: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_signin)
        initWidget(container_sign_in)
        viewModel.apply {
            observe(signInLiveData, ::getLoginResponse)
        }

    }

    private fun initWidget(view: View) {
        signInWidget.apply {
            initView(view)
            addWidget(this)
            show()
            observe(onClicked, ::onSignInRequest)
        }


    }

    private fun addWidget(widget: Widget) {
        lifecycle.addObserver(widget)
    }

    private fun onSignInRequest(callAction: SignInWidget.CallToAction) {
        when (callAction) {
            is SignInWidget.CallToAction.ValidateUser -> {
                viewModel.getLoginData(
                    LoginRequestModel(
                        callAction.loginRequest.loginId,
                        callAction.loginRequest.password
                    )
                )
            }
        }
    }

    private fun getLoginResponse(loginstate: BaseViewModel.State) {
        when (loginstate) {
            is BaseViewModel.State.LoginSuccess -> Toast.makeText(this, "Sucess", Toast.LENGTH_LONG)
                .show()
            is BaseViewModel.State.Error -> Toast.makeText(this, "Fail", Toast.LENGTH_LONG)
                .show()
            else -> {

            }
        }
    }

}
