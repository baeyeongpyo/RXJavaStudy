package com.example.yeongpyo.rxjavastudy

import io.reactivex.Observable
import io.reactivex.functions.Function
import org.junit.Test
import java.util.*


class ReactiveXTest_Chat3 {


    @Test
    fun RxMap(){
        val map_function : Function<String, String> = Function { test -> "$test Test" }
        Observable.just("1", "2", "3")
                .map(map_function)
                .subscribe(System.out::println)
        println(map_function.apply("TEST"))
    }

    @Test
    fun RxMap_Type(){
        val map_funciton = Function<String, Int> { test ->
            when(test){
                "1" -> 1
                "2" -> 2
                "3" -> 3
                else -> 0
            }
        }
        Observable.just("1", "2", "3", "4")
                .map( map_funciton )
                .subscribe(System.out::println)
    }

    @Test
    fun flatMap_(){
        val funciton_ = Function<String, Observable<String> > { test -> Observable.just("#1 $test", "#2 $test") }

        val arrlist = ArrayList<String>().apply {
            add("test1")
            add("test2")
            add("test3")
        }.asIterable()

//        Observable.just(" Test 1", " Test 2", " Test3")
//                .flatMap( funciton_ )
//                .subscribe(System.out::println)

        Observable.fromIterable(arrlist)
                .flatMap( funciton_ )
                .subscribe(System.out::println)
    }

    @Test
    fun rxTimes_table(){
        val Times_table_fun = Function<Int, Observable<String>> { num ->
            Observable.range(1,9).map { row -> "$num * $row = ${num * row}" } }

        Observable.just(3)
                .flatMap( Times_table_fun )
                .subscribe(System.out::println)
//        Times_table_fun.apply(3).subscribe(System.out::println)

    }

    @Test
    fun rxFilter(){
        val arr = ArrayList<String>(Arrays.asList("1 Circle", "2 Diamond", "3 Triangle", "4 Circle", "5 Diamond"))
        Observable.fromIterable(arr.asIterable()).filter { obj -> obj.endsWith("Circle") }
                .subscribe(System.out::println)
    }

    @Test
    fun rxReduce(){
        Observable.just("1", "2", "3", "4")
                .reduce { t1, t2 -> "${t2} + ${t1} " }
                .subscribe(System.out::println)
    }
}