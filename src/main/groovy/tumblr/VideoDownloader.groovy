package tumblr

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by liuyufei on 8/21/16.
 */
class VideoDownloader implements DownLoadType {

    def type = "video"

    @Override
    def download(resp) {
        def posts = resp.data.response.posts
        println "totally number = ${posts.size()}"
        posts.each { blog_post ->
            blog_post.player.each { embed ->
                if (embed.width == 500) {
                    Document doc = Jsoup.parse(embed.embed_code);
                    def link = doc.select("source").first().attr("src");
                    println "download ${link}"
                    def fileName = link.tokenize('/')[3]
                    println "ready to write new file ${fileName}"
//                    File file_tmp = new File("${fileName}.mp4")
//                    file_tmp.withOutputStream { out ->
//                        try {
//                            out << new URL(link).openStream()
//                        }catch (e){
//                            file_tmp.delete()
//                            println e.message
//
//                        }finally{
//                            out.close()
//                        }
//                    }
                }
            }
        }
    }
}
