package com.egpaid.employeeapp.home.view

import android.app.KeyguardManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.appsecurity.view.AppSecurityActivity
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.homedashboard.DrawerFragment
import com.egpaid.employeeapp.home.homedashboard.listner.FragmentDrawerListener
import com.egpaid.employeeapp.home.monitor.view.HomeFragment
import com.egpaid.employeeapp.home.viewmodel.MainActivityViewModel
import com.egpaid.employeeapp.menuitem.view.MenuActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), FragmentDrawerListener {
    private lateinit var drawerFragment: DrawerFragment

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var appPreference: AppPreference

    private val LOCK_REQUEST_CODE = 221
    private val SECURITY_SETTING_REQUEST_CODE = 233

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (appPreference.getAppSecurityOption() == 1) {
            authenticateApp()
        } else {
            mainActivityViewModel.apply {
                getMyAppSideBar()
                observe(stateLiveData, ::getMySideBarData)
            }
        }
    }


    private fun getMySideBarData(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.MySideBarData -> {
                app_wise_progress_circular.visibility = View.GONE
                displayView()
            }
            is BaseViewModel.State.Loading -> {
                app_wise_progress_circular.visibility = View.VISIBLE
            }
            is BaseViewModel.State.Error -> {
                app_wise_progress_circular.visibility = View.GONE
                displayView()
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDrawerItemSelected(view: View, position: Int) {
        displayView(position)
    }

    private fun displayView() {
        drawerFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
        drawerFragment.init(R.id.fragment_navigation_drawer, drawer_layout, toolbar)
        displayView(0)
    }

    private fun displayView(position: Int) {

        when (position) {
            0 -> {
                showDefaultPage()
            }
            3 -> {
                val intent = Intent(this, AppSecurityActivity::class.java)
                startActivity(intent)
            }
            else -> {
                val bundle = Bundle()
                bundle.putInt("menuItemPosition", position - 1)
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    private fun showDefaultPage() {
        val fragment = HomeFragment()
        title = "Home"
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_content, fragment)
        fragmentTransaction.commit()
        supportActionBar?.title = title
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            LOCK_REQUEST_CODE -> if (resultCode == RESULT_OK) {
                mainActivityViewModel.apply {
                    getMyAppSideBar()
                    observe(stateLiveData, ::getMySideBarData)
                }
            } else {
                //If screen lock authentication is failed update text
                //textView.setText(resources.getString(R.string.unlock_failed))
            }
            SECURITY_SETTING_REQUEST_CODE ->                 //When user is enabled Security settings then we don't get any kind of RESULT_OK
                //So we need to check whether device has enabled screen lock or not
                if (isDeviceSecure()) {
                    //If screen lock enabled show toast and start intent to authenticate user
                    Toast.makeText(
                        this,
                        "Device is Secure",
                        Toast.LENGTH_SHORT
                    ).show()
                    authenticateApp()
                } else {
                    Toast.makeText(
                        this,
                        "Device  Security is cancelled",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
        if (resultCode == 0) {
            finish()
        }
    }

    private fun isDeviceSecure(): Boolean {
        val keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager

        //this method only work whose api level is greater than or equal to Jelly_Bean (16)
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && keyguardManager.isKeyguardSecure

        //You can also use keyguardManager.isDeviceSecure(); but it requires API Level 23
    }

    private fun authenticateApp() {
        //Get the instance of KeyGuardManager
        val keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager

        //Check if the device version is greater than or equal to Lollipop(21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Create an intent to open device screen lock screen to authenticate
            //Pass the Screen Lock screen Title and Description
            val i = keyguardManager.createConfirmDeviceCredentialIntent(
                "Enter phone Screen lock pattern ,PIN,password or fingerprint",
                "Unlock Employee app"
            )
            try {
                startActivityForResult(i, LOCK_REQUEST_CODE)
            } catch (e: Exception) {
                val intent = Intent(Settings.ACTION_SECURITY_SETTINGS)
                try {
                    startActivityForResult(intent, SECURITY_SETTING_REQUEST_CODE)
                } catch (ex: Exception) {

                }
            }
        }
    }
}