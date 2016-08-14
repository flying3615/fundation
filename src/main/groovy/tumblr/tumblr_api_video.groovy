package tumblr

/**
 * Created by liuyufei on 8/14/16.
 */


import groovyx.net.http.RESTClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by liuyufei on 8/14/16.
 */
def api_key = "jk2DqzTEltU8SeAaoTNXN5d7K3wbt6XaGcGv6zEPFuywJ2CFVk"
def blog = "omnmno.tumblr.com"
//def blog = "zxd2425.tumblr.com"
def type = "video"
def path_posts_str = "v2/blog/${blog}/posts/${type}"
def forecastApi = new RESTClient('https://api.tumblr.com/')

def response = forecastApi.get(['path': path_posts_str, 'query': ['api_key': api_key]])


def void download_video(resp) {
    def posts = resp.data.response.posts
    println "totally number = ${posts.size()}"
    posts.each { blog_post ->
        blog_post.player.each { embed ->
            if (embed.width == 500) {
                Document doc = Jsoup.parse(embed.embed_code);
                def link = doc.select("source").first().attr("src");
                println "download ${link}"
                def fileName = link.tokenize('/')[3]
                File file_tmp = new File("${fileName}.mp4")
                file_tmp.withOutputStream { out ->
                    try {
                        out << new URL(link).openStream()
                    }catch (e){
                        file_tmp.delete()
                        println e.message

                    }finally{
                        out.close()
                    }
                }
            }
        }
    }
}

download_video(response)
