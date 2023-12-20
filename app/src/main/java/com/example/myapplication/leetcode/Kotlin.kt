package com.example.myapplication.leetcode

/**
 * @Author wuyuhang
 * @Date 2023/12/7 18:05
 * @Describe
 */
class Kotlin(number: Int) {

    var number: Int = number.also {
        println("number")
    };

    constructor(): this(1) {
        println("constructor 1 execute")
    }

    constructor(name: String): this() {
        println("constructor 2 execute")
    }

    init {
        println("init 1 execute")
    }

    init {
        println("init 2 execute")
    }


}