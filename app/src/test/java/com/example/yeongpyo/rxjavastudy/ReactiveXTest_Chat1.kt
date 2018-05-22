package com.example.yeongpyo.studytoandroid

import io.reactivex.Observable
import org.junit.Test

class ReactiveXTest_Chat1{
    @Test
    fun Create_fun0(){
        Observable.create<String> {
            it.onNext("a")
            it.onNext("b")
            it.onNext("c")
            it.onNext("d")
            it.onComplete()
        }.subscribe( System.out::println )
    }
    @Test
    fun ArrayToBoxing(){
        val Arr = arrayOf("a","b","c","d")
        var intArr : Array<Int> = arrayOf(1,2,3,4,5)
        Observable.fromArray(1,2,3,4,5).subscribe(System.out::println)
        Observable.fromIterable(Arr.asIterable()).subscribe(System.out::println)
    }


}