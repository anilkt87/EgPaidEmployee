package com.egpaid.employeeapp.applock.widget

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.egpaid.employeeapp.R

class GenericTextWatcher(private val view: View, private val editText: ArrayList<EditText>) :
    TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        val string = s.toString()
        when (view.id) {
            R.id.otp_edit_box1 -> {
                if (string.length == 1) editText[1].requestFocus()
            }
            R.id.otp_edit_box2 -> {

                if (string.length == 1) editText[2].requestFocus() else if (string.isEmpty()) editText[0].requestFocus()
            }
            R.id.otp_edit_box3 -> {
                if (string.length == 1) editText[3].requestFocus() else if (string.isEmpty()) editText[1].requestFocus()
            }
            R.id.otp_edit_box4 -> {
                if (string.isEmpty()) editText[2].requestFocus()

            }

        }
    }
}