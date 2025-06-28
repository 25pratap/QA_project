package orangeHrm;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Basesetup {
	 public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	 	String User="Admin";
	 	String Password="admin123";
	    public WebDriver driver;
	    public WebDriverWait wait;

	    @BeforeTest
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get(baseUrl);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	    }
	    
	    @AfterTest
	    public void tearDown() throws InterruptedException {
	    	Thread.sleep(5000);  
	        driver.close();
	        driver.quit();
	    }
}
