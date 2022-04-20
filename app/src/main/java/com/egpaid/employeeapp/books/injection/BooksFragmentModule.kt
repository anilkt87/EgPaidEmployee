package com.egpaid.employeeapp.books.injection

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egpaid.employeeapp.base.adapter.DiffCallback
import com.egpaid.employeeapp.base.di.qualifier.ForFragment
import com.egpaid.employeeapp.base.di.scope.PerFragment
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.books.domain.GetBookUseCase
import com.egpaid.employeeapp.books.domain.GetBookUseCaseImpl
import com.egpaid.employeeapp.books.repository.BooksRepo
import com.egpaid.employeeapp.books.repository.BooksRepoImpl
import com.egpaid.employeeapp.books.view.BooksFragment
import com.egpaid.employeeapp.books.view.adapter.BookAdapter
import com.egpaid.employeeapp.books.view.adapter.BookAdapterImpl
import com.egpaid.employeeapp.books.view.widget.BooksWidget
import com.egpaid.employeeapp.books.view.widget.BooksWidgetImpl
import com.egpaid.employeeapp.books.viewmodel.BooksViewModel
import com.egpaid.employeeapp.books.viewmodel.BooksViewModelFactory
import com.egpaid.employeeapp.books.viewmodel.BooksViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class BooksFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: BooksFragment): Context = fragment.requireContext()

    @Provides
    @PerFragment
    fun provideLayoutInflater(@ForFragment context: Context): LayoutInflater = LayoutInflater.from(context)

    @Provides
    fun provideBooksRepository(booksRepo: BooksRepoImpl): BooksRepo = booksRepo

    @Provides
    internal fun provideUseCase(useCase: GetBookUseCaseImpl): GetBookUseCase = useCase

    @Provides
    @PerFragment
    fun provideBooksViewModel(
        fragment: BooksFragment,
        factory: BooksViewModelFactory
    ): BooksViewModel =
        ViewModelProvider(fragment, factory).get(BooksViewModel::class.java)

    @Provides
    @PerFragment
    fun bindBooksViewModel(booksViewModel: BooksViewModelImpl): ViewModel =
        booksViewModel

    @Provides
    @PerFragment
    fun provideStateLiveData(): MutableLiveData<BaseViewModel.State> = MutableLiveData()

    @PerFragment
    @Provides
    @Named("PerFragment")
    open fun provideInt(): String {
        return "hello"
    }

    @Provides
    @PerFragment
    fun provideBooksWidget(booksWidget: BooksWidgetImpl): BooksWidget = booksWidget


    @Provides
    @PerFragment
    @Named("ForBookAdapter")
    fun provideLinearLayoutManager(@ForFragment context: Context): RecyclerView.LayoutManager = LinearLayoutManager(context)

    @Provides
    @PerFragment
    @Named("ForBookAdapter")
    fun provideDiffCallback(): DiffCallback = DiffCallback()

    @Provides
    @PerFragment
    fun provideBooksAdapter(adapter: BookAdapterImpl): BookAdapter = adapter

}