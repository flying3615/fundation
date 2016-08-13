package enhance
import org.joda.time.DateTime
/**
 * Created by liuyufei on 8/13/16.
 */
class DateParser {

    def String parse(time) {
        //!null == true
        //!!null == false
        if (!time) throw new IllegalArgumentException()

        use(DateTimeCategory) {
            def printableTime = new DateTime(time)
            //with DateTimeCategory,
            // we can enhance DateTime Object with addition method!!!
            printableTime.createPrintableTime('suffix')
        }

    }

}
