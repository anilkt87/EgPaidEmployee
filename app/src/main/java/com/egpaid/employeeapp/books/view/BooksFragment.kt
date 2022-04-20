package com.egpaid.employeeapp.books.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.base.widget.Widget
import com.egpaid.employeeapp.books.entities.Books
import com.egpaid.employeeapp.books.view.widget.BooksWidget
import com.egpaid.employeeapp.books.viewmodel.BooksViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.view_books_widget.*
import javax.inject.Inject
import javax.inject.Named

class BooksFragment : Fragment() {

    companion object {

        fun newInstance(): BooksFragment {
            return BooksFragment()
        }
    }

    @Inject
    lateinit var booksViewModel: BooksViewModel

    @Inject
    @Named("PerFragment")
    lateinit var pdpListWidget: String

    @Inject
    lateinit var booksWidget: BooksWidget

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidget(books_container)
        booksViewModel.apply {

            observe(stateLiveData, ::onChangeState)
        }
    }

    override fun onResume() {
        super.onResume()
        booksViewModel.getPersonData()
    }

    private fun initWidget(view: View) {
        booksWidget.apply {
            initView(view)
            addWidget(this)
        }
    }

    private fun addWidget(widget: Widget) {
        lifecycle.addObserver(widget)
    }

    private fun onChangeState(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.Loading -> booksWidget.showLoading()
            is BaseViewModel.State.Success -> {
                booksWidget.setContent(state.data as Books)
            }

            is BaseViewModel.State.NetworkError -> booksWidget.showNetworkError()
            is BaseViewModel.State.GeneralError -> booksWidget.showGeneralError()
            else -> {

            }
        }

    }
}