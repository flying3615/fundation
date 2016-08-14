package tumblr
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver
/**
 * Created by liuyufei on 8/14/16.
 */
class TumblrSeleniumSearch {
    static void main(args){
        //only ff can work???
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.tumblr.com/search/奥运");
        //only get 4 elements at most?
        List<WebElement> title = driver.findElements(By.tagName("h3"));
        title.each {println it.getText()}
        driver.quit();
    }
}
