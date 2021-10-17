package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QASandboxLoginPage extends BaseHelper {

    @FindBy (xpath = "//input[@name='email']") WebElement emailField;
    @FindBy (xpath = "//input[@name='password']") WebElement passField;
    @FindBy (className = "full-width-btn") WebElement loginButton;

    WebDriver driver;

    public QASandboxLoginPage(WebDriver driver) {
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

    public void login(String username,String password)
    {
        navigateToQASandboxLoginPage();
        enterValidCredentials(username,password);
        presslogin();
    }
}
