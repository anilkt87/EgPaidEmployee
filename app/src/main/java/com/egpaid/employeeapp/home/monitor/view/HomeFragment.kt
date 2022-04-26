package com.egpaid.employeeapp.home.monitor.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.homedashboard.adapter.MyGridViewAdapter
import com.egpaid.employeeapp.home.homedashboard.listner.GrdViewListener
import com.egpaid.employeeapp.home.monitor.viewmdel.HomeDataViewModel
import com.egpaid.employeeapp.home.view.entities.Menu
import com.egpaid.employeeapp.home.view.entities.myprofile.MyProfile
import com.egpaid.employeeapp.merchentonboarding.view.MerchantOnBoardingActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home_view.*
import kotlinx.android.synthetic.main.item_visitin_card_view_home.*
import kotlinx.android.synthetic.main.nav_drawer_row.view.*
import javax.inject.Inject


class HomeFragment : Fragment(), GrdViewListener {

    @Inject
    lateinit var homeDataViewModel: HomeDataViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_view, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        homeDataViewModel.apply {
            getMySideBarOnHomePageItem()
            bindMyProfileData()
            observe(stateLiveData, ::getHomeSideBarData)
        }

    }

    private fun getHomeSideBarData(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.MySideBarHomePage -> {

                recyclerView.layoutManager = LinearLayoutManager(context)

                recyclerView.adapter = MyGridViewAdapter(requireContext(), state.data, this)
            }

            is BaseViewModel.State.MyHomePageItemSelected -> {

                redirectToCorrespondingPage(state.data)

            }
            is BaseViewModel.State.MyProfileResponse -> {
                showMyProfile(state.data)
            }

            else -> {

            }
        }

    }

    override fun onHomeItemSelected(view: View, position: Int) {
        homeDataViewModel.getItemData(position)
    }

    private fun redirectToCorrespondingPage(menuItem: Menu?) {

        if (menuItem?.pagename == "Merchant") {
            val intent = Intent(requireContext(), MerchantOnBoardingActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "Work is Under Progress", Toast.LENGTH_LONG).show()
        }

    }

    private fun showMyProfile(data: MyProfile) {
        txt_name.text = data.name
        txt_job_role.text = data.department
        txt_phone.text = data.mobile
        txt_email.text = data.officeemail
        Glide.with(requireContext())
            .load(data.profileimg)
            .into(img_profile)
        txt_company_address.text =
            data.address + " " + data.city + " " + data.district + " " + data.state + data.pincode
    }

}