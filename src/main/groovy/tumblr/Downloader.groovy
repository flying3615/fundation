package tumblr

import groovyx.net.http.RESTClient

/**
 * Created by liuyufei on 8/21/16.
 */
class Downloader {

    DownLoadType downLoadType;


    def api_key="jk2DqzTEltU8SeAaoTNXN5d7K3wbt6XaGcGv6zEPFuywJ2CFVk"
    def forecastApi = new RESTClient('https://api.tumblr.com/')


    def doDownload(title){
        def path_posts_str = "v2/blog/${title}/posts/${downLoadType.type}"
        def response = forecastApi.get(['path': path_posts_str,'query':['api_key':api_key,'limit':20]])
        downLoadType.download(response);
    }






}
