package com.zooplus.jacekb.learningTime.futures;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.zooplus.jacekb.learningTime.akka.common.PiCalculator;
import scala.math.BigDecimal;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 03/02/14
 * Time: 15:29
 */
public class GuavaFutures {
    public static void main(String[] args) {
        try {
            ListeningScheduledExecutorService service = MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(4));
//        val f: Future[(BigDecimal, Long)] = future {
//            val piCalculator = new PiCalculator()
//            val start = new Date().getTime
//            val π = piCalculator.calculatePiFor(0, 500000000)
//            val end = new Date().getTime
//                    (π, end - start)
//        }
            final ListenableFuture<Tuple<BigDecimal, Long>> f = service.submit(new Callable<Tuple<BigDecimal, Long>>() {
                @Override
                public Tuple<BigDecimal, Long> call() throws Exception {
                    PiCalculator piCalculator = new PiCalculator();
                    Long start = new Date().getTime();
                    BigDecimal π = piCalculator.calculatePiFor(0, 500000000);
                    Long end = new Date().getTime();
                    Tuple<BigDecimal, Long> tuple = new Tuple<BigDecimal, Long>();
                    tuple.setA(π);
                    tuple.setB(end - start);
                    return tuple;
                }
            });
//        println("Calculation of π fired up")
            System.out.println("Calculation of π fired up");
//        f onComplete showResult
//            def showResult(x: Try[(BigDecimal, Long)]): Unit = {
//                    x match {
//            case Success(s) => println(s"Calculated π: ${s._1}, time: ${s._2}")
//            case Failure(t) => println(s"Operation failed, exception message: ${t.getMessage}")
//                }
//              }
            f.addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        //println(s"Calculated π: ${s._1}, time: ${s._2}")
                        Tuple<BigDecimal, Long> result = f.get();
                        System.out.println("Calculated π: " + result.a + ", time: " + result.b);
                    } catch (Exception e) {
                        // println(s"Operation failed, exception message: ${t.getMessage}")
                        System.out.println("Operation failed, exception message: " + e.getMessage());
                    }
                }
            }, service);
            System.out.println("Listener added");
//        Await.ready(f, Duration.Inf)
            f.get();
            System.out.println("Result calculated");
            Thread.sleep(1000);
            System.out.println("Slept, exiting");
        } catch (Exception e) {
            System.out.println("Outer operations failed, exception message: " + e.getMessage());
        }
    }

    private static class Tuple<A, B> {
        private A a;

        private B b;

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }
}
