package Register.Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Information {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    public Information(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    private final By NameInput = By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[2]");
    private final By EmailInput = By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[3]");
    private final By SignupButton = By.xpath("//*[@id='form']/div/div/div[3]/div/form/button");
    private final By ENTERACCOUNTINFORMATION = By.xpath("//*[@id='form']/div/div/div/div[1]/h2/b");
    private final By GenderCheckBox = By.xpath("//*[@id='id_gender2']");
    private final By PasswordInput = By.xpath("//*[@id='password']");
    private final By dayslist = By.id("days");
    private final By monthslist = By.id("months");
    private final By yearslist = By.id("years");
    private final By NewsletterCheckbox = By.xpath("//*[@id='newsletter']");
    private final By SpecialOffersCheckbox = By.xpath("//*[@id='optin']");
    private final By firstNameInput = By.xpath("//*[@id='first_name']");
    private final By lastNameInput = By.xpath("//*[@id='last_name']");
    private final By CompanyInput = By.xpath("//*[@id='company']");
    private final By address1Input = By.xpath("//*[@id='address1']");
    private final By address2Input = By.xpath("//*[@id='address2']");
    private final By countrylist = By.id("country");
    private final By stateInput = By.xpath("//*[@id='state']");
    private final By cityInput = By.xpath("//*[@id='city']");
    private final By zipcodeInput = By.xpath("//*[@id='zipcode']");
    private final By mobileNumberInput = By.xpath("//*[@id='mobile_number']");
    private final By CreateAccountButton = By.xpath("//*[@id='form']/div/div/div/div[1]/form/button");
    private final By accountCreatedHeader = By.xpath("//*[@id='form']/div/div/div/h2/b");
    private final By continueButton = By.xpath("//*[@id='form']/div/div/div/div/a");
    private final By loggedInHeader = By.xpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[10]/a");
    private final By DeleteAccountButton = By.xpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[5]");
    private final By AccountDeletedHeader = By.xpath("//*[@id='form']/div/div/div/h2/b");
    private final By ContinueButton = By.xpath("//*[@id=\"form\"]/div/div/div/div/a");

    //Actions
    public void assertonenteraccountinfoisvisible(String expectedResult){
        driver.element().assertThat(ENTERACCOUNTINFORMATION).text().isEqualTo(expectedResult)
                .perform();
    }
    public void selectgender(){
        driver.element()
                .click(GenderCheckBox);
    }
    public void InputPassword(String password){
        driver.element()
                .type(PasswordInput,password);
    }
    public void Selectdateofbirth(String day,String month,String year){
        driver.element().select(dayslist,day);
        driver.element().select(monthslist,month);
        driver.element().select(yearslist,year);

    }
    public void NewsletterCheckBox(){
        driver.element()
                .click(NewsletterCheckbox);
    }
    public void SpecialoffersCheckBox(){
        driver.element()
                .click(SpecialOffersCheckbox);
    }
    public void filldetails(String firstname,String lastname, String Company,String Add1,String Add2,String country,String state,String city, String zipcode,String mobilenumber){
        driver.element().type(firstNameInput,firstname);
        driver.element().type(lastNameInput,lastname);
        driver.element().type(CompanyInput,Company);
        driver.element().type(address1Input,Add1);
        driver.element().type(address2Input,Add2);
        driver.element().select(countrylist, country);
        driver.element().type(stateInput,state);
        driver.element().type(cityInput,city);
        driver.element().type(zipcodeInput,zipcode);
        driver.element().type(mobileNumberInput,mobilenumber);
    }
    public void CreateaccountButton(){
        driver.element().click(CreateAccountButton);
    }
    public void assertonaccountcreatedisvisisble(String expected){
        driver.element().assertThat(accountCreatedHeader)
                .text().isEqualTo(expected)
                .perform();
    }
    public void Clickcontinue(){
        driver.element().click(continueButton);
    }
    public void assertonloggedinasisvisible(String expected){
        driver.element().assertThat(loggedInHeader)
                .text().isEqualTo(expected)
                .perform();
    }
    public void Clickdeleteaccount(){
        driver.element().click(DeleteAccountButton);
    }
    public void assertonAccountdeleted(String expected){
        driver.element().assertThat(AccountDeletedHeader)
                .text().isEqualTo(expected)
                .perform();
        driver.element().click(ContinueButton);
    }

}
