package com.example.yeongpyo.rxjavastudy;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class ReactiveXTest_Chat3_java {

    @Test
    public void Chater3Test(){
        Function<String, String> testfunction = test -> test + "TEST";
        String[] ball = {"1", "2", "3"};
        Observable<String> source = Observable.fromArray(ball)
                .map(testfunction);
        source.subscribe(System.out::println);
    }
}
