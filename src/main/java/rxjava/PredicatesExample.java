package rxjava;

import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyufei on 8/24/16.
 */
public class PredicatesExample {

    public static void main(String[] args) {
        System.out.println("filter example");
        Observable.from(generateList())
                .filter(i->i%3==0&&i<10)
                .subscribe(System.out::println);

        System.out.println("take example");
        Observable.from(generateList())
                .take(4)
                .subscribe(System.out::println);

        System.out.println("last example");
        Observable.from(generateList())
                .last()
                .subscribe(System.out::println);

        System.out.println("last 4 example");
        Observable.from(generateList())
                .takeLast(4)
                .subscribe(System.out::println);


        System.out.println("first default example");
        Observable.empty()
                .firstOrDefault("List is empty!")
                .subscribe(System.out::println);

        System.out.println("last default example");
        Observable.empty()
                .lastOrDefault("List is empty!")
                .subscribe(System.out::println);

    }



    private static List<Integer> generateList(){

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
        return integers;
    }
}
