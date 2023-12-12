package Register.Pages;
import org.openqa.selenium.By;
import com.shaft.driver.SHAFT;
import org.testng.annotations.BeforeMethod;




public class Homepage {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;

    String HomePageURL = "http://automationexercise.com";

    //Element Locators
    private final By PageTitle = By.xpath("/html/body/section[2]/div/div[1]/div[2]/div[1]/h2");
    private final By SignupLoginButton = By.linkText("Signup / Login");

    public Homepage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }


    //Actions
    @BeforeMethod
    public void navigatetohomepage() {
        driver.browser().navigateToURL(HomePageURL);
    }


    public void assertonhomepageisvisible(String expectedResult) {
        driver.element().assertThat(PageTitle).text().isEqualTo(expectedResult)
                .perform();
    }
    public void clickSignupLoginButton() {
        driver.element()
                .click(SignupLoginButton);
    }




}
