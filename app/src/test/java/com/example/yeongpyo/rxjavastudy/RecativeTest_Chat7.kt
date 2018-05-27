package com.example.yeongpyo.rxjavastudy

import io.reactivex.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

class RecativeTest_Chat7 {
    val data = arrayOf(1,2,3,4,5,6)
    @Test
    fun rxCount(){
        Observable.fromIterable(data.asIterable())
                .count()
                .subscribe{ i -> println(i) }
    }

    val dataString = arrayOf("1","2","3","4","5","6")
    @Test
    fun rxDelay(){
        Observable.fromIterable(dataString.asIterable())
                .delay (100L, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println)
        Thread.sleep(100)
    }

    @Test
    fun rxTimeInterval(){
        Observable.fromIterable(dataString.asIterable())
                .delay { item -> Observable.just(item) }
                .delay(100L , TimeUnit.MILLISECONDS)
                .timeInterval()
                .subscribe(System.out::println)
        Thread.sleep(100)
    }


}