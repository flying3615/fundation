package GPS

import groovy.sql.Sql

/**
 * Created by liuyufei on 8/13/16.
 */

Sql sql = Sql.newInstance("jdbc:mysql://localhost:3306/gps",
        'root', 'root',
        "com.mysql.jdbc.Driver")

//sql.eachRow('select * from routepoints'){
//    println "Latitue: ${it.latitude}, Longitude: ${it.longitude}," +
//            "summary: ${it.summary}, Temperature: ${it.temperature}"
//}

sql.execute("select * from routepoints"){a,b->
    println a
    println b
}



sql.close()