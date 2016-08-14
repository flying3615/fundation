package tumblr

import groovyx.net.http.RESTClient

/**
 * Created by liuyufei on 8/14/16.
 */
def api_key="jk2DqzTEltU8SeAaoTNXN5d7K3wbt6XaGcGv6zEPFuywJ2CFVk"
def blog = "chaojixihuan.tumblr.com"
def type = "photo"
//def type = "video"
def path_posts_str = "v2/blog/${blog}/posts/${type}"
def forecastApi = new RESTClient('https://api.tumblr.com/')

def response = forecastApi.get(['path': path_posts_str,'query':['api_key':api_key,'limit':20]])

def void download_photo(resp) {
    resp.data.response.posts.each {blog_post->
        blog_post.photos.each{photo->
            def address = photo.alt_sizes[0].url
            def fileName = address.tokenize('/')[-1]
            println "ready to write new file ${fileName}"
            new File("${fileName}").withOutputStream { out ->
                out << new URL(address).openStream()
            }
        }
    }
}

def void download_video(resp) {
    resp.data.response.posts.each {blog_post->
        println blog_post.player
    }
}

download_photo(response)
//download_video(response)
