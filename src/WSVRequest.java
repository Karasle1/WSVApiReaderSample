/* Class for the request to WSV API handling
just one method getUnits is implemeted
method accepts three params in String type - loginId which is your websupervisor login,
authorisation is bearer token with "Bearer" prefix needed, and comapKey created during app registration.

 is possible to extend by others supported API methods
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URI;


public class WSVRequest {


    public WSVRequest(){}

    public String getUnits(String loginId, String authorisation, String comapKey) {

        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.websupervisor.net/v1.1/"+loginId+"/units");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            // Headers configurations
            request.setHeader("Authorization", authorisation);
            request.setHeader("Comap-Key", comapKey);

            /*Create the request body if becessary */
            // Request body
            //   StringEntity reqEntity = new StringEntity("{body}");
            //   request.setEntity(reqEntity);


            /* Response parsing to JSON returned in body as a String */
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();


                String jsonString = EntityUtils.toString(entity);
                JSONObject obj = new JSONObject(jsonString);
                return obj.toString();


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }

}




