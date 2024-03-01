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


    /**
     * kotlin静态内部类
     */
    class KotlinStaticInner {

        fun kotlinInnerTest() {
            println("kotlin static inner test")
        }

        fun kotlinInnerTest2(): Int {
            return 2;
        }

    }

    /**
     * kotlin非静态内部类
     */
    inner class KotlinInner {

        fun kotlinInnerTest() {
            println("kotlin inner test")
        }

        fun kotlinInnerTest2(): Int {
            return 3;
        }

    }

}