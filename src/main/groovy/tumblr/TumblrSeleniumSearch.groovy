package tumblr
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver

import java.util.concurrent.TimeUnit

/**
 * Created by liuyufei on 8/14/16.
 */
class TumblrSeleniumSearch {

    static void main(args){
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.tumblr.com/search/cat");
        List<WebElement> title = driver.findElements(By.xpath("//div[@data-view-exists='true']"));
        Downloader downloader = new Downloader()
        def imageDownLoaderType = new ImageDownloader()
        def videoDownLoaderType = new VideoDownloader()
        title.each {WebElement it->
            def blog_url = it.getAttribute("data-tumblelog-url").substring(7)
            //trim "http://"
            println "find posts in blog ${blog_url}"
//            downloader.downLoadType = imageDownLoaderType
//            downloader.doDownload(blog_url)
            downloader.downLoadType = videoDownLoaderType
            downloader.doDownload(blog_url)
        }
        driver.quit();
    }
}
