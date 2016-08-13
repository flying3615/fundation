package callJava

/**
 * Created by liuyufei on 8/12/16.
 */
// we have gradle, don't need grapes
//if in terminal, we can run it as a script

///jar will be downloaded in Users/liuyufei/.groovy/grapes
//'grape list' to list grapes jar
//@Grapes(
//        @Grab(group = 'joda-time',module='joda-time',version='2.3')
//)

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

def printableTime = new DateTime("2001-06-02T03:27:07Z")
def format = DateTimeFormat.forPattern("MM/dd/yyyy - hh:mm aa")
println printableTime.toString(format)