package com.example.yeongpyo.rxjavastudy

import android.graphics.drawable.shapes.Shape
import io.reactivex.Observable
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class RecativeTest_Chat4 {
    @Test
    fun rxInterval(){
        Observable.interval(1L, TimeUnit.MILLISECONDS)
                .map{ data -> (data +1) * 100}
                .take(5)
                .subscribe(System.out::println)
        Thread.sleep(1000)
    }

    @Test
    fun rxTimer(){
        Observable.timer(100L, TimeUnit.MILLISECONDS)
                .map { notUsed ->
                    SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                            .format(Date())
                }
                .subscribe(System.out::println)
        Thread.sleep(1000)
    }

    @Test
    fun rxRange(){
        Observable.range(1, 20)
                .filter { i -> i % 2 == 0 }
                .subscribe(System.out::println)
    }

    @Test
    fun rxIntervalRange(){
        Observable.intervalRange(
                1L,
                5L,
                100L,
                100L,
                TimeUnit.MILLISECONDS)
                .subscribe(System.out::println)
        Thread.sleep(1000)
    }
    val colors = Arrays.asList("1", "2", "3", "4", "5").iterator()
    fun getObservable() : Observable<String> {
        if ( colors.hasNext() ){
            var color = colors.next()
            return Observable.just(
                    "${color}-B",
                    "${color}-R",
                    "${color}-P")
        }
        return Observable.empty()
    }

    @Test
    fun rxDefer(){
        val callable = Callable<Observable<String>>{ getObservable() }
        val subject = Observable.defer(callable)
        subject.subscribe(System.out::println)
        subject.subscribe(System.out::println)
        subject.subscribe(System.out::println)
        subject.subscribe(System.out::println)
    }

    @Test
    fun repeat() {
        val subject = Observable.just("A", "B", "C", "D")
                .repeat(3)
        subject.doOnComplete { println("Complete") }
                .subscribe(System.out::println)
    }
}