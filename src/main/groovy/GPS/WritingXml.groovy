package GPS

/**
 * Created by liuyufei on 8/12/16.
 */
def file = new File('../data/St_Louis_Zoo_sample.gpx')
def slurper = new XmlSlurper()
def gpx = slurper.parse(file)

def markupBuilder = new groovy.xml.StreamingMarkupBuilder()
def xml = markupBuilder.bind{
    //element
    route{
        //comments
        mkp.comment(gpx.metadata.name)
        gpx.wpt.each{ point->
            //attribute
            routepoint(name:point.name.toString()){
                latitude(point.@lat)
                longitude(point.@log)
            }
        }
    }
}

def outfile = new File('../data/truncated.xml')
outfile.write(xml.toString())


