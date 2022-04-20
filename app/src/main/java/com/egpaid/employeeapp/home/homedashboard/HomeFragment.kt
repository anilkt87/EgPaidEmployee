package com.egpaid.employeeapp.home.homedashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var appPreference: AppPreference

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!appPreference.isChildMode) {
            btn_setup_child_mode.text = "Setup Child Mode"
        } else {
            btn_setup_child_mode.text = "Disable  Child Mode"
        }
        btn_setup_child_mode.setOnClickListener {

            appPreference.isChildMode = !appPreference.isChildMode


            if (!appPreference.isChildMode) {
                btn_setup_child_mode.text = "Setup Child Mode"
            } else {
                btn_setup_child_mode.text = "Disable  Child Mode"
            }
        }
    }

}