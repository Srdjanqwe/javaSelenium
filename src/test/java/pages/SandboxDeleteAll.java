package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SandboxDeleteAll extends BaseHelper
{
    @FindBy(xpath = "//input[@name='email']") WebElement emailField;
    @FindBy (xpath = "//input[@name='password']") WebElement passField;
    @FindBy (className = "full-width-btn") WebElement loginButton;
    @FindBy (xpath = "//a[@class='menu-items--item  ' and @href='/testcases']") WebElement tcCard;

    WebDriver driver;

    public SandboxDeleteAll(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToQASandboxLoginPage() { driver.get("https://qa-sandbox.ni.htec.rs/login"); }
    private void enterValidCredentials(String username, String password)
    {
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("root")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("root")));
        emailField.sendKeys(username);
        passField.sendKeys(password);
    }
    private void presslogin() { loginButton.click(); }

    private void waitForMainTCGrid()
    {
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@href='/dashboard']")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@href='/dashboard']")));
        js.executeScript("arguments[0].click()",tcCard);
    }

    private void clickOnTC(int i)
    {
        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("navigate-left-title"),"Test Cases"));
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("main")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("main")));
//        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("portrait-grid")));
//        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("portrait-grid")));

//        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("preview-card")));
//        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("preview-card")));

        WebElement testCases = driver.findElement(By.className("portrait-grid"));
        List<WebElement> previewCard = testCases.findElements(By.xpath("//div[@class='preview-card']"));
        System.out.println("size:"+previewCard.size());

        WebElement fstTC = previewCard.get(i);
        js.executeScript("arguments[0].click();", fstTC);

        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("navigate-left-title"),"Edit Test Case"));
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("width-container")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("width-container")));
        wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-danger']")));

        WebElement deleteButton = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
        deleteButton.click();
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='react-confirm-alert']")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='react-confirm-alert']")));
        WebElement removeButton = driver.findElement(By.className("confirmation-dialog--buttons--confirm"));
        removeButton.click();

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='alert']")));
    }

    private void editAllTCTitles()
    {
        for (int i=0; i<=1; i++)
        {
            clickOnTC(i);
        }
    }

    public void login(String username,String password)
    {
        navigateToQASandboxLoginPage();
        enterValidCredentials(username,password);
        presslogin();
        waitForMainTCGrid();
        editAllTCTitles();
    }
}
