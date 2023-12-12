package Register.Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Signup {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    //Locators
    private final By NewUserSignup = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/h2");
    private final By NameInput = By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[2]");
    private final By EmailInput = By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[3]");
    private final By SignupButton = By.xpath("//*[@id='form']/div/div/div[3]/div/form/button");

    public Signup(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public void assertonNewUserSignupisvisible(String expectedResult) {
        driver.element().assertThat(NewUserSignup).text().isEqualTo(expectedResult).perform();
    }
    public void enterNameAndEmail(String name, String email) {
        driver.element().type(NameInput,name);
        driver.element().type(EmailInput,email);
    }
    public void clickSignupButton() {
        driver.element().click(SignupButton);
    }

}
