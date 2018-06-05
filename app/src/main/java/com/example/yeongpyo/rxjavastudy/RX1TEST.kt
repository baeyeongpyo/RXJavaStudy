package com.example.yeongpyo.rxjavastudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.Observable.interval
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import kotlinx.android.synthetic.main.rx1test.*
import java.util.concurrent.TimeUnit

class RX1TEST : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rx1test)
        var sw = true
        button.setOnClickListener {
            if (sw) subject_()
            else subject.dispose()
            sw = !sw
        }
    }

    lateinit var subject : Disposable
    fun subject_(){
        subject = interval(1L, TimeUnit.SECONDS)
                .map { it + 1 }
                .map{it.toString()}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rx1TEST::setText)
    }


}
