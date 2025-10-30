package mianshi.t1114;

import java.util.concurrent.CountDownLatch;

/**
 * leetcode 多线程算法：
 *
 * @see <a href="https://leetcode.cn/problems/print-in-order/description/">1114.按序打印</a>
 */
public class Check {


    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();

        foo.first(new Thread(()->{
            System.out.println("first");
        }));
        foo.second(new Thread(()->{
            System.out.println("second");
        }));
        foo.third(new Thread(()->{
            System.out.println("third");
        }));
    }
   static class Foo {
        CountDownLatch sec = new CountDownLatch(1);
        CountDownLatch trd = new CountDownLatch(1);

        public Foo() {

        }


        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            sec.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            sec.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            trd.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            trd.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
