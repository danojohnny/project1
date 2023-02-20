import com.github.javafaker.Faker;
import org.checkerframework.common.value.qual.StaticallyExecutable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;




public class Main {

    @Test
    public static void main(String[] args) throws InterruptedException {

        Faker faker = new Faker();
       WebDriver driver = new ChromeDriver();
       driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

       // Get title of the page
       String actualTitle = driver.getTitle();
       String expectedTitle = "Welcome to Duotify!";
        Assert.assertEquals(actualTitle, expectedTitle);

       // find signup button and click it
       driver.findElement(By.id("hideLogin")).click();

       String username = faker.name().username();
       String firstName = faker.address().firstName();
       String email = faker.internet().emailAddress();
       String password = faker.internet().password();
       //Fill the form
       //find login field
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("firstName")).sendKeys("Dano");
        driver.findElement(By.id("lastName")).sendKeys("John");
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("email2")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password2")).sendKeys(password);

        driver.findElement(By.name("registerButton")).click();

        // Check current URL
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://duotify.us-east-2.elasticbeanstalk.com/browse.php?");


        String name = driver.findElement(By.id("nameFirstAndLast")).getText();
        Assert.assertEquals(name, "Dano John");

        driver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);

        String name2 = driver.findElement(By.className("userInfo")).getText();
        Assert.assertEquals(name2, "Dano John");
        driver.findElement(By.id("rafael")).click();
        Thread.sleep(1000);

        String loggedoutURL = driver.getCurrentUrl();
        Assert.assertEquals(loggedoutURL, "http://duotify.us-east-2.elasticbeanstalk.com/register.php");


        //LOGIN
        driver.findElement(By.id("loginUsername")).sendKeys(username);
        driver.findElement(By.id("loginPassword")).sendKeys(password);

        driver.findElement(By.name("loginButton")).click();

        String pageSource =  driver.getPageSource();
        Thread.sleep(1000);

        String text = "You Might Also Like";
        //Assert.assertTrue(pageSource.contains(text));


        // LOGOUT Again
        driver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("rafael")).click();
        Thread.sleep(1000);









    }
}
