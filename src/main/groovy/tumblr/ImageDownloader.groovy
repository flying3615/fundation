package tumblr

/**
 * Created by liuyufei on 8/21/16.
 */
class ImageDownloader implements DownLoadType{

    def type = "photo"

    @Override
    def download(resp) {
        resp.data.response.posts.each {blog_post->
            blog_post.photos.each{photo->
                def address = photo.alt_sizes[0].url
                def fileName = address.tokenize('/')[-1]
                println "ready to write new image ${fileName}"
                new File("${fileName}").withOutputStream { out ->
                    out << new URL(address).openStream()
                }
            }
        }
    }
}
