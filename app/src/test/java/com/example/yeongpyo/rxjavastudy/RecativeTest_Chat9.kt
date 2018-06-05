package com.example.yeongpyo.rxjavastudy

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import org.junit.Test
import java.io.IOException
import java.util.concurrent.Executors

class RecativeTest_Chat9 {

    @Test
    fun rxSingle(){
        Observable.range(100, 5)
                .subscribeOn(Schedulers.single())
                .subscribe(System.out::println)
        Observable.range(0, 5)
                .subscribeOn(Schedulers.single())
                .subscribe(System.out::println)
        Thread.sleep(1000)
    }

    @Test
    fun rxExecutor(){
        val strings = arrayOf("1", "2", "3")
        val subject = Observable.fromIterable(strings.asIterable())
        val excutor = Executors.newFixedThreadPool(2)
        subject.subscribeOn(Schedulers.from(excutor))
                .subscribe(System.out::println)
        subject.subscribeOn(Schedulers.from(excutor))
                .subscribe(System.out::println)
        Thread.sleep(500)
    }

    @Test
    fun rxCallback(){
        val readMe_url = "https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md"
        val request = Request.Builder().url(readMe_url).build()
        OkHttpClient().newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {}
            override fun onResponse(call: Call?, response: Response?) { println(response?.body()?.string()) }
        })
        Thread.sleep(2000)
    }

    @Test
    fun rxcallback2_play(){
        rxCallbackclass().http_run()
        Thread.sleep(2000)
    }

}