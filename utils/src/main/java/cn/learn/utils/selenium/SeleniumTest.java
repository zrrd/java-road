package cn.learn.utils.selenium;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @author shaoyijiong
 * @date 2020/11/25
 */
public class SeleniumTest {


  public static void main(String[] args) {
    WebDriver webDriver = null;
    try {
      String url = "http://tg.668wan.com/lhcsbth5/111/?uid=hklhcsh5_132";
      //启动一个 chrome 实例
      webDriver = new ChromeDriver();
      //访问网址
      webDriver.get(url);
      Thread.sleep(5000);
      // 做页面操作
      Actions actions = new Actions(webDriver);
      // 选取节点
      WebElement myElement = webDriver.findElement(By.tagName("body"));
      // 移动到所选节点
      actions.moveToElement(myElement).perform();
      //Thread.sleep(10000);
      // 点击操作
      //actions.click().perform();
      System.out.println("保存网页");
      Thread.sleep(5000);
      // ctrl + s java awt的api
      Robot robot = new Robot();
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_S);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_S);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      Thread.sleep(2000);
      // 调用windows 原生操作 调用脚本
      Runtime.getRuntime().exec("D:\\tmp\\a.exe");
      Thread.sleep(10000000);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      webDriver.close();
    }
  }
}

