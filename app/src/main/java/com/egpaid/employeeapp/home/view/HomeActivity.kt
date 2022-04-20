package com.egpaid.employeeapp.home.view

import android.Manifest
import android.app.AppOpsManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.homedashboard.HomeFragment
import com.egpaid.employeeapp.home.monitor.view.MonitorFragment
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.profile.ProfileFragment
import com.egpaid.employeeapp.home.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject
import javax.inject.Named


class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    @Inject
    @Named("PerActivity1")
    lateinit var pdpListWidget: String

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_home)
        navigation.setOnNavigationItemSelectedListener(this)
        loadFragment(HomeFragment())
        if (getGrantStatus()) {
            homeViewModel.getDailyAppUsage()
            homeViewModel.getWeeklyAppUsage()
        } else {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }
        homeViewModel.apply {
            getMonitorData()
            getMonitorWeeklyData()
            observe(stateLiveData, ::onChangeState)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> fragment = HomeFragment()
            R.id.navigation_monitor -> fragment = MonitorFragment()
            R.id.navigation_profile -> fragment = ProfileFragment()
        }

        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            return true
        }
        return false
    }


    /**
     * check if PACKAGE_USAGE_STATS permission is aloowed for this application
     *
     * @return true if permission granted
     */
    private fun getGrantStatus(): Boolean {
        val appOps = applicationContext
                .getSystemService(APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(), applicationContext.packageName
        )
        return if (mode == AppOpsManager.MODE_DEFAULT) {
            applicationContext.checkCallingOrSelfPermission(Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED
        } else {
            mode == AppOpsManager.MODE_ALLOWED
        }
    }

    private fun onChangeState(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.SuccessListData -> {
                state.response as List<Monitor>
            }
        }
    }
}