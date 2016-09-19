package GPS
import groovyx.net.http.RESTClient
import java.text.DateFormat
import java.text.SimpleDateFormat
/**
 * Created by liuyufei on 9/8/16.
 */

String apiKey = "AIzaSyDbYeJRB7JpJcKpZ-xJ0toun2h3xsy5N-A";
def baseUrl = 'https://maps.googleapis.com/maps/api/timezone/json'
def forecastApi = new RESTClient(baseUrl)

new File("./input.csv").splitEachLine(",") {fields ->
    def queryString = [location: "${fields[1]},${fields[2]}", timestamp: 0, key: apiKey]
    def response = forecastApi.get(query: queryString)
    String zoneID = response.data.timeZoneId
    DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = utcFormat.parse(fields[0]);
    DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    pstFormat.setTimeZone(TimeZone.getTimeZone(zoneID));
    println "${fields[0]},${fields[1]},${fields[2]},${zoneID},${pstFormat.format(date)}";
}


