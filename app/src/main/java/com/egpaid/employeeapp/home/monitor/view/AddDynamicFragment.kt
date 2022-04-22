package com.egpaid.employeeapp.home.monitor.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.egpaid.employeeapp.R
import kotlinx.android.synthetic.main.fragment_dynamic.*

class AddDynamicFragment : Fragment() {
    private var textFragment = ""

    companion object {
        fun newInstance(text: String): AddDynamicFragment {
            val fragment = AddDynamicFragment()
            val bundle = Bundle()
            bundle.putString("AddFragment", text)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textFragment = arguments?.get("AddFragment").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dynamic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title.text = textFragment
    }
}