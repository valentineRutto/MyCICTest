package com.valentinerutto.mycictest.data.netwokr

import com.valentinerutto.mycictest.data.remote.UserPostData
import com.valentinerutto.mycictest.data.remote.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService{
    @POST("/auth/login")
    fun login( @Body myUser: UserPostData): Response<UserResponse>
}