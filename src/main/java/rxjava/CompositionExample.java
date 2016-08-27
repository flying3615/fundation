package rxjava;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyufei on 8/24/16.
 */
public class CompositionExample {

    public static void main(String[] args) throws InterruptedException {
        Object waitMonitor = new Object();
        synchronized (waitMonitor) {
            List<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            integers.add(5);
            integers.add(6);
            integers.add(7);
            integers.add(8);
            integers.add(9);
            integers.add(10);
            integers.add(11);
            integers.add(12);

            Observable
                    .from(integers)
                    .parallel(integerObservable -> integerObservable
                            .filter(a -> a % 2 == 0))
                    .toSortedList((a, b) -> a - b)
                    .subscribeOn(Schedulers.io())
                    .doOnCompleted(() -> {
                        synchronized (waitMonitor) {
                            waitMonitor.notify();
                        }
                    })
                    .subscribe(
                        resultList -> {
                        resultList.forEach(System.out::println);
                    });
            waitMonitor.wait();
        }
    }

}
