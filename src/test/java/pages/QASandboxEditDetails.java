package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class QASandboxEditDetails extends BaseHelper
{
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailField;
    @FindBy (xpath = "//input[@name='password']") WebElement passField;
    @FindBy (className = "full-width-btn") WebElement loginButton;
    @FindBy (xpath = "//a[@class='menu-items--item  ' and @href='/testcases']") WebElement tcCard;
    @FindBy (xpath = "//button[text()='Submit']") WebElement submitButton;

    public List<TC> addedTitles;

    WebDriver driver;

    public QASandboxEditDetails(WebDriver driver) {
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

        addedTitles = new ArrayList<>();
    }

    private void clickOnTC(int i)
    {
        // TC info
        TC addedTitle = new TC();

        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("navigate-left-title"),"Test Cases"));
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("main-grid")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("main-grid")));
//        openTC.click();
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("portrait-grid")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("portrait-grid")));

        WebElement testCases = driver.findElement(By.className("portrait-grid"));
        List<WebElement> previewCard = testCases.findElements(By.xpath("//div[@class='preview-card']"));

        WebElement fstTC = previewCard.get(i);
        fstTC.click();

//        js.executeScript("arguments[0].click()",openTC);
        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("navigate-left-title"),"Edit Test Case"));
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("width-container")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("width-container")));
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("form-element--input")));
        //        WebElement titleField = driver.findElement(By.xpath("//input[@placeholder='Title']"));

        WebElement titleField = driver.findElement(By.className("form-element--input"));
        //        WebElement titleField = driver.findElement(By.xpath("/html/body/div/div/div[3]/div[2]/div/div/div[2]/div[1]/div[2]/input"));

        WebElement detailField = driver.findElement(By.xpath("//textarea[@name='description']"));
        System.out.println("Before change:"+" "+detailField.getText());
        detailField.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));
        detailField.sendKeys("The"+" "+"["+titleField.getAttribute("value") +"]"+"belongs to"+"["+i+"]"+" "+"of this use case is even/odd.");

//        js.executeScript("arguments[0].value='The [id] of this use case is even/odd.';", titleField);
        System.out.println("After change:"+" "+detailField.getText());
        addedTitle.name = detailField.getText();
        System.out.println("Added to list:"+" "+addedTitle.name);

        // Added object Title to  list addedTitles
        addedTitles.add(addedTitle);

        submitButton.click();

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
