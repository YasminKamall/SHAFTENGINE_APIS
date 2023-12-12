package apis.restfulbooker.tests;
import apis.restfulbooker.objects.Apis;
import apis.restfulbooker.objects.ApisBooking;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestfulBookerAPISTest {
    private SHAFT.API api;
    private ApisBooking apisBooking;



    /////////// Configurations \\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        api = new SHAFT.API(Apis.Url);

        Apis.login(api, "admin", "password123");
        apisBooking = new ApisBooking(api);
    }

    ////Tests
    @Test
    public void Deletetest(){
        apisBooking.deleteBooking("Yasmin","Mohamed");
        apisBooking.validateThatTheBookingDeleted();
    }
    @Test
    public void createbookingtest(){
        apisBooking.createBooking("Yasmin","Mohamed","Breakfast");
        apisBooking.validateThatBookingCreated("Yasmin","Mohamed","Breakfast");

    }
    @Test
    public void getBookingIdstest(){

        apisBooking.getBookingIDS("Yasmin","Mohamed");
    }
}
