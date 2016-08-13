package syntax
/**
 * Created by liuyufei on 8/11/16.
 */
def range = 'a'..'g'

for( letter in range){
    println letter
}


def enum DAYS {
    SUN,
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT
}

def weekdays = DAYS.MON..DAYS.FRI
weekdays.each {println it}
println "weekdays from & to"
println weekdays.from
println weekdays.to
