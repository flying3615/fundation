package rxjava;


import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * Created by liuyufei on 8/24/16.
 */
public class SimpleCreationExamples {

    public static void main(String[] args) throws InterruptedException {
        Object waitMonitor = new Object();


//        simpleCreation(waitMonitor);
//        futureCreation();
        parallel(waitMonitor);
//        waitMonitor.notify();


        synchronized (waitMonitor) {
            System.out.println("wait");
            //need to wait those sub thread finish
            waitMonitor.wait();
        }

    }

    private static void simpleCreation(Object lock) {


        Observable<Integer> observable = null;
        System.out.println("---------------------");
        System.out.println("Observable creation from a list value");
        System.out.println("---------------------");

        List<Integer> emitList = new ArrayList<>();
        emitList.add(1);
        emitList.add(2);
        emitList.add(3);
        emitList.add(4);
        emitList.add(5);
        observable = Observable.from(emitList);
        observable
                .subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.computation())
//                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        i -> {
                            System.out.println("onNext thread entr: " + Thread.currentThread().getName());
                            System.out.println(i);
                            System.out.println("onNext thread exit: " + Thread.currentThread().getName());

                        },
                        t -> {
                            t.printStackTrace();
                        },
                        () -> {
                            System.out.println("On Completed");
                            synchronized (lock) {
                                System.out.println("notify");
                                lock.notify();
                            }
                        });

    }

    private static void futureCreation() {
        System.out.println("---------------------");
        System.out.println("Observable creation from future task");
        System.out.println("---------------------");
        FutureTask<Integer[]> future = new FutureTask<>(() -> {
            Integer[] integers = {1, 2, 3, 4, 5};
            return integers;
        });


        Observable<Integer[]> observableFutureArray = Observable.from(future);

        //schedule this future to run on the computation scheduler
        Schedulers.computation().schedule(() -> {
            future.run();
        });

        observableFutureArray.subscribe((array) -> {
            for (int i : array) {
                System.out.println(i);
            }
        });
    }

    public static void parallel(Object waitMonitor) {


        System.out.println("----------------------------");
        System.out.println("Driving thread: " + Thread.currentThread().getName());
        System.out.println("----------------------------");

        List<Integer> emitList = new ArrayList<>();
        emitList.add(1);
        emitList.add(2);
        emitList.add(3);
        emitList.add(4);
        emitList.add(5);
        emitList.add(6);

        Observable<Integer> observable = Observable.from(emitList);

        observable
                .subscribeOn(Schedulers.newThread())
                .parallel((a) -> {
                            return
                                    a.filter((i) -> i % 2 == 0)
                                            .doOnNext((xx) -> {
                                                System.out.println("Parallel thread in " + Thread.currentThread().getName());
                                                System.out.println("Parallel " + xx);
                                                try {
                                                    Thread.sleep(2000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                System.out.println("Parallel thread out " + Thread.currentThread().getName());

                                            });
                        }
                        , Schedulers.io())
                .subscribe(
                        //onNext function
                        i -> {
                            System.out.println("onNext thread entr: " + Thread.currentThread().getName());
                            System.out.println(i);
                            System.out.println("onNext thread exit: " + Thread.currentThread().getName());
                        },
                        //onError function
                        t -> {
                            t.printStackTrace();
                        },
                        () -> {
                            System.out.println("onCompleted()");
                            synchronized (waitMonitor){
                                waitMonitor.notify();
                            }
                        });

    }
}
