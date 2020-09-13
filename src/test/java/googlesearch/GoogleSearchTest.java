package googlesearch;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleSearchTest {

    ChromeDriver driver;
    Actions actions;
    String baseUrl = "https://www.google.com/";
    String searchPhrase = "Vladimir Putin";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver(new ChromeOptions().addArguments("--lang=en-GB"));
        actions = new Actions(driver);
    }

    @Test
    public void googleReturnsValidResults() throws InterruptedException {
        //Открываем гугл и находим поле для ввода поискового запроса.
        driver.get(baseUrl);
        //Для описания одного текстэдита и двух кнопок не имеет смысла создавать отедельный
        //пэйдж обджект, поэтому объявляем элементы прямо в тесте.
        WebElement TextEditSearch = driver.findElement(By.xpath(".//input[@title='Search']"));

        //Подсказка хранится в атрибуте Title. Достаем ее и проверяем.
        String toolTipText = TextEditSearch.getAttribute("title");
        Assert.assertEquals(toolTipText, "Search");

        //вводим поисковый запрос
        TextEditSearch.sendKeys(searchPhrase);

        //Ждем пока появится выпадающее окно саджесченов и закрываем его кнопкой ESC.
        Thread.sleep(50);
        actions.sendKeys(Keys.ESCAPE).perform();

        //Нажимаем кнопку поиска.
        driver.findElement(By.xpath(".//div[@class='FPdoLc tfB0Bf']/center/input[@value='Google Search']")).click();

        //Находим первый результат поиска и достаем из него текст.
        //Проверяем, что он содержит поисковую фразу.
        String text = driver.findElement(By.xpath("//div[@id='rso']/div[1]/*//a/h3")).getText();
        Assert.assertTrue(text.contains(searchPhrase));

        //Нажимаем на лого гугл в верхней части экрана.
        driver.findElement(By.id("logo")).click();
        //Проверяем что на стринце нет результатов поиска.
        Assert.assertTrue(driver.findElements(By.id("search")).isEmpty());
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}