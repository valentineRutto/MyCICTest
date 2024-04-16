package com.valentinerutto.mycictest.data

import com.valentinerutto.mycictest.data.netwokr.ApiService
import com.valentinerutto.mycictest.data.remote.UserPostData
import com.valentinerutto.mycictest.data.remote.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LoginRepository(private val apiService: ApiService) {

    fun postUser(userPostData: UserPostData): Flow<Resource<UserResponse>> = flow {

        emit(Resource.Loading())

        try {
            val remoteResponse = apiService.login(userPostData)

            if (remoteResponse.isSuccessful.not()) {
                emit(
                    Resource.Error("something went wrong: ${remoteResponse.message()}")
                )
            }

            Resource.Success(remoteResponse.body())

        } catch (e: HttpException) {
            emit(
                Resource.Error("something went wrong: ${e.message()}")
            )
        } catch (e: IOException) {
            emit(
                Resource.Error("something went wrong:${e.message}")
            )
        }
    }
}