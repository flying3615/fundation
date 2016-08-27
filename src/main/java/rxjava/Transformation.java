package rxjava;

import rx.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuyufei on 8/26/16.
 */
public class Transformation {
    public static void main(String[] args) {
        List<String> inputs = Arrays.asList("aaa", "bbb", "ccc");
        System.out.println("--------map transformation-------");
        mapTran(inputs);
        System.out.println("--------scan transformation-------");
        scanTran(inputs);
        List<Integer> input_nums = Arrays.asList(1,2,3,4,5,6,7);
        System.out.println("--------groupBy transformation-------");
        groupByTran(input_nums);

    }

    //map is map
    private static void mapTran(List<String> inputs){
        Observable.from(inputs)
                .map(letterString -> letterString.toUpperCase())
                .subscribe(System.out::println);

        System.out.println("-------------------------");

        Observable.from(inputs).flatMap(letterString -> {
            String[] returnStrings = new String[]{letterString.toUpperCase(), letterString.toLowerCase()};
            return Observable.from(returnStrings);
        }).subscribe(System.out::println);
    }

    //like reduce
    private static void scanTran(List<String> inputs){
        Observable.from(inputs)
                .scan(new StringBuilder(),(accumBuffer,nextLetter)-> accumBuffer.append(nextLetter))
                .subscribe(System.out::println);

        System.out.println("-------------------------");

        Observable.from(inputs)
                .scan(new StringBuilder(),(accumBuffer,nextLetter)->accumBuffer.append(nextLetter))
                //only get the last one
                .last()
                .subscribe(System.out::println);
    }


    private static void groupByTran(List<Integer> inputs){

        List<Integer> evenList = new ArrayList<>();
        List<Integer> oddList = new ArrayList<>();

        Observable.from(inputs)
                .groupBy(i-> 0==(i%2)?"EVEN":"ODD")
                .subscribe(groupList->{
                    groupList.subscribe(x->{
                        if(groupList.getKey().equals("EVEN")){
                            evenList.add(x);
                        }else{
                            oddList.add(x);
                        }
                    });
                });
        System.out.println("Event list ---------------");
        evenList.forEach(System.out::print);
        System.out.println();
        System.out.println("Odd list ---------------");
        oddList.forEach(System.out::print);

    }


    private static void conditional(){
        Observable.empty().defaultIfEmpty("Hello World")
                .subscribe(System.out::println);
    }
}
