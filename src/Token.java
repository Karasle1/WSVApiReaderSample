/* Clas for token handling
 there are just two methods .
 - getToken method ask the API for the new token and return it as a string
 method need two params type String - clientId and secret. You have to make the app registration and generate secret on ComAp Cloud Identity API.
 documentation available on the https://portal.websupervisor.com
 - checkExp method simply check token validation. It returns boolean true/false
 method need just one parameter in String type - token which should be checked.
*/

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

    public Token(){}

    public String getToken(String clientId, String secret) {
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
                    "  \"clientId\": \""+clientId+"\",\n" +
                    "  \"secret\": \""+secret+"\"\n" +
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
            return !jwt.getExpiresAt().before(new Date());
        }



    }




