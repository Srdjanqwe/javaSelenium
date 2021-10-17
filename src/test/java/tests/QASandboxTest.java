package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class QASandboxTest extends BaseTest
{
    @Test
    public void QASandboxLoginPage() throws InterruptedException {
        String username = "srdjan.rados90@gmail.com";
        String password = "Qwertysha1@";

        QASandboxLoginPage loginPage = new QASandboxLoginPage(driver);
        loginPage.login(username,password);

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@href='/dashboard']")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@href='/dashboard']")));
        WebElement logOutButton = driver.findElement(By.xpath("//a[@href='/dashboard']"));
//        System.out.println("Logout:"+logOutButton.getText());

        Assert.assertTrue("There is no button", logOutButton.getText().contains("Logout"));

        // Visualisation accpetance
        Thread.sleep(3000);
    }

    @Test
    public void QASandboxPageWrong() throws InterruptedException {
        String username = "srdjan.rados9@gmail.com";
        String password = "Qwertysha1@";

        QASandboxLoginPage loginPage = new QASandboxLoginPage(driver);
        loginPage.login(username,password);

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[@id='validation-msg']")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[@id='validation-msg']")));

        WebElement validationErrorMess = driver.findElement(By.xpath("//label[@id='validation-msg']"));
//        System.out.println("error message:"+" "+validationErrorMess.getText());

        Assert.assertTrue("Wrong validation error message",validationErrorMess.getText().contains(username+" "+"is not authorized or wrong email/password combination"));

        // Visualisation accpetance
        Thread.sleep(3000);
    }

    @Test
    public void QASandboxEditAllTitlesTC() throws InterruptedException
    {
        String username = "srdjan.rados90@gmail.com";
        String password = "Qwertysha1@";


        QASandboxEditTitlePage loginPage = new QASandboxEditTitlePage(driver);
        loginPage.login(username,password);

        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("navigate-left-title"),"Test Cases"));
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("main-grid")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("main-grid")));
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("portrait-grid")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("portrait-grid")));

        WebElement testCases = driver.findElement(By.className("portrait-grid"));
        List<WebElement> previewCard = testCases.findElements(By.xpath("//div[@class='preview-card']"));

        for (int i=0; i<=loginPage.addedTitles.size()-1; i++)
        {
            WebElement tcTitleName = previewCard.get(i);
            String cartTcTitleName = tcTitleName.findElement(By.className("preview-card-title-value")).getText();
            System.out.println("List titles:"+cartTcTitleName);
            assertEquals("Different TC names", loginPage.addedTitles.get(i).name, cartTcTitleName);
        }

        // Visualisation accpetance
        Thread.sleep(8000);

    }

    @Test
    public void QASandboxEditDetailsTC() throws InterruptedException
    {
        String username = "srdjan.rados90@gmail.com";
        String password = "Qwertysha1@";

        QASandboxEditDetails loginPage = new QASandboxEditDetails(driver);
        loginPage.login(username,password);

        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("navigate-left-title"),"Test Cases"));
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("main-grid")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("main-grid")));
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("portrait-grid")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("portrait-grid")));

        WebElement testCases = driver.findElement(By.className("portrait-grid"));
        List<WebElement> previewCard = testCases.findElements(By.xpath("//div[@class='preview-card']"));

        for (int i=0; i<=loginPage.addedTitles.size()-1; i++)
        {
            WebElement tcDetails = previewCard.get(i);
            String cartTcTitleName = tcDetails.findElement(By.className("preview-card-body--items-single-value")).getText();
            System.out.println("List details:"+cartTcTitleName);
            assertEquals("Different TC details", loginPage.addedTitles.get(i).name.split("\\s+"), cartTcTitleName.split(" "));
        }

        // Visualisation accpetance
        Thread.sleep(8000);

    }

    @Test
    public void DeleteAllTC() throws InterruptedException
    {
        String username = "srdjan.rados90@gmail.com";
        String password = "Qwertysha1@";

        SandboxDeleteAll loginPage = new SandboxDeleteAll(driver);
        loginPage.login(username,password);

        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("navigate-left-title"),"Test Cases"));
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("main")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("main")));
//        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("portrait-grid")));
//        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("portrait-grid")));

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("no-content")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("no-content")));

        WebElement noContent = driver.findElement(By.className("no-content"));
        Assert.assertTrue("There is no button", noContent.getText().contains("There are no test cases created. Click New Test Case in top right corner to get started."));

        // Visualisation accpetance
        Thread.sleep(8000);
    }
}
