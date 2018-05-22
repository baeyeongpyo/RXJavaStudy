package com.example.yeongpyo.studytoandroid

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import org.junit.Test

class ReactiveXTest_Chat2 {
    @Test
    fun ColdObservable_AsyncSubJect(){
        val asyncsubject = AsyncSubject.create<String>()
        asyncsubject.subscribe(System.out::println)
        asyncsubject.onNext("Async 1")
        asyncsubject.onNext("Async 2")
        asyncsubject.onNext("Async 3")
        asyncsubject.subscribe(System.out::println)
        asyncsubject.onNext("Async 4")
        asyncsubject.onNext("Async 5")
        asyncsubject.subscribe(System.out::println)
        asyncsubject.onComplete()
        asyncsubject.onNext("Async 6")
        asyncsubject.onNext("Async 7")
        asyncsubject.onNext("Async 8")
        asyncsubject.subscribe(System.out::println)
        asyncsubject.onComplete()
    }


    @Test
    fun ColdObservable_AsyncSubJect2(){
        var asyncSubject = Observable.fromArray("Async 1", "Async 2", "Async 3", "Async 4")

        var subsribe_make = AsyncSubject.create<String>()
        subsribe_make.subscribe(System.out::println)

        asyncSubject.subscribe(subsribe_make)

    }

    @Test
    fun HotObervable_Behavior(){
        val subject = BehaviorSubject.createDefault("Behavior 1")
        subject.subscribe{ t -> println( "subject 1# $t")}
        subject.onNext("Behavior 2")
        subject.onNext("Behavior 3")
        subject.onNext("Behavior 4")
        subject.subscribe{ t -> println( "subject 2# $t")}
        subject.onNext("Behavior 5")
        subject.subscribe{ t -> println( "subject 3# $t")}
    }

}