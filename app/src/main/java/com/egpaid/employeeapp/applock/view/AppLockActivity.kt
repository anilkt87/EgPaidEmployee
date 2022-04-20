package com.egpaid.employeeapp.applock.view

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.applock.biomtericmanager.*
import com.egpaid.employeeapp.applock.viewmodel.AppLockViewModel
import com.egpaid.employeeapp.applock.widget.AppLockWidget
import com.egpaid.employeeapp.applock.widget.AppLockWidget.*
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel.State.Success
import com.egpaid.employeeapp.base.widget.Widget
import com.egpaid.employeeapp.home.view.HomeActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.view_app_lock_widget.*
import javax.inject.Inject

class AppLockActivity : AppCompatActivity() {

    @Inject
    lateinit var appLockWidget: AppLockWidget

    @Inject
    lateinit var appLockViewModel: AppLockViewModel

    private lateinit var cryptographyManager: CryptographyManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_app_lock)
        initWidget(app_lock_enter_pin_container)
        appLockViewModel.apply {
            observe(pinStateLiveData, ::GetAppLockState)
        }

        // we will Do Next Version  showBiometricPromptForEncryption()
    }

    private fun GetAppLockState(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.CREATE_PIN -> appLockWidget.displayCreatePin()
            is BaseViewModel.State.ENTER_PIN -> appLockWidget.displayEnterPin()
            is BaseViewModel.State.INCORRECT_PIN -> appLockWidget.wrongPasswordEntry()
            is Success -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initWidget(view: View) {
        appLockWidget.apply {
            initView(view)
            addWidget(this)
            show()
            observe(onClicked, ::onCreatePinClick)
        }


    }

    private fun addWidget(widget: Widget) {
        lifecycle.addObserver(widget)
    }

    private fun onCreatePinClick(callAction: CallToAction) {
        when (callAction) {
            is CallToAction.ValidateDigit -> {
                appLockViewModel.validatePin(callAction.digitValue)
            }
            is CallToAction.CreatePassword -> {
                appLockViewModel.saveAppLockPin(callAction.password)
            }
            is CallToAction.ChangePassword ->{
                appLockViewModel.changePassword(callAction.oldPassword,callAction.newpassword)
            }
            else -> {

            }
        }

    }

    private fun showBiometricPromptForEncryption() {
        val canAuthenticate = BiometricManager.from(this).canAuthenticate()
        if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
            val secretKeyName = this.getString(R.string.secret_key_name)
            cryptographyManager = CryptographyManager()
            val cipher = cryptographyManager.getInitializedCipherForEncryption(secretKeyName)
            val biometricPrompt =
                BiometricPromptUtils.createBiometricPrompt(this, ::encryptAndStoreServerToken)
            val promptInfo = BiometricPromptUtils.createPromptInfo(this)
            biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
        }
    }

    private fun encryptAndStoreServerToken(authResult: BiometricPrompt.AuthenticationResult) {
        authResult.cryptoObject?.cipher?.apply {
            SampleAppUser.fakeToken?.let { token ->
                Log.d(ContentValues.TAG, "The token from server is $token")
                val encryptedServerTokenWrapper = cryptographyManager.encryptData(token, this)
                cryptographyManager.persistCiphertextWrapperToSharedPrefs(
                    encryptedServerTokenWrapper,
                    applicationContext,
                    SHARED_PREFS_FILENAME,
                    Context.MODE_PRIVATE,
                    CIPHERTEXT_WRAPPER
                )
            }
        }
        finish()
    }


}