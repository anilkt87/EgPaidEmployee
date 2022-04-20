package com.egpaid.employeeapp.books.view.widget

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.egpaid.employeeapp.books.entities.Books
import com.egpaid.employeeapp.books.entities.ItemsItem
import com.egpaid.employeeapp.books.view.adapter.BookAdapter
import com.egpaid.employeeapp.books.view.widget.BooksWidget.*
import kotlinx.android.synthetic.main.view_books_loaded.*
import kotlinx.android.synthetic.main.view_books_widget.*
import javax.inject.Inject
import javax.inject.Named

class BooksWidgetImpl @Inject constructor(
    private val bookAdapter: BookAdapter,
    @Named("ForBookAdapter")
    private val linearLayoutManager: RecyclerView.LayoutManager
) : BooksWidget {
    override lateinit var containerView: View

    override fun initView(contentView: View) {
        this.containerView = contentView
        books_list.apply {
            adapter = bookAdapter
            layoutManager = linearLayoutManager
        }
    }


    override fun showLoading() = showState(State.LOADING)


    override fun setContent(content: Books) {
        content?.let {
            bookAdapter.setBooks(books = it.items as List<ItemsItem>)
        }

        showState(State.CONTENT)
    }

    override fun showGeneralError() = showState(State.GENERAL_ERROR)

    override fun showNetworkError() = showState(State.NETWORK_ERROR)

    override fun onRetry(action: () -> Unit) {
    }

    override fun show() {
        books_view_flipper.visibility = View.VISIBLE
    }

    override fun hide() {
        books_view_flipper.visibility = View.GONE
    }


    private fun showState(state: State) {
        if (books_view_flipper.displayedChild != state.ordinal) {
            books_view_flipper.displayedChild = state.ordinal
        }
    }

}