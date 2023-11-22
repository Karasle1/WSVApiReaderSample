

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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

public class Token {
    public String token;
    public String exp;

    public Token(){}

    public String getToken() {
        HttpClient httpclient = HttpClients.createDefault();
        String token = null;
        try
        {
            URIBuilder builder = new URIBuilder("https://api.websupervisor.net/identity/application/authenticate");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            // Request body
            StringEntity reqEntity = new StringEntity("{\n" +
                    "  \"clientId\": \"78e21e88-5cf0-4ed5-86c8-a4caad853cfe\",\n" +
                    "  \"secret\": \"4z98Q~AQ-fJjlLVD.OdfqjNnn~XOcKRmVSpH5cSx\"\n" +
                    "}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                String jsonString = EntityUtils.toString(entity);
                JSONObject obj = new JSONObject(jsonString);
                token = obj.getString("access_token");

            }
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return token;
    }

        public boolean checkExp(String token ) {

            DecodedJWT jwt = JWT.decode(token);
            if( jwt.getExpiresAt().before(new Date())) {
                return false;
            } else {
                return true;

            }
        }



    }




