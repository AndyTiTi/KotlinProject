package com.yrgs.kotlinproject.api

import io.reactivex.Observable
import com.yrgs.kotlinproject.entity.LoginResponse
import com.yrgs.kotlinproject.entity.LoginResponseWrapper
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WanAndroidAPI {
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<LoginResponseWrapper<LoginResponse>>
}