package com.egpaid.employeeapp.base.base.domain

import androidx.annotation.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Base use case class to be used in the main app and in libraries
 * Contains [CompositeDisposable] which keeps track of all [io.reactivex.Observable]
 * which has been created inside the use case.
 */
interface UseCase {

    /**
     * Tracks newly created [Disposable] by adding it to [CompositeDisposable]
     *
     * @param disposable new [Disposable] to be added to tracking
     * @return same [Disposable] added to tracking
     */
    fun trackDisposable(@NonNull disposable: Disposable): Disposable

    /**
     * Clears all tracked [Disposable] by invoking [CompositeDisposable.clear]
     */
    fun cleanup()

}