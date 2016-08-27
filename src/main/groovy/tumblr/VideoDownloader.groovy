package tumblr
import org.jsoup.Jsoup
import rx.Observable
import rx.schedulers.Schedulers
/**
 * Created by liuyufei on 8/21/16.
 */
class VideoDownloader implements DownLoadType {

    def type = "video"

    @Override
    def download(resp) {
        List<TreeMap> posts = resp.data.response.posts
        println "totally number = ${posts.size()}"
        Observable<TreeMap> observablePosts = Observable.from(posts)

        def toURL = { post ->
            return Observable.from(post)
                    .subscribeOn(Schedulers.io())
                    .map({ it -> it.player[2] })
                    .filter({ it -> it.width == 500 })
                    .map({ it -> Jsoup.parse(it.embed_code) })
                    .filter({ it -> it.select("source").first() != null })
        }


        observablePosts.flatMap(toURL).subscribe(
                { doc ->
                    def link = doc.select("source").first().attr("src");
                    println "download ${link} @ ${Thread.currentThread().getName()}"
                    writeToFile(link)
                },
                { e -> println e.printStackTrace() },
                { println "complete" }
        )

    }

    def writeToFile(link) {
        def fileName = link.tokenize('/')[3]
        println "ready to write new file ${fileName}"
        File file_tmp = new File("${fileName}.mp4")
        file_tmp.withOutputStream { out ->
            try {
                out << new URL(link).openStream()
            } catch (e) {
                file_tmp.delete()
                println e.message

            } finally {
                out.close()
            }
        }
    }
}
