import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class WebDriverTests {

    private final String driverPath = "D:\\ChromeDriver\\Chrome_89\\chromedriver.exe";
    private final String url = "http://www.hotwire.com/";

    @BeforeClass
    public void prepareChromeDriver() {
        System.setProperty("webdriver.chrome.driver", driverPath);
    }


    @Test(groups = {"WebDriverTests"})
    public void getFlightsSearchResults() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        chromeOptions.addArguments("start-maximized");
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        webDriver.get(url);
        Thread.sleep(3000);
        webDriver.findElement(By.cssSelector(".farefinder-option[data-bdd='farefinder-option-bundles']")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.cssSelector(".margin-top-4 .hw-btn[data-bdd='farefinder-package-bundleoption-car']")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.cssSelector(".margin-top-6 [placeholder='Enter address, city or airport']")).sendKeys("SFO");
        Thread.sleep(1000);
        webDriver.findElement(By.cssSelector(".margin-top-4 [placeholder='Enter address, city or airport']")).sendKeys("LAX");
        Thread.sleep(1000);
        webDriver.findElement(By.cssSelector(".margin-top-6 [placeholder='Enter address, city or airport']")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.cssSelector(".DateRangePicker__form-group--margined [data-bdd='farefinder-package-startdate-input']")).click();
        Thread.sleep(2000);

        // get tomorrow's date and 20 days from then(departure date and return date)
        LocalDate departureDate = LocalDate.now().plusDays(1);
        LocalDate returnDate = departureDate.plusDays(20);
        String departureMonth = departureDate.getMonth().toString().charAt(0) + departureDate.getMonth().toString().substring(1).toLowerCase();
        String returnMonth = returnDate.getMonth().toString().charAt(0) + returnDate.getMonth().toString().substring(1).toLowerCase();
        String ariaLabelDeparture = departureMonth + " " + departureDate.getYear();
        String ariaLabelReturn = returnMonth + " " + returnDate.getYear();

        List<WebElement> days = webDriver.findElements(By.cssSelector(String.format("[aria-label='%s'] .day.clickable", ariaLabelDeparture)));
        for (WebElement day : days) {
            if(day.getText().equals(String.valueOf(departureDate.getDayOfMonth()))) {
                day.click();
                break;
            }
        }

        days = webDriver.findElements(By.cssSelector(String.format("[aria-label='%s'] .day.clickable", ariaLabelReturn)));
        for (WebElement day : days) {
            if (day.getText().equals(String.valueOf(returnDate.getDayOfMonth()))) {
                day.click();
                break;
            }
        }

        // select evening departure and morning return
        Select departureSelect = new Select(webDriver.findElement(By.cssSelector(".hw-input-group [data-bdd='farefinder-package-pickuptime-input']")));
        Select returnSelect = new Select(webDriver.findElement(By.cssSelector(".hw-input-group [data-bdd='farefinder-package-dropofftime-input']")));
        departureSelect.selectByVisibleText("Evening");
        returnSelect.selectByVisibleText("Morning");
        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector(".submit-button [data-bdd='farefinder-package-search-button']")).submit();
        new FluentWait<>(webDriver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(10));
        webDriver.quit();
    }
}
