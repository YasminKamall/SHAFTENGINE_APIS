package apis.restfulbooker.objects;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class Apis {
    private SHAFT.API api;

    public Apis(SHAFT.API api){
        this.api=api;
    }
    // Base URL
    public final static String Url = System.getProperty("baseUrl");
    ////Status Codes
    public static final int success_statuscode = 200;
    public static final int successDelete_statuscode = 201;

    ////Service Names
    private static final String authentication_servicename = "/auth";

    public static void login(SHAFT.API api,String username,String password){
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
        String token = api.getResponseJSONValue("token");
        api.addHeader("Cookie","token="+token);


    }
}
