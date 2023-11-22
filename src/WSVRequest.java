

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.auth0.jwt.JWT;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.net.URI;
import java.util.Date;

public class WSVRequest {
    public String data;


    public WSVRequest(){}

    public String getUnits(String loginId, String authorisation, String comapKey) {

        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.websupervisor.net/v1.1/"+loginId+"/units");


            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Authorization", authorisation);
            request.setHeader("Comap-Key", comapKey);


            // Request body
         //   StringEntity reqEntity = new StringEntity("{body}");
         //   request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();


                String jsonString = EntityUtils.toString(entity);
                JSONObject obj = new JSONObject(jsonString);
                String body = obj.toString();
                return body;




        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }

}




