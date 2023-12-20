package com.example.myapplication.leetcode;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wuyuhang
 * @Date 2023/12/18 14:13
 * @Describe
 */
public class DesignPattern {

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        DesignPattern.getInstance1().testClone();
    }

    private DesignPattern() {

    }

    /**
     * 单例模式,两种懒加载的实现。第一种注意变量要设置为private以及要设置一个默认的私有构造函数
     * 第二种是利用了类的加载机制来保证多线程下不会重复实例化,该Instance在静态内部类第一次被用到时会初始化
     * 先static再final
     */
    private static volatile DesignPattern INSTANCE = null;
    public int a;

    public static DesignPattern getInstance1() {
        if (INSTANCE == null) {
            synchronized (DesignPattern.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DesignPattern();
                }
            }
        }
        return INSTANCE;
    }

    public static DesignPattern getInstance2() {
        return DesignPatternSingleton.INSTANCE;
    }

    public static class DesignPatternSingleton {
        public static final DesignPattern INSTANCE = new DesignPattern();
    }

    /**
     * 生产者消费者模式
     * 下面给出了三种实现，分别是wait/notify，condition，Java内置的阻塞队列
     */

    /**
     * 生产消费者模式的第一种实现
     */
    public final LinkedList<String> list = new LinkedList<>();

    public static class Provider extends Thread {

        private final LinkedList<String> list;

        public Provider(LinkedList<String> list, String name) {
            super(name);
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                //注意sleep和wait都需要try catch，且异常都是同一个
                try {
                    int random = new Random().nextInt(2000);
                    sleep(random);
                    synchronized (list) {
                        while (list.size() == 3) {
                            //注意这里要用list.wait而不是直接wait
                            list.wait();
                        }
                        list.addLast("p");
                        System.out.println(Thread.currentThread() + "provide");
                        list.notify();
                    }
                } catch (InterruptedException e) {

                }
            }
        }
    }

    public static class Consumer extends Thread {

        private final LinkedList<String> list;

        public Consumer(LinkedList<String> list, String name) {
            super(name);
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int random = new Random().nextInt(2000);
                    sleep(random);
                    synchronized (list) {
                        while (list.size() == 0) {
                            list.wait();
                        }
                        list.removeFirst();
                        System.out.println(Thread.currentThread() + "remove");
                        list.notifyAll();
                    }
                } catch (InterruptedException e) {

                }
            }
        }
    }


    public void testProviderAndConsumer1() {
        LinkedList<String> list = new LinkedList<>();
        Provider provider1 = new Provider(list, "provider1");
        Provider provider2 = new Provider(list, "provider2");
        Provider provider3 = new Provider(list, "provider3");
        Consumer consumer1 = new Consumer(list, "consumer1");
        Consumer consumer2 = new Consumer(list, "consumer2");
        Consumer consumer3 = new Consumer(list, "consumer3");
        provider1.start();
        provider2.start();
        provider3.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }

    /**
     * 生产者消费者模式的第二种实现
     */

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private LinkedList<String> list2 = new LinkedList<>();

    public static class Producer2 extends Thread {
        private final LinkedList<String> list;
        private final Lock lock;
        private Condition notEmpty;
        private Condition notFull;

        public Producer2(String name, LinkedList<String> list, Lock lock, Condition notEmpty, Condition notFull) {
            super(name);
            this.list = list;
            this.lock = lock;
            this.notFull = notFull;
            this.notEmpty = notEmpty;
        }

        @Override
        public void run() {
            while (true) {
                int time = new Random().nextInt(2000);
                try {
                    sleep(time);
                    lock.lock();
                    while (list.size() == 5) {
                        notFull.await();
                    }
                    list.addLast("producer");
                    System.out.println(Thread.currentThread() + "producer, size: " + list.size());
                    notEmpty.signal();
                    lock.unlock();
                } catch (InterruptedException e) {

                }
            }
        }
    }

    public static class Consumer2 extends Thread {
        private final LinkedList<String> list;
        private final Lock lock;
        private Condition notEmpty;
        private Condition notFull;

        public Consumer2(String name, LinkedList<String> list, Lock lock, Condition notEmpty, Condition notFull) {
            super(name);
            this.list = list;
            this.lock = lock;
            this.notEmpty = notEmpty;
            this.notFull = notFull;
        }

        @Override
        public void run() {
            while (true) {
                int time = new Random().nextInt(2000);
                try {
                    sleep(time);
                    lock.lock();
                    while (list.size() == 0) {
                        notEmpty.await();
                    }
                    list.removeFirst();
                    System.out.println(Thread.currentThread() + "consumer, size: " + list.size());
                    notFull.signal();
                    lock.unlock();

                } catch (InterruptedException e) {

                }

            }
        }
    }

    public void testProviderAndConsumer2() {
        Producer2 producer21 = new Producer2("producer1", list2, lock, notEmpty, notFull);
        Producer2 producer22 = new Producer2("producer2", list2, lock, notEmpty, notFull);
        Producer2 producer23 = new Producer2("producer3", list2, lock, notEmpty, notFull);
        Consumer2 consumer21 = new Consumer2("consumer1", list2, lock, notEmpty, notFull);
        Consumer2 consumer22 = new Consumer2("consumer2", list2, lock, notEmpty, notFull);
        Consumer2 consumer23 = new Consumer2("consumer3", list2, lock, notEmpty, notFull);
        producer21.start();
        producer22.start();
        producer23.start();
        consumer21.start();
        consumer22.start();
        consumer23.start();
    }

    /**
     * 生产者消费者模式的第三种实现，阻塞队列内部实现也是同第二种实现一样
     * 注意阻塞队列不能使用LinkedBlockingQueen，他的add和remove方法会抛出异常
     * 要使用linkedBlockingQueue且需要使用 take 和 put 方法
     */
    private LinkedBlockingDeque<String> linkedBlockingQueue = new LinkedBlockingDeque<>(3);

    public static class Producer3 extends Thread {
        private LinkedBlockingDeque<String> list;

        public Producer3(LinkedBlockingDeque<String> list, String name) {
            super(name);
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                int time = new Random().nextInt(2000);
                try {
                    Thread.sleep(time);
                    list.put("producer");
                    System.out.println(Thread.currentThread() + "producer, list size: " + list.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static class Consumer3 extends Thread {
        private LinkedBlockingDeque<String> list;

        public Consumer3(LinkedBlockingDeque<String> list, String name) {
            super(name);
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                int time = new Random().nextInt(2000);
                try {
                    Thread.sleep(time);
                    list.take();
                    System.out.println(Thread.currentThread() + "consumer, list size: " + list.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void testProviderAndConsumer3() {
        Producer3 producer31 = new Producer3(linkedBlockingQueue, "producer1");
        Producer3 producer32 = new Producer3(linkedBlockingQueue, "producer2");
        Producer3 producer33 = new Producer3(linkedBlockingQueue, "producer3");
        Consumer3 consumer31 = new Consumer3(linkedBlockingQueue, "consumer1");
        Consumer3 consumer32 = new Consumer3(linkedBlockingQueue, "consumer2");
        Consumer3 consumer33 = new Consumer3(linkedBlockingQueue, "consumer3");
        producer31.start();
        producer32.start();
        producer33.start();
        consumer31.start();
        consumer32.start();
        consumer33.start();
    }


    /**
     * 原型模式，clone，在Java中使用clone方法需要实现cloneable接口，
     * 否则调用clone方法会抛出异常CloneNotSupportedException
     */

    public static class Clone implements Cloneable{
        public CloneA cloneA;
        public CloneB cloneB;
        public Clone(CloneA cloneA, CloneB cloneB) {
            this.cloneA = cloneA;
            this.cloneB = cloneB;
        }

        @Override
        protected Clone clone() throws CloneNotSupportedException {
//            return (Clone) super.clone();
            // 注意，clone接口默认不会clone非基本类型，只会clone一份指针指向同一块内存区域，
            // 因此在clone时需要递归使用clone方法clone成员变量，即使成员变量实现了cloneable接口。
            // 或者直接在clone方法中手动new一个新的成员变量对象，然后赋值给clone对象来实现深拷贝
            Clone clone = (Clone) super.clone();
            clone.cloneA = this.cloneA.clone();
            clone.cloneB = this.cloneB.clone();
            return clone;
        }
    }

    public static class CloneA implements Cloneable {

        public String string;

        public CloneA(String s) {
            this.string = s;
        }

        @Override
        protected CloneA clone() throws CloneNotSupportedException {
            return (CloneA) super.clone();
        }
    }

    public static class CloneB implements Cloneable {

        public String string;

        public CloneB(String s) {
            this.string = s;
        }

        @Override
        protected CloneB clone() throws CloneNotSupportedException {
            return (CloneB) super.clone();
        }
    }

    public void testClone() {
        CloneA cloneA = new CloneA("cloneA");
        CloneB cloneB = new CloneB("cloneB");
        Clone clone = new Clone(cloneA, cloneB);
        // 记得try-catch
        try {
            Clone clone1 = clone.clone();
            System.out.println("clone = " + clone.hashCode() + " clone1 = " + clone1.hashCode());
            System.out.println("cloneA = " + clone.cloneA.hashCode() + " clone1A = " + clone1.cloneA.hashCode());
            System.out.println("cloneB = " + clone.cloneB.hashCode() + " clone1B = " + clone1.cloneB.hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

}
