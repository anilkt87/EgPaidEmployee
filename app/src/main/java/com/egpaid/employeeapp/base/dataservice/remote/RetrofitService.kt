package com.egpaid.employeeapp.base.dataservice.remote

import com.egpaid.employeeapp.books.entities.Books
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseModel
import com.egpaid.employeeapp.signin.entities.Person
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitService {

    @GET("public-api/users")
    fun getGetPerson( @Query("_format") format: String,@Query("access-token") accessToke:String): Single<Person>

    @GET("books/v1/volumes")
    fun getBooks( @Query("q") format: String,@Query("inauthor") accessToke:String): Single<Books>

    @POST("auth/login")
    fun loginUser(@Body userData: LoginRequestModel): Single<LoginResponseModel>
}