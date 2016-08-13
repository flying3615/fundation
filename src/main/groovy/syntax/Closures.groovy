package syntax
/**
 *
 * Created by liuyufei on 8/11/16.
 */
//find the even value
(1..10).findAll{it%2==0}.each{println "in a closure: $it"}
