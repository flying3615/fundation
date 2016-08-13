package GPS

import groovy.sql.Sql
import groovyx.net.http.RESTClient

/**
 * Created by liuyufei on 8/12/16.
 */
def file = new File('../data/St_Louis_Zoo_sample.gpx')
def slurper = new XmlSlurper()
def gpx = slurper.parse(file)
println "--get element value--"
println gpx.metadata.name
println gpx.metadata.desc
println "--access to attribute of element--"
println gpx.@version
println gpx.@creator

println ""

def credentialsFile = new File("credentials.groovy")
def configSlurper = new ConfigSlurper()
//read from external config file
def credentials = configSlurper.parse(credentialsFile.toURL())

def forecastApi = new RESTClient('https://api.forecast.io/')

Sql sql = Sql.newInstance("jdbc:mysql://localhost:3306/gps",
        credentials.database.user, credentials.database.password,
        "com.mysql.jdbc.Driver")



gpx.wpt.each {

    def queryString = "forecast/${credentials.apiKey}/${it.@lat},${it.@lon}"
    def response = forecastApi.get(path: queryString)

    println it.name
    println "${response.data.currently.summary}"
    println "${response.data.currently.temperature} degree"

    def routepoints = sql.dataSet("routepoints")
    routepoints.add(
            latitude:it.@lat.toDouble(),
            longitude:it.@lon.toDouble(),
            temperature:response.data.currently.temperature,
            summary:response.data.currently.summary
    )

}


sql.close()
