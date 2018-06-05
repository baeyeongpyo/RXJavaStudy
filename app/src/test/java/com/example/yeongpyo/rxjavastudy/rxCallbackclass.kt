package com.example.yeongpyo.rxjavastudy

import okhttp3.*
import java.io.IOException

class rxCallbackclass(){
    val url_1 = "https://raw.githubusercontent.com/yudong80/reactivejava/master"
    val url_2 = url_1 + "/samples/callback_hell"

    val onSuccess = object : Callback {
        override fun onFailure(call: Call?, e: IOException?) {}
        override fun onResponse(call: Call?, response: Response?) { println(response?.body()?.string()) }
    }

    fun http_run(){
        val request = Request.Builder()
                .url(url_1)
                .build()
        OkHttpClient().newCall(request)
                .enqueue(object  : Callback {
                    override fun onFailure(call: Call?, e: IOException?) {}
                    override fun onResponse(call: Call?, response: Response?) {
                        Request.Builder()
                                .url(url_2)
                                .build()
                                .let {
                                    OkHttpClient().newCall(it).enqueue(onSuccess)
                                }
                    }
                })
    }

}