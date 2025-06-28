package orangeHrm;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;



public class OrangeHRM extends Basesetup {
@Test(priority=1)
	public void InvalidloginTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.clear();
        usernameField.sendKeys("ram");

        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordField.clear();
        passwordField.sendKeys("ram123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='Invalid credentials']")));

        Assert.assertTrue(errorMsg.isDisplayed(), "Error message is not displayed.");
        Assert.assertEquals(errorMsg.getText(), "Invalid credentials", "Error message text does not match.");

        System.out.println("Invalid login correctly showed error: " + errorMsg.getText());
	}
        

  @Test(priority=2)
    public void loginTest() {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(User);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(Password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
        System.out.println("Login successful and Dashboard loaded.");
    
        
    }
    @Test(priority=3)
    public void addEmployee() {
    	    driver.findElement(By.xpath("//span[text()='PIM']")).click();
            driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
            driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Ram");
            driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Gupta"); 

            driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
            String confirmationMessage = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();

            if (confirmationMessage.contains("Personal Details")) {
                System.out.println("Employee added successfully!");
            } else {
                System.out.println("Failed to add employee!");
            }
       }
    
    @Test(priority=4)
    public void searchEmployeeByName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Go to PIM > Employee List
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Employee List']"))).click();

        // Enter employee name
        driver.findElements(By.tagName("input")).get(1).sendKeys("Ram");

        // Click Search
        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

        // Get search result message
        String message = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//span[@class='oxd-text oxd-text--span']")))
            .getText();

        System.out.println("Search result message: " + message);

        // Check if employee is found
        if (message.equalsIgnoreCase("No Records Found")) {
            System.out.println("Employee not found.");
            Assert.fail("No matching employee found.");
        } else {
            System.out.println("Employee found successfully.");
            Assert.assertTrue(message.contains("Record") || !message.isEmpty());
        }
    }


    
    @Test(priority = 5)
    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='oxd-userdropdown-name']"))).click();

    
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']"))).click();

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        System.out.println("Logged out successfully.");
    } 
  
}


