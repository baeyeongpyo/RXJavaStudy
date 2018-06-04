package com.example.yeongpyo.rxjavastudy

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

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
        val readMe_url = "Http://"
//        Request.buildr().url(readMe_url).build();
    }

}