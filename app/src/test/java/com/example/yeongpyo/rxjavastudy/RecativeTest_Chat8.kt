package com.example.yeongpyo.rxjavastudy

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.TimeUnit

class RecativeTest_Chat8 {
    val strings = arrayOf("1A", "2B", "3C")
    @Test
    fun rxSchedulers1() {
        Observable.fromIterable(strings.asIterable())
                .doOnNext { println("Original data = $it") }
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .map { "$it MAP" }
                .subscribe(System.out::println)
        Thread.sleep(400)
    }

    @Test
    fun rxScheduler2() {
        Observable.fromIterable(strings.asIterable())
                .doOnNext { println("Origin data : $it") }
                .subscribeOn(Schedulers.newThread())
                .map { "Scjedulers data $it" }
                .subscribe(System.out::println)
        Thread.sleep(100)
    }

    /*
    * 뉴 스레드 스케쥴러         -> newThread()
    * 싱글 스레드 스케쥴러       -> single()
    * 계산 스케줄러             -> computation()
    * IO 스케쥴러               -> io()
    * 트램펄린 스케쥴러          -> trampoline()
    * */

    @Test
    fun rxNewScheduler() {
        Observable.fromIterable(strings.asIterable())
                .doOnNext { println(" Origin Data : $it") }
                .map { "<< $it >>" }
                .subscribeOn(Schedulers.newThread())
                .subscribe(System.out::println)
//        Thread.sleep(100)

        Observable.fromIterable(strings.asIterable())
                .doOnNext { println(" Origin Data : $it") }
                .map { "## $it ##" }
                .subscribeOn(Schedulers.newThread())
                .subscribe(System.out::println)
        Thread.sleep(100)
    }

    @Test
    fun rxComputation() {
        val subject = Observable.fromIterable(strings.asIterable())
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS)) { a, b -> a }
        subject.map { "<< $it >>" }
                .subscribeOn(Schedulers.computation())
                .subscribe(System.out::println)

        subject.map { "## $it ##" }
                .subscribeOn(Schedulers.computation())
                .subscribe(System.out::println)

        Thread.sleep(1000)

    }

    @Test
    fun rxIO(){
        //
    }

    @Test
    fun trampoline(){
        val strings = arrayOf("1", "2", "3")
        Observable.fromIterable(strings.asIterable())
                .subscribeOn(Schedulers.trampoline())
                .map { "<< $it >>" }
                .subscribe(System.out::println)

        Thread.sleep(300)
    }
}