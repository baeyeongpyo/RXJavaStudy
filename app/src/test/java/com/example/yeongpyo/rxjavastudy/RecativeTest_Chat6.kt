package com.example.yeongpyo.rxjavastudy

import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.zipWith
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit

class RecativeTest_Chat6 {

    @Test
    fun rxamb(){
        val data1 = arrayOf("1", "2", "3")
        val data2 = arrayOf("2-data", "3-data")
        val arr = Arrays.asList(
                Observable.fromIterable(data1.asIterable()).doOnComplete{ println(" data 1 Print ")},
                Observable.fromIterable(data2.asIterable()).delay(100L, TimeUnit.MILLISECONDS)
                        .doOnComplete { println(" data 2 Print ") })

        Observable.amb(arr)
                .doOnComplete { println(" amb Print ") }
                .subscribe(System.out::println)
        Thread.sleep(1000)
    }

    @Test
    fun rxTakeUntil(){
        val data = arrayOf("1", "2", "3", "4", "5", "6")
        Observable.fromIterable(data.asIterable())
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS)
                , {value , notused -> value})
                .takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS))
                .subscribe(System.out::println)
        Thread.sleep(1000)
    }

    @Test
    fun rxTakeUntil2(){
        val data = arrayOf("1", "2", "3", "4", "5", "6")
        Observable.fromIterable(data.asIterable())
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS))
                {value , notused -> value}
                .takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS))
                .subscribe(System.out::println)
        Thread.sleep(1000)
    }

    @Test
    fun rxSkipUntil(){
        val data = arrayOf("1", "2", "3", "4", "5", "6")
        Observable.fromIterable(data.asIterable())
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), {value, notUsed -> value})
                .skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS))
                .subscribe(System.out::println)
        Thread.sleep(1000)
    }

    @Test
    fun rxall(){
        val data = arrayOf("1", "2", "3", "4", "5", "6")
        Observable.fromIterable(data.asIterable())
                .all { i -> i.toInt() < 10 }
                .subscribe { i -> println("$i") }

        Observable.fromIterable(data.asIterable())
                .all { i -> i.toInt() < 5 }
                .subscribe { i -> println("$i") }
    }

}