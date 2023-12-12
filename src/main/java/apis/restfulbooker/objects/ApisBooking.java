package apis.restfulbooker.objects;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;

public class ApisBooking {
    private SHAFT.API api;

    //Service Names
    private final String createbooking_servicename = "/booking";
    public ApisBooking(SHAFT.API api){
        this.api=api;
    }


    ////////////////// Business Methods | Actions \\\\\\\\\\\\\\\\\\\\\
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
                .setTargetStatusCode(Apis.success_statuscode).perform();
    }
    public void deleteBooking(String firstname,String lastname ) {
        String ID = getBookingIDS( firstname,  lastname);
        api.delete("/booking/"+ID).setTargetStatusCode(Apis.successDelete_statuscode).perform();
    }
    public String getBookingIDS(String firstname, String lastname){
        api.get(createbooking_servicename)
                .setUrlArguments("firstname"+firstname+"&lastname"+lastname)
                .setTargetStatusCode(Apis.success_statuscode).perform();
        return api.getResponseJSONValue("$[0].bookingid");
    }
    ////Validations/////
    public void validateThatBookingCreated(String expectedfirstname,String expectedlastname,String expectedAdditionalNeeds){
        api.verifyThatResponse().extractedJsonValue("booking.additionalneeds").isEqualTo("Breakfast").perform();
        api.verifyThatResponse().extractedJsonValue("booking.lastname").isEqualTo("Mohamed").perform();
        api.verifyThatResponse().extractedJsonValue("booking.additionalneeds").isEqualTo(expectedAdditionalNeeds).perform();
        api.verifyThatResponse().body().contains("\"bookingid\":").perform();

    }
    public void validateThatTheBookingDeleted(){

        api.assertThatResponse().body().isEqualTo("Created").perform();
    }


}
