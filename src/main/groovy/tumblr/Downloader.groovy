package tumblr

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import rx.Observable
import rx.schedulers.Schedulers

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by liuyufei on 8/21/16.
 */
class Downloader {

    DownLoadType downLoadType;
    Executor executor = Executors.newFixedThreadPool(10);


    def api_key = "jk2DqzTEltU8SeAaoTNXN5d7K3wbt6XaGcGv6zEPFuywJ2CFVk"
    def forecastApi = new RESTClient('https://api.tumblr.com/')


    def doDownload(title) {
        def path_posts_str = "v2/blog/${title}/posts/${downLoadType.type}"
        println "ready to subscribe on ${downLoadType.type}"
        getResp(path_posts_str)
                .doOnNext({ println "emit ${it} at ${Thread.currentThread().getName()}" })
                .observeOn(Schedulers.io())
                .subscribe({
                    println "receive ${it} at ${Thread.currentThread().getName()}";
                    downLoadType.download(it)
                })
    }

    def Observable<HttpResponseDecorator> getResp(path_api) {
        return Observable.create({ observer ->
//            executor.execute({
//                def response = forecastApi.get(['path': path_api, 'query': ['api_key': api_key, 'limit': 20]])
            def response = forecastApi.get(['path': path_api, 'query': ['api_key': api_key, 'limit': 20]])
            observer.onNext(response)
            observer.onCompleted()
//            })
        })
    }


}
