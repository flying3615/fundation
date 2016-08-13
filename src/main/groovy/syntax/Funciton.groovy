package syntax
/**
 * Created by liuyufei on 8/11/16.
 */

def number = 0..9

def isEven(num){
    num%2 == 0
}


number.each {println "$it is "+ (isEven(it)?"even":"not even")}
//number.each {println "$it is "}
