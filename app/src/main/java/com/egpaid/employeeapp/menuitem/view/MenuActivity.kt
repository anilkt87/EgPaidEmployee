package com.egpaid.employeeapp.menuitem.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.menuitem.viewmodel.MenuViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_menu_detail_view.*
import javax.inject.Inject

class MenuActivity : AppCompatActivity() {

    @Inject
    lateinit var menuViewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_menu_detail_view)
        val bundle = intent.extras
        if (bundle != null) {
            val position = bundle.getInt("menuItemPosition")
            menuViewModel.apply {
                getMyData(position)
                observe(stateLiveData, ::getMenuDetailItem)
            }

        }
    }

    private fun getMenuDetailItem(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.MyNavBarDetailPage -> {
                tv_title.text = state.data.pagename.toString()
            }

            else -> {

            }
        }
    }
}

