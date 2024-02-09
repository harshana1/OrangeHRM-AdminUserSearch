package TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminUserSearch {

    //Global Variable Section
    String BaseURL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    WebDriver driver;
    String actualText;
    String expectedText;
    Boolean status;

    @BeforeTest
    public void BeforeTestMethod(){

        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    //Test Case Section

    //Test Case - Search by Username (TC: 007)
    @Test (priority = 1)
    public void searchByUsername() throws Exception {

        System.out.println("---------------TC 001---------------");

        //Login to the system
        userLogin();

        //Select Admin sub menu item
        adminClick();

        //send the username

        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")).sendKeys("Admin");

        //click the search button
        searchButtonClick();

        Thread.sleep(4000);
        //Verify if the filter result is correct
        expectedText="Admin";
        actualText = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[2]/div")).getText();

        //Print actual text
        System.out.println("Test Case 001 Actual Text: "+ actualText);

        if (expectedText.equals(actualText))
        {
            System.out.println("TC 001: PASS");
            System.out.println("Admin search for system user by username is done successfully");
            //System.out.println("TC 007: Screen Image Captured - Success");
            //this.takeSnapShot(driver, "C:\\Users\\Dell\\Desktop\\New folder\\SearchByUsernameSuccess.png");
        }
        else
        {
            System.out.println("TC 001: FAIL");
            //System.out.println("TC 007: Screen Image Captured - Fail");
            //this.takeSnapShot(driver, "C:\\Users\\Dell\\Desktop\\New folder\\SearchByUsernameError.png");

        }

        System.out.println("------------------------------------");

        //Click reset button
        resetButtonClick();

    }

    //Test Case - Search By Existing Username (TC: 008)
    @Test (priority = 2)
    public void searchByNonExistingUsername() throws Exception {

        resetButtonClick();

        System.out.println("---------------TC 002---------------");

        //send the username
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")).sendKeys("Chandana");

        //click the search button
        searchButtonClick();

        //Verify if the filter result is correct
        expectedText="No Records Found";
        actualText = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")).getText();
        if (expectedText.equals(actualText))
        {
            System.out.println("TC 002: PASS");
            System.out.println("Admin search for system non existing user by username is done successfully");
            //this.takeSnapShot(driver, "C:\\Users\\Dell\\Desktop\\New folder\\SearchByUsernameError.png");
        }
        else
        {
            System.out.println("TC 002: FAIL");
            //System.out.println("TC 008: Screen Image Captured");
            //this.takeSnapShot(driver, "C:\\Users\\Dell\\Desktop\\New folder\\SearchByNonExistingUsernameError.png");
        }
        System.out.println("------------------------------------");

        //Click reset button
        resetButtonClick();
    }

    //Search By UserRole (TC: 009)
    @Test (priority = 3)
    public void searchByUserRole() throws InterruptedException {
        System.out.println("---------------TC 003---------------");

        //User role drop down filtering
        userRoleDropDownFilterSelection();

        //click the search button
        searchButtonClick();

        //Verify if the filter result is correct
        expectedText="Admin";
        actualText = driver.findElement(By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[3]")).getText();
        if (expectedText.equals(actualText))
        {
            System.out.println("TC 003: PASS");
            System.out.println("Admin search for system user by user role is done successfully");
        }
        else
        {
            System.out.println("TC 003: FAIL");
        }
        System.out.println("------------------------------------");

        //Click reset button
        resetButtonClick();
    }

    //Search By Employee Name (TC: 010)
    @Test (priority = 4)
    public void searchByEmployeeName() throws InterruptedException {
        System.out.println("---------------TC 004---------------");

        //employee name filtering
        employeeNameEnter();

        //click the search button
        searchButtonClick();

        //Verify if the filter result is correct
        expectedText="Ranga Akunuri";
        actualText = driver.findElement(By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[4]")).getText();
        if (expectedText.equals(actualText))
        {
            System.out.println("TC 004: PASS");
            System.out.println("Admin search for system user by employee name is done successfully");
        }
        else
        {
            System.out.println("TC 004: FAIL");
        }
        System.out.println("------------------------------------");

        //Click reset button
        resetButtonClick();
    }

    //Search By Status (TC: 011)
    @Test (priority = 5)
    public void searchByStatus() throws InterruptedException {
        System.out.println("---------------TC 005---------------");

        //select status
        statusDropDownFilterSelection();

        //click the search button
        searchButtonClick();

        //Verify if the filter result is correct
        expectedText="Enabled";
        actualText = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div/div[5]/div")).getText();
        if (expectedText.equals(actualText))
        {
            System.out.println("TC 005: PASS");
            System.out.println("Admin search for system user by status is done successfully");
        }
        else
        {
            System.out.println("TC 005: FAIL");
        }
        System.out.println("------------------------------------");

        //Click reset button
        resetButtonClick();
    }

    //Reset Search Filter And Results (TC: 012)
    @Test (priority = 6)
    public void resetSearchFilterAndResults() throws InterruptedException {
        System.out.println("---------------TC 006---------------");

        //send the username
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")).sendKeys("Ranga Akunuri");

        //user role filtering
        userRoleDropDownFilterSelection();

        //employee name filtering
        employeeNameEnter();

        //status filtering
        statusDropDownFilterSelection();

        //click the search button
        searchButtonClick();

        //Click reset button
        resetButtonClick();

        //Verify if the reset result is correct
        expectedText="-- Select --";
        actualText = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]")).getText();
        if (expectedText.equals(actualText))
        {
            System.out.println("TC 006: PASS");
            System.out.println("Reset button click to reset search filtering and filtered results is done successfully");
        }
        else
        {
            System.out.println("TC 006: FAIL");
        }
        System.out.println("------------------------------------");
    }



    @AfterTest
    public void AfterTestMethod(){

    }


    //Supportive Method Section
    public void userLogin() throws InterruptedException {

        //Calling the OrangeHRM Login Page
        driver.get(BaseURL);
        Thread.sleep(3000);

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("InvalidPassword");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();

    Thread.sleep(3000);

        //Identify the Username Text Box and Send Value
        driver.findElement(By.name("username")).sendKeys("Admin");
        //Identify the Password Text Box and Send Value
        driver.findElement(By.name("password")).sendKeys("admin123");
        //Identify the Login Button and Click
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();

        Thread.sleep(3000);

    }
    public void adminClick() throws InterruptedException {

        //click Admin menu item
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span")).click();

        //Wait for 3 seconds till the page loads
        Thread.sleep(3000);

        //Verify if the Admin menu is clicked successfully
        expectedText="System Users";
        actualText = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[1]/h5")).getText();

        if (expectedText.equals(actualText))
        {
            System.out.println("User clicked on Admin sub-menu");
        }
        else
        {
            System.out.println("Unsuccessful click on Admin sub-menu");
        }

    }
    public void resetButtonClick() throws InterruptedException {

        //click the reset button
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]")).click();

        //Wait for 5 seconds till the page loads
        Thread.sleep(5000);
    }
    public void userRoleDropDownFilterSelection() throws InterruptedException {
        //click the drop down arrow under User Role
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i")).click();

        //Wait for 1 second till the drop down loads
        Thread.sleep(1000);

        //select the Admin option
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div[2]")).click();

        //Wait for 1 second till Admin option is selected
        Thread.sleep(1000);
    }
    public void searchButtonClick() throws InterruptedException {

        //click the search button
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")).click();

        //Wait for 5 seconds till the page loads
        Thread.sleep(5000);
    }
    public void employeeNameEnter() throws InterruptedException {
        //enter employee name
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/input")).sendKeys("Ranga A");

        //Wait for 2 seconds till the drop down loads
        Thread.sleep(2000);

        //select the name suggestion
        driver.findElement(By.xpath("   //*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div[2]/div")).click();
        //Wait for 2 seconds till suggested name is selected
        Thread.sleep(2000);
    }
    public void statusDropDownFilterSelection() throws InterruptedException {
        //click the drop down arrow under Status
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[2]/i")).click();

        //Wait for 1 second till the dropdown loads
        Thread.sleep(1000);

        //select the Enabled option
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div[2]/div[2]")).click();

        //Wait for 1 second till enabled option is selected
        Thread.sleep(1000);
    }

}

