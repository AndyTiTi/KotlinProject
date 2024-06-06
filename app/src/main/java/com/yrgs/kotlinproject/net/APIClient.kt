package com.yrgs.kotlinproject.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {
    private object Holder {
        val INSTANCE = APIClient()
    }

    companion object {
        val instance = Holder.INSTANCE

        // 双重校验锁
//        fun getInstance(): APIClient {
//
//            if (instance == null) {
//                synchronized(APIClient::class.java) {
//                    if (instance == null) {
//                        instance = APIClient()
//                    }
//                }
//            }
//            return instance!!
//        }
    }

    fun <T> instanceRetrofit(apiInterface: Class<T>): T {
        var mOkHttpClient = OkHttpClient().newBuilder()
            .readTimeout(10000, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS)
            .writeTimeout(10000, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://wanandroid.com")
            .client(mOkHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(apiInterface);
    }
}