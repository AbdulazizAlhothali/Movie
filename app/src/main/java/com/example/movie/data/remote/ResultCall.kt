package com.example.movie.data.remote

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.RuntimeException

class ResultCall<T>(val apiCall: Call<T>) :
    Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        apiCall.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                response.code(),
                                Result.success(response.body()!!)
                            )
                        )
                    } else {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                Result.failure(
                                    HttpException(response)
                                )
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    val errorMessage = when (t) {
                        is IOException -> "No internet connection"
                        is HttpException -> "Something went wrong!"
                        else -> t.localizedMessage
                    }
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(Result.failure(RuntimeException(errorMessage, t)))
                    )
                }
            }
        )
    }

    override fun isExecuted(): Boolean {
        return apiCall.isExecuted
    }

    override fun execute(): Response<Result<T>> {
        return Response.success(Result.success(apiCall.execute().body()!!))
    }

    override fun cancel() {
        apiCall.cancel()
    }

    override fun isCanceled(): Boolean {
        return apiCall.isCanceled
    }

    override fun clone(): Call<Result<T>> {
        return ResultCall(apiCall.clone())
    }

    override fun request(): Request {
        return apiCall.request()
    }

    override fun timeout(): Timeout {
        return apiCall.timeout()
    }
}
