package com.example.yeongpyo.rxjavastudy

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Action
import io.reactivex.functions.BiFunction
import io.reactivex.observables.ConnectableObservable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.zipWith
import org.junit.Test
import java.util.concurrent.TimeUnit

class RecativeTest_Chat5 {

    val arr = arrayOf("1", "2", "3")
    @Test
    fun rxConcatMap(){
        Observable.interval(10L, TimeUnit.MILLISECONDS)
                .map { i -> arr[i.toInt()] }
                .take(arr.size.toLong())
                .concatMap { s -> Observable.interval(20L, TimeUnit.MILLISECONDS)
                        .map { s -> "$s <>" }
                        .take(arr.size.toLong())}
                .subscribe(System.out::println)
        Thread.sleep(5000)
    }

    @Test
    fun rxflatMap(){
        Observable.interval(20L, TimeUnit.MILLISECONDS)
                .map { i -> arr[i.toInt()] }
                .take(arr.size.toLong())
                .flatMap { s -> Observable.interval(30L, TimeUnit.MILLISECONDS).
                map { s -> "$s <>" }
                        .take(3)}
                .subscribe(System.out::println)
        Thread.sleep(5000)
    }

    @Test
    fun rxSwitchMap(){
        Observable.interval(10L, TimeUnit.MILLISECONDS).
                map { i -> arr[i.toInt()] }
                .take(arr.size.toLong())
                .doOnNext{ msg -> println("DoOn $msg ")
                Thread.sleep(200)}
                .switchMap { sw ->
                    Observable.interval(20L, TimeUnit.MILLISECONDS)
                            .take(arr.size.toLong())
                }.subscribe{ msg -> println("final $msg")}
                Thread.sleep(5000)
    }

    val grouparr = arrayOf("1", "2", "3", "2-A", "3-A")
    @Test
    fun rxgroupBy(){
           Observable.fromIterable(grouparr.asIterable())
                .groupBy(::getGrouping)//{ i -> getGrouping(i) }
                .subscribe{ subject -> subject.subscribe{ txt ->
                    println( "${subject.key} +  + $txt" )
                }}
        Thread.sleep(5000)
    }

    @Test
    fun rxgroupby_filter(){
        Observable.fromIterable(grouparr.asIterable())
                .groupBy { i -> getGrouping(i) }
                .subscribe { subject ->
                    subject.filter { i -> subject.key.equals("A") }
                            .subscribe { println("${subject.key} + + $it") }
                }
        Thread.sleep(4000)
    }

    fun getGrouping(txt : String) : String =
            if ( txt.endsWith("A") ) "Group A $txt"
            else if ( txt.endsWith("B")) "Group B $txt"
            else "Not Group $txt"

    @Test
    fun rxscan(){
        Observable.fromIterable(arr.asIterable())
                .scan{i1 , i2 -> "$i1 (${i2})"}
                .subscribe(System.out::println)
    }

    @Test
    fun rxzip(){
        val arrtxt = arrayOf("A", "B", "C")
        Observables.zip(
                Observable.fromIterable(arr.asIterable()),
                Observable.fromIterable(arrtxt.asIterable()),
                Observable.fromIterable(arr.asIterable())
        ){i1, i2, i3 -> "data1 :${i1}, data2 :${i2}, data3 :${i3}"}
                .subscribe(System.out::println)
    }

    //combinelatest
    @Test
    fun rxConbinlate(){
        val test1 = Observable.just(1,2,3,4,5)
        val test2 = Observable.just("one")
        val test3 = Observable.just("two")
        Observables.combineLatest(test1, test2, test3,
                {i1, i2, i3 -> "Observable $i1 , $i2 , $i3"})
                .subscribe(System.out::println)
    }

    @Test
    fun rxMerge(){
        val case1 = arrayOf("1", "3", "5")
        val case2 = arrayOf("2", "4")
        val MergeTest1 = Observable.interval(50L, 10L, TimeUnit.MILLISECONDS)
                .map { i -> case1[i.toInt()] }.take(case1.size.toLong())
        val MErgeTest2 = Observable.interval(10L, TimeUnit.MILLISECONDS)
                .map { i -> case2[i.toInt()] }.take(case2.size.toLong())
        Observable.merge(MergeTest1, MErgeTest2).subscribe(System.out::println)
        Thread.sleep(2000)
    }

    @Test
    fun rxConcat1(){
        val action = Action{ println("onComple")}
        val case1 = arrayOf("1", "3", "5")
        val case2 = arrayOf("2", "4", "6")
        val Concat1 = Observable.fromIterable(case1.asIterable())
                .doOnComplete(action)
        val Concat2 = Observable.fromIterable(case2.asIterable())
                .doOnComplete(action)
        Observable.concat(Concat1, Concat2)
                .doOnComplete(action)
                .subscribe(System.out::println)
    }

    @Test
    fun rxConcat2(){
        val action = Action{ println("onComple")}
        val case1 = arrayOf("1", "3", "5")
        val case2 = arrayOf("2", "4", "6")
        val Concat1 = Observable.fromIterable(case1.asIterable())
                .doOnComplete(action)
        val Concat2 = Observable.interval(10L, TimeUnit.MILLISECONDS)
                .map { i -> case2[i.toInt()] }.take(case2.size.toLong())
                .doOnComplete(action)
        Observable.concat(Concat1, Concat2)
                .doOnComplete(action)
                .subscribe(System.out::println)

        Thread.sleep(1000)
    }

}