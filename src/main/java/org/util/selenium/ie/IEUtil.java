package org.util.selenium.ie;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by chichen.cc on 2016/1/7.
 */
public class IEUtil {

    public static void main(String[] args) throws Exception {
        // Set path of IEDriverServer.exe.
        // Note : IEDriverServer.exe should be In D: drive.
        //   - See more at: http://software-testing-tutorials-automation.blogspot.kr/2015/07/h
        //  ow-to-run-selenium-webdriver-test.html#sthash.a1Dqr11v.dpuf
        System.setProperty("webdriver.ie.driver", "D:\\temp\\jvm\\IEDriverServer.exe");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

        // Initialize InternetExplorerDriver Instance.
        WebDriver driver = new InternetExplorerDriver(ieCapabilities);
        // Load sample calc test URL.
        driver.get("http://baidu.com");
        //http://haijia.bjxueche.net
        // Execute sample calc test.
//        driver.findElement(By.xpath("//input[@id='1']")).click();
//        driver.findElement(By.xpath("//input[@id='plus']")).click();
//        driver.findElement(By.xpath("//input[@id='6']")).click();
//        driver.findElement(By.xpath("//input[@id='equals']")).click();
//        String result = driver.findElement(By.xpath("//input[@id='Resultbox']")).getAttribute("value");
//        System.out.println("Calc test result Is : " + result);

        try {
            Thread.sleep(15000);
        }
        catch(InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        driver.close();
    }
}