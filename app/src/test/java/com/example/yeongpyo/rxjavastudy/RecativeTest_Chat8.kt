package com.example.yeongpyo.rxjavastudy

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class RecativeTest_Chat8 {
    val strings = arrayOf("1A", "2B", "3C")
    @Test
    fun rxfirst_Schedulers(){
        Observable.fromIterable(strings.asIterable())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
    }
}