package enhance

import org.joda.time.format.DateTimeFormat

/**
 * Created by liuyufei on 8/13/16.
 */
class DateTimeCategory {

    def static String createPrintableTime(dateTime,suffix){
        def format = DateTimeFormat.forPattern('MM/dd/yyyy - hh:mm aa')
        dateTime.toString(format)+suffix
    }
}
