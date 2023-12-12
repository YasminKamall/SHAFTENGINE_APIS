import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestfulBookerAPISTest {
    SHAFT.API api;
    String token;
    String ID;
    ////Service Names
    private final String authentication_servicename = "/auth";
    private final String createbooking_servicename = "/booking"
            ;
    ////Status Codes
    private final int success_statuscode = 200;
    private final int successDelete_statuscode = 201;

    /////////// Configurations \\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeclass(){
        api = new SHAFT.API("https://restful-booker.herokuapp.com");
        login("admin","password123");

    }

    ////Tests
    @Test
    public void Deletetest(){
        deleteBooking("Yasmin","Mohamed");
        validateThatTheBookingDeleted();
    }
    @Test
    public void createbookingtest(){
        createBooking("Yasmin","Mohamed","Breakfast");
        validateThatBookingCreated("Yasmin","Mohamed","Breakfast");

    }
    @Test
    public void getBookingIdstest(){
        getBookingIDS("Yasmin","Mohamed");
    }


    ////////////////// Business Methods | Actions \\\\\\\\\\\\\\\\\\\\\
    public void login(String username,String password){
        String tokenbody = """
                {
                    "username" : "{USERNAME}",
                    "password" : "{PASSWORD}"
                    }
                """
                .replace("{USERNAME}",username)
                .replace("{PASSWORD}",password);
        api.post(authentication_servicename)
                .setContentType(ContentType.JSON)
                .setRequestBody(tokenbody)
                .setTargetStatusCode(success_statuscode).
                perform();
        api.assertThatResponse().body().contains("\"token\":").perform();
        token = api.getResponseJSONValue("token");
        api.addHeader("Cookie","token="+token);


    }
    public void createBooking(String firstname,String lastname,String additionalneeds){
        String createbookingbody = """
                {
                    "firstname" : "{FIRSTNAME}",
                    "lastname" : "{LASTNAME}",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2023-11-01",
                        "checkout" : "2024-01-01"
                    },
                    "additionalneeds" : "{ADDITIONAL_NEEDS}"
                }
            """
                .replace("{FIRSTNAME}",firstname)
                .replace("{LASTNAME}",lastname)
                .replace("{ADDITIONAL_NEEDS}", additionalneeds);

        api.post(createbooking_servicename).
                setContentType(ContentType.JSON)
                .setRequestBody(createbookingbody)
                .setTargetStatusCode(success_statuscode).perform();
        ID = api.getResponseJSONValue("bookingid");

    }

    public String getBookingIDS(String firstname, String lastname){
        api.get(createbooking_servicename)
                .setUrlArguments("firstname"+firstname+"&lastname"+lastname)
                .setTargetStatusCode(success_statuscode).perform();
        return api.getResponseJSONValue("$[0].bookingid");
    }
    private void deleteBooking(String firstname,String lastname ) {
        String ID = getBookingIDS( firstname,  lastname);
        api.delete("/booking/"+ID).addHeader("Cookie","token="+token).setTargetStatusCode(successDelete_statuscode).perform();
    }
    ////Validations/////
    public void validateThatBookingCreated(String expectedfirstname,String expectedlastname,String expectedAdditionalNeeds){
        api.verifyThatResponse().extractedJsonValue("booking.additionalneeds").isEqualTo("Breakfast").perform();
        api.verifyThatResponse().extractedJsonValue("booking.lastname").isEqualTo("Mohamed").perform();
        api.verifyThatResponse().extractedJsonValue("booking.additionalneeds").isEqualTo(expectedAdditionalNeeds).perform();
        api.verifyThatResponse().body().contains("\"bookingid\":").perform();

    }
    private void validateThatTheBookingDeleted(){
        api.assertThatResponse().body().isEqualTo("Created").perform();
    }


}
