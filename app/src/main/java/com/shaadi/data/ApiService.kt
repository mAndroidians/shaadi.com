package com.shaadi.data



import androidx.lifecycle.LiveData
import com.shaadi.BuildConfig
import com.shaadi.data.remote.GetResultResponse
import com.shaadi.data.util.ApiResponse
import retrofit2.http.*

interface ApiService {
    companion object {
        var baseUrl: String = BuildConfig.BASE_URL
    }

     @GET("?results=10")
     fun getResult(): LiveData<ApiResponse<GetResultResponse>>



}
