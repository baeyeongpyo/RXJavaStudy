package com.example.yeongpyo.studytoandroid

import io.reactivex.Observable
import io.reactivex.observables.ConnectableObservable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit

class ReactiveXTest_Chat2 {
    @Test
    fun ColdObservable_AsyncSubJect(){
        /*
        * onComplete 호출시에 동작됨
        * */
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
        /*
        * 기본 Default값을 설정이 필요하며
        * 값이 들어올때 호출됨
        * */
        val subject = BehaviorSubject.createDefault("Behavior 1")
        subject.subscribe{ t -> println( "subject 1# $t")}
        subject.onNext("Behavior 2")
        subject.onNext("Behavior 3")
        subject.onNext("Behavior 4")
        subject.subscribe{ t -> println( "subject 2# $t")}
        subject.onNext("Behavior 5")
        subject.subscribe{ t -> println( "subject 3# $t")}
    }

    @Test
    fun PublishSubject_() {
        /*
        * 값이 없으면 받을때까지 기다렸다가 호출됨
        * */
        val subject = PublishSubject.create<String>()
        subject.subscribe { data -> println("#1 $data") }
        subject.onNext("1")
        subject.onNext("2")
        subject.subscribe { data -> println("#2 $data") }
        subject.onNext("3")
        subject.onNext("4")
        subject.onNext("5")
        subject.onComplete()

    }

    @Test
    fun ReplaySubject_(){
        /*
        * Subscribe 전의 데이터를 다시 전부 읽어온다.
        * */
        val subject = ReplaySubject.create<String>()
        subject.subscribe{ data -> println("#1 $data")}
        subject.onNext("1")
        subject.onNext("2")
        subject.subscribe{ data -> println("#2 $data")}
        subject.onNext("3")
        subject.onComplete()
    }

    @Test
    fun ConnectableObservable_(){
        /*
        * interval 의 시간만큼 뒤에 동작하기 시작함
        * Connect를 해주어야만 동작을 한다.
        * */
        val arr = ArrayList<String>().apply {
            add("TEST 1")
            add("TEST 2")
            add("TEST 3")
            add("TEST 4")
        }
        val subject : ConnectableObservable<String> = Observable
                .interval(10L, TimeUnit.SECONDS)
                .map{ i -> arr[i.toInt()] }
                .take(arr.size.toLong())
                .publish()
        subject.subscribe{ data -> println("#1 $data")}
        subject.connect()
    }
}