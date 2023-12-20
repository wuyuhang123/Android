package com.example.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wuyuhang
 * @Date 2023/12/3 16:27
 * @Describe
 */
public class Generics{
    public static class Animal {
        public void fun() {
            System.out.println("Animal");
        }
    }

    public static class Cat extends Animal {
        public void fun() {
            System.out.println("Cat");
        }
    }

    public static class Dog extends Animal {
        public void fun() {
            System.out.println("Dog");
        }
    }

    public static void main(String[] args) {
        // 数组的情况，数组的话由于没有类型擦除且支持协变，所以在下面这个例子中，由于animal是dog的父类，
        // 所以animal数组是dog数组的父类，在每次往里面塞数据时就会进行校验
        Dog[] dogs = new Dog[3];
        Animal[] animals = dogs;
        animals[0] = new Dog();
//        这种写法是错误的，但是编译能过，因为编译器只知道这个数组是animal或者其子类的数组，
//        所以理论上这个引用可能引用到animal数组，但是运行时发现这个数组其实是一个dog数组，因此他不应该放入animal对象，
//        且数组在编译后没有进行类型擦除，所以在放入的时候就可以检测出并且抛出异常。
//        animals[1] = new Animal();
        animals[0].fun();

        List<? extends Animal> list = new ArrayList<>();
        List<Animal> animalList = new ArrayList();
        // 首先考虑extends的场景，当我拿到下面这个引用的时候，我是想干什么呢？这个引用保证这个list里面存的对象都是
        // Animal的子类型，那么这里面的对象都会有从Animal继承的方法和变量，我可以把他们当作Animal使用，
        List<? extends Animal> out = animalList;
        // 接下来考虑super的场景，我选择animal作为下界，也就是说这个list里面的所有对象都是Animal及其父类，
        // 那么这里的对象是不存在一个公共方法的，但是能够保证让我可以把Animal对象给塞进去
        List<? super Animal> in = animalList;
        in.add(new Cat());
        in.add(new Animal());
        for (Animal animal : out) {
            animal.fun();
        }
    }

    static class A {
        Object a = new Object();
        Object get() {
            return a;
        }
    }

    static class B extends A {
        @Override
        A get() {
            return this;
        }
    }

}
