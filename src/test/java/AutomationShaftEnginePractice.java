import com.shaft.driver.SHAFT;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Register.Pages.Homepage;
import Register.Pages.Information;
import Register.Pages.Signup;
public class AutomationShaftEnginePractice {

    SHAFT.GUI.WebDriver driver;

    SHAFT.TestData.JSON testData;
    private Homepage home;
    private Information info;
    private Signup signup;
    @BeforeClass
    public void beforeClass() {
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("src/test/resources/TestData/TestData.json");

    }
    @BeforeMethod
    public void BrowserSearch() {
        info = new Information(driver);
        home = new Homepage(driver);
        signup = new Signup(driver);
        home.navigatetohomepage();
    }

    @Test
    public void RegisterUser() {
        //Verify that home page is visible successfully
        home.assertonhomepageisvisible("FEATURES ITEMS");
        //Click on 'Signup / Login' button
        home.clickSignupLoginButton();
        //Verify 'New User Signup!' is visible
        signup.assertonNewUserSignupisvisible("New User Signup!");
        //Enter name and email address
        signup.enterNameAndEmail(testData.getTestData("UserName"), testData.getTestData("Email")+System.currentTimeMillis()+"@gizasystems.com");
        //Click 'Signup' button
        signup.clickSignupButton();
        //Verify that 'ENTER ACCOUNT INFORMATION' is visible
        info.assertonenteraccountinfoisvisible("ENTER ACCOUNT INFORMATION");
        //Fill details: Title, Name, Email, Password, Date of birth
        info.selectgender();
        info.InputPassword(testData.getTestData("Password"));
        info.Selectdateofbirth(testData.getTestData("BirthDay"), testData.getTestData("Month"), testData.getTestData("Year") );
        //Select checkbox 'Sign up for our newsletter!'
        info.NewsletterCheckBox();
        //Select checkbox 'Receive special offers from our partners!'
        info.SpecialoffersCheckBox();
        //Fill details: First name, Last name, Company, Address1, Address2, Country, State, City, Zipcode, Mobile Number.
        info.filldetails(testData.getTestData("FirstName"), testData.getTestData("LastName"),
                testData.getTestData("Company"), testData.getTestData("Address1"),
                testData.getTestData("Address2"), testData.getTestData("Country"),
                testData.getTestData("State"), testData.getTestData("City"),
                testData.getTestData("ZipCode"), testData.getTestData("Mobile") );
        //Click 'Create Account button'
        info.CreateaccountButton();
        // Verify that 'ACCOUNT CREATED!' is visible
        info.assertonaccountcreatedisvisisble("ACCOUNT CREATED!");
        // Click 'Continue' button
        info.Clickcontinue();
        //Verify that 'Logged in as username' is visible
        info.assertonloggedinasisvisible("Logged in as Yasmin");
        // Click 'Delete Account' button
        info.Clickdeleteaccount();
        //Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        info.assertonAccountdeleted("ACCOUNT DELETED!");
    }

}
