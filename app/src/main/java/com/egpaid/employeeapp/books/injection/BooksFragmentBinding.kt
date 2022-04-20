package com.egpaid.employeeapp.books.injection

import com.egpaid.employeeapp.books.view.BooksFragment
import com.egpaid.employeeapp.base.di.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BooksFragmentBinding {
    @PerFragment
    @ContributesAndroidInjector(modules = [BooksFragmentModule::class])
    abstract fun bindBooksFragment(): BooksFragment

}